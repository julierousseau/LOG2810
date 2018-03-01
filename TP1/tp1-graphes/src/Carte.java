/**
 * Classe Carte
 *
 * Samuel D'Amours-Fortier, Justine Lambert et Julie Rousseau
 * LOG2810 : Structures discrètes
 *
 * Permet de manipuler une carte et d'obtenir le plus court chemin
 */

import java.util.ArrayList;
import java.util.Iterator;

public class Carte {
	private ArrayList<Ville> villes;
	
	public Carte() {
		villes = new ArrayList<Ville>();
	}
	
	/**
	 * M�thode AjouterVille
	 * @param ville: Ville � ajouter
	 */
	public void AjouterVille(Ville ville) {
		villes.add(ville);
	}
	
	/**
	 * M�thode GetVilleById
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
	 * M�thode LireGraphe
	 */
	public void LireGraphe() {
		String chaine = "";
		for(int i = 0; i < villes.size();i++) {
			chaine += villes.get(i).AfficherSurCarte();			
		}
		
		System.out.println(chaine);
	}
	
	/**
	 * M�thode PlusCourtChemin
	 * @param vehicule: V�hicule � utiliser
	 * @param depart: Ville de d�part
	 * @param arrivee: Ville d'arriv�e
	 */
	public void PlusCourtChemin(Vehicule vehicule,Ville depart, Ville arrivee) {
		ArrayList<Ville> plusCourtChemin = new ArrayList<Ville>();
		
		// TODO: G�rer les chemins alternatifs pour l'essence
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
		AfficherChemin(tempsParcours, plusCourtChemin);
	}
	
	/**
	 * M�thode AfficherChemin
	 * @param tempsParcours: le temps total du trajet
	 * @param chemin: le trajet parcouru
	 */
	private void AfficherChemin(int tempsParcours, ArrayList<Ville> chemin) {
		if(tempsParcours == -1) {
		System.out.println("Impossible d'effectuer le braquage");
		return;
		}		
		String chaine = "";
		
		for(int i = 0; i < chemin.size(); i++) {
			chaine += chemin.get(i).AfficherChemin() + "->";
		}
		
		if(chaine.charAt(chaine.length() - 1) == '>')
			chaine = chaine.substring(0, chaine.length() - 3);
		chaine += "Dur�e du trajet: " + tempsParcours + "minutes";
		
		System.out.println(chaine);
	}
	
	/**
	 * M�thode InitDistance
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
	 * M�thode TrouverProchaineVille
	 * @param finale: Permet de comparer et de savoir si un trajet est possible
	 * @return : la ville non visit�e ayant la plus petite distance.
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
	 * M�thode CalculerTemps
	 * @param chemin: le chemin � parcourir
	 * @return: le temps total si le trajet est possible
	 */
	private int CalculerTemps(ArrayList<Ville> villes, Vehicule vehicule) {
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
