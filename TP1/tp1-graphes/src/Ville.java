import java.util.ArrayList;

/**
 * Classe Ville
 * 
 * Samuel D'Amours-Fortier, Justine Lambert et Julie Rousseau
 * LOG2810 : Structures discrÃ¨tes
 * 
 * Reprï¿½sente une ville sur une carte
 *
 */

public class Ville implements Affichage {
	private int id;
	private ArrayList<Route> routes;
	private boolean stationService;
	//Attributs nï¿½cessaire pour le plus court chemin
	private int temps;
	private boolean estVisitee;
	private Ville precedente;
	private boolean testeeEssence = false; 
	
	public Ville(int id, boolean stationService) {
		this.id = id;
		this.stationService = stationService;
		this.routes = new ArrayList<Route>();
	}
	
	/**
	 * Mï¿½thode GetId
	 * @return: identifiant de la ville
	 */
	public int GetId() {
		return id;
	}
	
	/**
	 * Mï¿½thode EstStationService
	 * @return: si la ville possï¿½de une station service
	 */
	public boolean EstStationService() {
		return stationService;
	}
	
	/**
	 * Mï¿½thode GetRoutes
	 * @return: Le tableau de toutes les routes liï¿½es ï¿½ la ville
	 * */
	public ArrayList<Route> GetRoutes() {
		return routes;
	}
	
	/**
	 * Mï¿½thode AjouterRoute
	 * @param route: la route ï¿½ ajouter
	 */
	public void AjouterRoute(Route route) {
		routes.add(route);
	}
	
	/**
	 * Mï¿½thode AfficherChemin
	 * @return: une chaine de caractï¿½res avec tous les chemins 
	 */
	public String AfficherChemin() {
		return " " + id + " ";
	}
	
	/**
	 * Mï¿½thode AfficherSurCarte
	 * @return: une chaine de caractï¿½res avec toutes les villes adjacentes et les routes
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
	
	//Mï¿½thodes nï¿½cessaire pour le plus court chemin
	/**
	 * Mï¿½thode SetDistance
	 * @param temps: le temps par rapport au point d'arrivï¿½
	 */
	public void SetTempsTotal(int temps) {
		this.temps = temps;
	}
	
	/**
	 * Mï¿½thode GetDistance
	 * @return: la distance par rapport au point d'arrivï¿½
	 */
	public int GetTempsTotal() {
		return temps;
	}
	
	/**
	 * Mï¿½thode SetStatutVisitee
	 * @param visitee: la valeur boolï¿½enne si le ville a ï¿½tï¿½ visitï¿½ ou non
	 */
	public void SetStatutVisitee(boolean visitee) {
		this.estVisitee = visitee;
	}
	/**
	 * Mï¿½thode GetStatusVisite
	 * return: l'information si la ville ï¿½ ï¿½tï¿½ visitï¿½
	 */
	public boolean GetStatutVisitee() {
		return estVisitee;
	}
	/**
	 * Mï¿½thode SetPrecedente
	 * @param precedente: la ville prï¿½cï¿½dente dans le chemin
	 */
	public void SetPrecedente(Ville precedente) {
		this.precedente = precedente;
	}
	/**
	 * Mï¿½thode GetPrecedente
	 * @return: la ville prï¿½cï¿½dente dans le chemin
	 */
	public Ville GetPrecedente() {
		return precedente;
	}
	
	/**
	 * Méthode GetTestEssence
	 * @return: si la ville a déja été testé pour un parcours d'essence
	 */
	public boolean GetTestEssence() {
		return testeeEssence;
	}
	
	/**
	 * Méthode SetTestEssence
	 */
	public void SetTestEssence() {
		this.testeeEssence = true;
	}
}

