/**
 * Classe Route
 * 
 * Samuel D'Amours-Fortier, Justine Lambert et Julie Rousseau
 * LOG2810 : Structures discrètes
 * 
 * Repr�sente une route sur une carte
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
	 * M�thode GetDestination
	 * @return: la destination de la route
	 */
	public Ville GetDestination() {
		return destination;
	}
	
	/**
	 * M�thode GetTemps
	 * @return: le temps pour se rendre � destination par la route
	 */
	public int GetTemps() {
		return tempsDeplacement;
	}
	
	/**
	 * M�thode AfficherChemin
	 * @return: une chaine de caract�re du segment du chemin
	 */
	public String AfficherChemin() {
		return destination.AfficherChemin();
	}
	
	/**
	 * M�thode AfficherSurCarte
	 * @return: une chaine de caract�re repr�sentant les informations de la route
	 */
	public String AfficherSurCarte() {
		return "(" + destination.GetId() + "," + tempsDeplacement + ")";
	}
}
