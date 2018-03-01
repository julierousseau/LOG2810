import java.util.ArrayList;

/**
 * Classe Ville
 * 
 * Samuel D'Amours-Fortier, Justine Lambert et Julie Rousseau
 * LOG2810 : Structures discrètes
 * 
 * Repr�sente une ville sur une carte
 *
 */

public class Ville implements Affichage {
	private int id;
	private ArrayList<Route> routes;
	private boolean stationService;
	//Attributs n�cessaire pour le plus court chemin
	private int temps;
	private boolean estVisitee;
	private Ville precedente;
	
	public Ville(int id, boolean stationService) {
		this.id = id;
		this.stationService = stationService;
		this.routes = new ArrayList<Route>();
	}
	
	/**
	 * M�thode GetId
	 * @return: identifiant de la ville
	 */
	public int GetId() {
		return id;
	}
	
	/**
	 * M�thode EstStationService
	 * @return: si la ville poss�de une station service
	 */
	public boolean EstStationService() {
		return stationService;
	}
	
	/**
	 * M�thode GetRoutes
	 * @return: Le tableau de toutes les routes li�es � la ville
	 * */
	public ArrayList<Route> GetRoutes() {
		return routes;
	}
	
	/**
	 * M�thode AjouterRoute
	 * @param route: la route � ajouter
	 */
	public void AjouterRoute(Route route) {
		routes.add(route);
	}
	
	/**
	 * M�thode AfficherChemin
	 * @return: une chaine de caract�res avec tous les chemins 
	 */
	public String AfficherChemin() {
		return " " + id + " ";
	}
	
	/**
	 * M�thode AfficherSurCarte
	 * @return: une chaine de caract�res avec toutes les villes adjacentes et les routes
	 */
	public String AfficherSurCarte() {
		String carte = "(" + id + "(";
		
		for(int i = 0; i < routes.size(); i++) {
			carte += routes.get(i).AfficherSurCarte() + ",";
		}
		
		if(carte.charAt(carte.length() - 1) == ',')
			carte = carte.substring(0, carte.length() - 2);
		
		carte += "))";
		
		return carte;
	}
	
	//M�thodes n�cessaire pour le plus court chemin
	/**
	 * M�thode SetDistance
	 * @param temps: le temps par rapport au point d'arriv�
	 */
	public void SetTemps(int temps) {
		this.temps = temps;
	}
	
	/**
	 * M�thode GetDistance
	 * @return: la distance par rapport au point d'arriv�
	 */
	public int GetTemps() {
		return temps;
	}
	
	/**
	 * M�thode SetStatutVisitee
	 * @param visitee: la valeur bool�enne si le ville a �t� visit� ou non
	 */
	public void SetStatutVisitee(boolean visitee) {
		this.estVisitee = visitee;
	}
	/**
	 * M�thode GetStatusVisite
	 * return: l'information si la ville � �t� visit�
	 */
	public boolean GetStatutVisitee() {
		return estVisitee;
	}
	/**
	 * M�thode SetPrecedente
	 * @param precedente: la ville pr�c�dente dans le chemin
	 */
	public void SetPrecedente(Ville precedente) {
		this.precedente = precedente;
	}
	/**
	 * M�thode GetPrecedente
	 * @return: la ville pr�c�dente dans le chemin
	 */
	public Ville GetPrecedente() {
		return precedente;
	}
}

