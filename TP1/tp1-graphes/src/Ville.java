import java.util.ArrayList;

/**
 * Classe Ville
 * 
 * Repr�sente une ville sur une carte
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
		
		return "carte";
	}
}
