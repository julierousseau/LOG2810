import java.util.ArrayList;

/**
 * Classe Ville
 * 
 * Représente une ville sur une carte
 *
 */
public class Ville implements Affichage {
	private int id;
	private ArrayList<Route> routes;
	private boolean stationService;
	//Attributs nécessaire pour le plus court chemin
	private int temps;
	private boolean estVisitee;
	private Ville precedente;
	
	public Ville(int id, boolean stationService) {
		this.id = id;
		this.stationService = stationService;
		this.routes = new ArrayList<Route>();
	}
	
	/**
	 * Méthode GetId
	 * @return: identifiant de la ville
	 */
	public int GetId() {
		return id;
	}
	
	/**
	 * Méthode EstStationService
	 * @return: si la ville possède une station service
	 */
	public boolean EstStationService() {
		return stationService;
	}
	
	/**
	 * Méthode GetRoutes
	 * @return: Le tableau de toutes les routes liées à la ville
	 * */
	public ArrayList<Route> GetRoutes() {
		return routes;
	}
	
	/**
	 * Méthode AjouterRoute
	 * @param route: la route à ajouter
	 */
	public void AjouterRoute(Route route) {
		routes.add(route);
	}
	
	/**
	 * Méthode AfficherChemin
	 * @return: une chaine de caractères avec tous les chemins 
	 */
	public String AfficherChemin() {
		return " " + id + " ";
	}
	
	/**
	 * Méthode AfficherSurCarte
	 * @return: une chaine de caractères avec toutes les villes adjacentes et les routes
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
	
	//Méthodes nécessaire pour le plus court chemin
	/**
	 * Méthode SetDistance
	 * @param temps: le temps par rapport au point d'arrivé
	 */
	public void SetTempsTotal(int temps) {
		this.temps = temps;
	}
	
	/**
	 * Méthode GetDistance
	 * @return: la distance par rapport au point d'arrivé
	 */
	public int GetTempsTotal() {
		return temps;
	}
	
	/**
	 * Méthode SetStatutVisitee
	 * @param visitee: la valeur booléenne si le ville a été visité ou non
	 */
	public void SetStatutVisitee(boolean visitee) {
		this.estVisitee = visitee;
	}
	/**
	 * Méthode GetStatusVisite
	 * return: l'information si la ville à été visité
	 */
	public boolean GetStatutVisitee() {
		return estVisitee;
	}
	/**
	 * Méthode SetPrecedente
	 * @param precedente: la ville précédente dans le chemin
	 */
	public void SetPrecedente(Ville precedente) {
		this.precedente = precedente;
	}
	/**
	 * Méthode GetPrecedente
	 * @return: la ville précédente dans le chemin
	 */
	public Ville GetPrecedente() {
		return precedente;
	}
}

