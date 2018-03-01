import java.util.ArrayList;
import java.util.Iterator;

public class Carte {
	private ArrayList<Ville> villes;
	
	public Carte() {
		villes = new ArrayList<Ville>();
	}
	
	/**
	 * Méthode AjouterVille
	 * @param ville: Ville à ajouter
	 */
	public void AjouterVille(Ville ville) {
		villes.add(ville);
	}
	
	/**
	 * Méthode LireGraphe
	 */
	public void LireGraphe() {
		String chaine = "";
		for(int i = 0; i < villes.size();i++) {
			chaine += villes.get(i).AfficherSurCarte();			
		}
		
		System.out.print(chaine);
	}
	
	/**
	 * Méthode PlusCourtChemin
	 * @param vehicule: Véhicule à utiliser
	 * @param depart: Ville de départ
	 * @param arrivee: Ville d'arrivée
	 */
	public void PlusCourtChemin(Vehicule vehicule,Ville depart, Ville arrivee) {
		ArrayList<Ville> plusCourtChemin = new ArrayList<Ville>();
		
		// TODO: Gérer les chemins alternatifs pour l'essence
		InitDistance();
		depart.SetTemps(0);
		Ville villeActuelle = depart;
		while(villeActuelle != arrivee) {
			Iterator<Route> itr = villeActuelle.GetRoutes().iterator();
			while(itr.hasNext()) {
				Route currentRoute = itr.next();
				Ville villeSuivante = currentRoute.GetDestination();
				int oldTemps = villeSuivante.GetTemps();
				int newTemps = villeActuelle.GetTemps() + currentRoute.GetTemps();
				if(newTemps < oldTemps && vehicule.GetTempsRestant() >= currentRoute.GetTemps()) {
					villeSuivante.SetTemps(newTemps);
					villeSuivante.SetPrecedente(villeActuelle);
				}
			}
			villeActuelle.SetStatutVisitee(true);
			villeActuelle = TrouverProchaineVille(arrivee);
		}
		if(arrivee.GetPrecedente() == null && vehicule.GetCompagnie().getClass().getName() == "CheapCar") {
			vehicule.SetCompagnie(new SuperCar());
			PlusCourtChemin(vehicule, depart, arrivee);
			return;
		}
		while(villeActuelle.GetPrecedente() != null) {
			plusCourtChemin.add(0, villeActuelle);
			villeActuelle = villeActuelle.GetPrecedente();
		}
		int tempsParcours = CalculerTemps(plusCourtChemin, vehicule);
		
		if (tempsParcours == -1)
			if(vehicule.GetCompagnie().getClass().getName() == "CheapCar") {
				vehicule.SetCompagnie(new SuperCar());
				PlusCourtChemin(vehicule, depart, arrivee);
				return;
			}
			else {
				System.out.print("Impossible d'effectuer le braquage");
				return;
			}
				
		
		// Affichage du plus court chemin
		String chaine = "";
		
		for(int i = 0; i < plusCourtChemin.size(); i++) {
			chaine += plusCourtChemin.get(i).AfficherChemin() + "->";
		}
		
		if(chaine.charAt(chaine.length() - 1) == '>')
			chaine = chaine.substring(0, chaine.length() - 3);
		
		System.out.print(chaine);
	}
	
	
	/**
	 * Méthode InitDistance
	 */
	private void InitDistance() {
		Iterator<Ville> itr = villes.iterator();
		while(itr.hasNext()) {
			Ville ville =itr.next();
			ville.SetTemps(Integer.MAX_VALUE);
			ville.SetStatutVisitee(false);
			ville.SetPrecedente(null);
		}
	}
	
	/**
	 * Méthode TrouverProchaineVille
	 * @param finale: Permet de comparer et de savoir si un trajet est possible
	 * @return : la ville non visitée ayant la plus petite distance.
	 */
	private Ville TrouverProchaineVille(Ville finale) {
		Ville prochaine = finale;
		Iterator<Ville> itr = villes.iterator();
		while(itr.hasNext()) {
			Ville comp = itr.next();
			if(!comp.GetStatutVisitee() && comp.GetTemps() < prochaine.GetTemps())
				prochaine = comp;
		}
		return prochaine;
	}

	/**
	 * Méthode CalculerTemps
	 * @param chemin: le chemin à parcourir
	 * @return: le temps total si le trajet est possible
	 */
	private int CalculerTemps(ArrayList<Ville> villes, Vehicule vehicule) {
		int temps = 0;
		for (int i = 0; i < villes.size(); i++) {
			Ville villeCourante = villes.get(i);
			Iterator<Route> itrr = villeCourante.GetRoutes().iterator();
			Route routeCourante = itrr.next();				
			while(routeCourante.GetDestination() != villes.get(i+1))
				routeCourante = itrr.next();
			if(villeCourante.EstStationService()) {
				temps += 15;
				vehicule.SetQuantiteEssence(100);
			}
			if(!vehicule.ConsommerEssence(routeCourante.GetTemps()))
				return -1;
			temps += routeCourante.GetTemps()*60;
		}
		return temps;
	}
}
