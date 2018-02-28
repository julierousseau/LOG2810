/**
 * Classe Route
 * 
 * Représente une route sur une carte
 *
 */
public class Route implements Affichage {
	private Ville destination;
	private int tempsDeplacement;
	
	public Route(Ville dest, int duree) {
		destination = dest;
		tempsDeplacement = duree;
	}
	
	/**
	 * Méthode GetDestination
	 * @return: la destination de la route
	 */
	public Ville GetDestination() {
		return destination;
	}
	
	/**
	 * Méthode GetTemps
	 * @return: le temps pour se rendre à destination par la route
	 */
	public int GetTemps() {
		return tempsDeplacement;
	}
	
	/**
	 * Méthode AfficherChemin
	 * @return: une chaine de caractère du segment du chemin
	 */
	public String AfficherChemin() {
		return destination.AfficherChemin();
	}
	
	/**
	 * Méthode AfficherSurCarte
	 * @return: une chaine de caractère représentant les informations de la route
	 */
	public String AfficherSurCarte() {
		return "(" + destination.GetId() + "," + tempsDeplacement + ")";
	}
}
