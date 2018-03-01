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
	 * Méthode GetVilleById
	 * @param id: identifiant de la ville
	 * @return la ville si elle existe
	 */
	public Ville GetVilleById(int id) {
		Ville ville = null;
		Ville villeCourante;
		
		for(int i = 0; i < villes.size(); i++) {
			villeCourante = villes.get(i);
			if(villeCourante.GetId() == id)
				ville = villeCourante;
		}
		
		return ville;
	}
	
	/**
	 * Méthode LireGraphe
	 */
	public void LireGraphe() {
		String chaine = "";
		for(int i = 0; i < villes.size();i++) {
			chaine += villes.get(i).AfficherSurCarte();			
		}
		
		System.out.println(chaine);
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
		plusCourtChemin = CalculerChemin(depart, arrivee);
		if(plusCourtChemin.isEmpty() && vehicule.GetCompagnie() instanceof CheapCar) {
			vehicule.SetCompagnie(new SuperCar());
			PlusCourtChemin(vehicule, depart, arrivee);
			return;
		}
		
		int tempsParcours = CalculerTemps(plusCourtChemin, vehicule);
		if (tempsParcours == -1)
			if(vehicule.GetCompagnie() instanceof CheapCar) {
				vehicule.SetCompagnie(new SuperCar());
				PlusCourtChemin(vehicule, depart, arrivee);
				return;
			}
		AfficherChemin(tempsParcours, plusCourtChemin, vehicule);
	}
	
	/**
	 * Méthode CalculerChemin
	 * @param debut: ville d'origine
	 * @param fin: ville d'arrivee
	 * @return: la liste des villes du plus court chemin
	 */
	private ArrayList<Ville> CalculerChemin(Ville debut, Ville fin){
		debut.SetTempsTotal(0);
		Ville villeActuelle = debut;
		while(villeActuelle != fin) {
			Iterator<Route> itr = villeActuelle.GetRoutes().iterator();
			while(itr.hasNext()) {
				Route currentRoute = itr.next();
				Ville villeSuivante = currentRoute.GetDestination();
				int oldTemps = villeSuivante.GetTempsTotal();
				int newTemps = villeActuelle.GetTempsTotal() + currentRoute.GetTemps();
				if(newTemps < oldTemps) {
					villeSuivante.SetTempsTotal(newTemps);
					villeSuivante.SetPrecedente(villeActuelle);
				}
			}
			villeActuelle.SetStatutVisitee(true);
			villeActuelle = TrouverProchaineVille(fin);
		}
		
		ArrayList<Ville> plusCourtChemin = new ArrayList<Ville>();
		if(villeActuelle.GetPrecedente() != null) {
			plusCourtChemin.add(0, villeActuelle);
			while((villeActuelle = villeActuelle.GetPrecedente()) != null) {
				plusCourtChemin.add(0, villeActuelle);
			}
		}
		return plusCourtChemin;
	}
	
	/**
	 * Méthode AfficherChemin
	 * @param tempsParcours: le temps total du trajet
	 * @param chemin: le trajet parcouru
	 */
	private void AfficherChemin(int tempsParcours, ArrayList<Ville> chemin, Vehicule vehicule) {
		if(tempsParcours == -1) {
		System.out.println("Impossible d'effectuer le braquage");
		return;
		}		
		String chaine = "Véhicule de la compagnie " + vehicule.GetCompagnie().getClass().getName() + ". Chemin parcouru: ";
		
		for(int i = 0; i < chemin.size(); i++) {
			chaine += chemin.get(i).AfficherChemin() + "->";
		}
		
		if(chaine.charAt(chaine.length() - 1) == '>')
			chaine = chaine.substring(0, chaine.length() - 3);
		chaine += " Durée du trajet: " + tempsParcours + " minutes. Essence restante: " + vehicule.GetQuantiteEssence() + "%.";
		
		System.out.println(chaine);
	}
	
	/**
	 * Méthode InitDistance
	 */
	private void InitDistance() {
		Iterator<Ville> itr = villes.iterator();
		while(itr.hasNext()) {
			Ville ville =itr.next();
			ville.SetTempsTotal(Integer.MAX_VALUE);
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
			if(!comp.GetStatutVisitee() && comp.GetTempsTotal() < prochaine.GetTempsTotal())
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
		if(villes.isEmpty())
			return -1;
		int temps = 0;
		for (int i = 0; i < villes.size()-1; i++) {
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
