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
		
		return "carte";
	}
}
