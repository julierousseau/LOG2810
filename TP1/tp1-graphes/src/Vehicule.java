/**
 * Classe abstraite Vehicule
 * 
 * Permet de définir les propriétés d'un véhicule.
 * 
 **/
public abstract class Vehicule {
	private Compagnie compagnie;
	private int qteEssence;

	public Vehicule(Compagnie cie, int qte) {
		compagnie = cie;
		qteEssence = qte;
	}
	
	/**
	 * Méthode abstraite GetConsommation
	 * @return le consommation d'essence par heure de route
	 */
	public abstract int GetConsommation();
	
	/**
	 * Méthode GetCompagnie
	 * @return la compagnie de location du véhicule
	 */
	public Compagnie GetCompagnie() {
		return compagnie;
	}
	
	/**
	 * Méthode SetCompagnie
	 * @param nouvelleCompagnie: La nouvelle compagnie du véhicule
	 */
	public void SetCompagnie(Compagnie nouvelleCompagnie) {
		compagnie = nouvelleCompagnie;
	}
	
	/**
	 * Méthode GetTempsRestant
	 * @return obtient le temps restant selon la quantité d'essence en heures
	 */
	public int GetTempsRestant() {
		return (qteEssence - 12) / GetConsommation();
	}
	
	/**
	 * Méthode GetQuantitéEssence
	 * @return obtient la quantité d'essence restante
	 */
	public int GetQuantiteEssence() {
		return qteEssence;
	}
	
	/**
	 * Méthode SetQuantiteEssence
	 * @param nouvelleQteEssence: La nouvelle quantité d'essence du véhicule
	 */
	public void SetQuantiteEssence(int nouvelleQteEssence) {
		qteEssence = nouvelleQteEssence;
	}
	
	/**
	 * Méthode ConsommerEssence
	 * @param temps: le temps de parcours
	 * @return: faux s'il n'y a pas suffisament d'essence pour effectuer le trajet
	 */
	public boolean ConsommerEssence(int temps) {
		if(GetTempsRestant() < temps)
			return false;
		SetQuantiteEssence(qteEssence - GetConsommation()*temps);
		return true;
	}
	
}
