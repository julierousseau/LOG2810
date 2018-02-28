/**
 * Classe abstraite Vehicule
 * 
 * Permet de d�finir les propri�t�s d'un v�hicule.
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
	 * M�thode abstraite GetConsommation
	 * @return le consommation d'essence par heure de route
	 */
	public abstract int GetConsommation();
	
	/**
	 * M�thode GetCompagnie
	 * @return la compagnie de location du v�hicule
	 */
	public Compagnie GetCompagnie() {
		return compagnie;
	}
	
	/**
	 * M�thode SetCompagnie
	 * @param nouvelleCompagnie: La nouvelle compagnie du v�hicule
	 */
	public void SetCompagnie(Compagnie nouvelleCompagnie) {
		compagnie = nouvelleCompagnie;
	}
	
	/**
	 * M�thode GetTempsRestant
	 * @return obtient le temps restant selon la quantit� d'essence en minutes
	 */
	public int GetTempsRestant() {
		return (qteEssence - 12) / GetConsommation();
	}
	
	/**
	 * M�thode SetQuantiteEssence
	 * @param nouvelleQteEssence: La nouvelle quantit� d'essence du v�hicule
	 */
	public void SetQuantiteEssence(int nouvelleQteEssence) {
		qteEssence = nouvelleQteEssence;
	}
}
