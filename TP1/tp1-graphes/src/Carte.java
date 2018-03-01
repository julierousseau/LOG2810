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
			chaine += villes.get(i).AfficherSurCarte() + ",\n";			
		}
		
		if(chaine.charAt(chaine.length() - 1) == ',')
			chaine = chaine.substring(0, chaine.length() - 2);
		
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
		plusCourtChemin = CalculerChemin(depart, arrivee, vehicule);
		if(plusCourtChemin.isEmpty() && vehicule.GetCompagnie() instanceof CheapCar) {
			plusCourtChemin = CheminEconomique(depart, arrivee, vehicule);
			if(plusCourtChemin.isEmpty()) {
				vehicule.SetCompagnie(new SuperCar());
				plusCourtChemin = CalculerChemin(depart, arrivee, vehicule);
				}
		}
		
		int tempsParcours = CalculerTemps(plusCourtChemin, vehicule);
		AfficherChemin(tempsParcours, plusCourtChemin, vehicule);
	}
	
	/**
	 * M�thode CalculerChemin
	 * @param debut: ville d'origine
	 * @param fin: ville d'arrivee
	 * @return: la liste des villes du plus court chemin
	 */
	private ArrayList<Ville> CalculerChemin(Ville debut, Ville fin, Vehicule vehicule){
		InitDistance();
		debut.SetTempsTotal(0);
		Ville villeActuelle = debut;
		while(villeActuelle != fin) {
			CalculerEssence(ConstruireChemin(villeActuelle), vehicule);
			if(villeActuelle.EstStationService())
				vehicule.SetQuantiteEssence(100);
			for(Route currentRoute : villeActuelle.GetRoutes()) {
				Ville villeSuivante = currentRoute.GetDestination();
				if(!villeSuivante.GetStatutVisitee()) {
					int oldTemps = villeSuivante.GetTempsTotal();
					int newTemps = villeActuelle.GetTempsTotal() + currentRoute.GetTemps();
					if(newTemps < oldTemps && vehicule.GetTempsRestant() > currentRoute.GetTemps()) {
						villeSuivante.SetTempsTotal(newTemps);
						villeSuivante.SetPrecedente(villeActuelle);
					}
				}
			}
			villeActuelle.SetStatutVisitee(true);
			villeActuelle = TrouverProchaineVille(fin);
		}
		
		return ConstruireChemin(villeActuelle);
	}

	/**
	 * M�thode CheminEconomique
	 * @param debut: ville d'origine
	 * @param fin: ville d'arrivee
	 * @return: la liste des villes selon l'essence comme crit�re principal
	 */
	private ArrayList<Ville> CheminEconomique(Ville debut, Ville fin, Vehicule vehicule){
		InitDistance();
		debut.SetTempsTotal(0);
		Ville villeActuelle = debut;
		while(villeActuelle != fin) {
			for(Route currentRoute : villeActuelle.GetRoutes()) {
				Ville villeSuivante = currentRoute.GetDestination();
				if(!villeSuivante.GetStatutVisitee()) {
					int newTemps = villeActuelle.GetTempsTotal() + currentRoute.GetTemps();
					int oldEssence = CalculerEssence(ConstruireChemin(villeSuivante),vehicule);
					Ville oldPrecedente = villeSuivante.GetPrecedente();
					villeSuivante.SetPrecedente(villeActuelle);
					if(CalculerEssence(ConstruireChemin(villeSuivante),vehicule) > oldEssence)
						villeSuivante.SetTempsTotal(newTemps);
					else
						villeSuivante.SetPrecedente(oldPrecedente);
				}
			}
			villeActuelle.SetStatutVisitee(true);
			villeActuelle = TrouverProchaineVille(fin);
		}
		
		return ConstruireChemin(villeActuelle);
	}
	
	/**
	 * M�thode ConstruireChemin
	 * @param ville: ville vers laquelle on veut construire le chemin
	 * @return: la liste des villes du chemin
	 */
	private ArrayList<Ville> ConstruireChemin(Ville ville) {
		ArrayList<Ville> chemin = new ArrayList<Ville>();
		if(ville.GetPrecedente() != null) {
			chemin.add(0, ville);
			while((ville = ville.GetPrecedente()) != null) {
				chemin.add(0, ville);
			}
		}
		return chemin;
	}
		
	/**
	 * M�thode AfficherChemin
	 * @param tempsParcours: le temps total du trajet
	 * @param chemin: le trajet parcouru
	 */
	private void AfficherChemin(int tempsParcours, ArrayList<Ville> chemin, Vehicule vehicule) {
		if(tempsParcours == -1) {
		System.out.println("Impossible d'effectuer le braquage");
		return;
		}		
		String chaine = "V�hicule de la compagnie " + vehicule.GetCompagnie().getClass().getName() + ".\nChemin parcouru: ";
		
		for(int i = 0; i < chemin.size(); i++) {
			chaine += chemin.get(i).AfficherChemin() + "->";
		}
		
		if(chaine != "" && chaine.charAt(chaine.length() - 1) == '>')
			chaine = chaine.substring(0, chaine.length() - 3);

		CalculerEssence(chemin, vehicule);
		chaine += " \nDur�e du trajet: " + tempsParcours + " minutes. \nEssence restante: " + vehicule.GetQuantiteEssence() + "%.";
		
		System.out.println(chaine);
	}
	
	/**
	 * M�thode InitDistance
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
	 * M�thode TrouverProchaineVille
	 * @param finale: Permet de comparer et de savoir si un trajet est possible
	 * @return : la ville non visit�e ayant la plus petite distance.
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
	 * M�thode CalculerTemps
	 * @param chemin: le chemin � parcourir
	 * @return: le temps total si le trajet est possible
	 */
	private int CalculerTemps(ArrayList<Ville> villes, Vehicule vehicule) {
		vehicule.SetQuantiteEssence(100);
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
			}
			temps += routeCourante.GetTemps()*60;
		}
		return temps;
	}
	
	/**
	 * M�thode CalculerEssence
	 * @param chemin: le chemin � parcourir
	 * @return: la quantit� d'essence restance
	 */
	private int CalculerEssence(ArrayList<Ville> villes, Vehicule vehicule) {
		vehicule.SetQuantiteEssence(100);
		if(villes.isEmpty())
			return -1;
		for (int i = 0; i < villes.size()-1; i++) {
			Ville villeCourante = villes.get(i);
			Iterator<Route> itrr = villeCourante.GetRoutes().iterator();
			Route routeCourante = itrr.next();				
			while(routeCourante.GetDestination() != villes.get(i+1))
				routeCourante = itrr.next();
			if(villeCourante.EstStationService() && i != 0) {
				vehicule.SetQuantiteEssence(100);
			}
			if(!vehicule.ConsommerEssence(routeCourante.GetTemps()))
				return -1;
			}
		return vehicule.GetQuantiteEssence();
	}
}
