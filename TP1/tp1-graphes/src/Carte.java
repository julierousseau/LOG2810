/**
 * Classe Carte
 *
 * Samuel D'Amours-Fortier, Justine Lambert et Julie Rousseau
 * LOG2810 : Structures discrÃ¨tes
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
	 * Mï¿½thode AjouterVille
	 * @param ville: Ville ï¿½ ajouter
	 */
	public void AjouterVille(Ville ville) {
		villes.add(ville);
	}
	
	/**
	 * Mï¿½thode GetVilleById
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
	 * Mï¿½thode LireGraphe
	 */
	public void LireGraphe() {
		String chaine = "";
		for(int i = 0; i < villes.size();i++) {
			chaine += villes.get(i).AfficherSurCarte() + ",\n";			
		}
		
		if(chaine.charAt(chaine.length() - 1) == ',')
			chaine = chaine.substring(0, chaine.length() - 2);
		
		System.out.println(chaine);
	}
	
	/**
	 * Mï¿½thode PlusCourtChemin
	 * @param vehicule: Vï¿½hicule ï¿½ utiliser
	 * @param depart: Ville de dï¿½part
	 * @param arrivee: Ville d'arrivï¿½e
	 */
	public void PlusCourtChemin(Vehicule vehicule,Ville depart, Ville arrivee) {
		// TODO: Gï¿½rer les chemins alternatifs pour l'essence
		InitDistance();
		ArrayList<Ville> plusCourtChemin = new ArrayList<Ville>();
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
			for(Route currentRoute : villeActuelle.GetRoutes()) {
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
	 * Méthode CheminAlternatif
	 * @param debut: ville d'origine
	 * @param fin: ville d'arrivee
	 * @return: la liste des villes du plus court chemin
	 */
	//private ArrayList
	
	/**
	 * Mï¿½thode AfficherChemin
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
		
		if(chaine != "" && chaine.charAt(chaine.length() - 1) == '>')
			chaine = chaine.substring(0, chaine.length() - 3);

		chaine += " Durée du trajet: " + tempsParcours + " minutes. Essence restante: " + vehicule.GetQuantiteEssence() + "%.";
		
		System.out.println(chaine);
	}
	
	/**
	 * Mï¿½thode InitDistance
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
	 * Mï¿½thode TrouverProchaineVille
	 * @param finale: Permet de comparer et de savoir si un trajet est possible
	 * @return : la ville non visitï¿½e ayant la plus petite distance.
	 */
	private Ville TrouverProchaineVille(Ville finale) {
		Ville prochaine = finale;
		for(Ville comp : villes) {
			if(!comp.GetStatutVisitee() && comp.GetTempsTotal() < prochaine.GetTempsTotal())
				prochaine = comp;
		}
		return prochaine;
	}

	/**
	 * Mï¿½thode CalculerTemps
	 * @param chemin: le chemin ï¿½ parcourir
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
			if(villeCourante.EstStationService() && i != 0) {
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
