import java.util.ArrayList;

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
		ArrayList<Route> plusCourtChemin = new ArrayList<Route>();
		
		// TODO: Trouver le plus court chemin 
			// et ajouter les routes du plus court chemin dans l'array
		
		// Affichage du plus court chemin
		String chaine = "";
		
		for(int i = 0; i < plusCourtChemin.size(); i++) {
			chaine += plusCourtChemin.get(i).AfficherChemin() + "->";
		}
		
		if(chaine.charAt(chaine.length() - 1) == '>')
			chaine = chaine.substring(0, chaine.length() - 3);
		
		System.out.print(chaine);
	}
}
