/**
 * Classe Automobile
 *
 * Samuel D'Amours-Fortier, Justine Lambert et Julie Rousseau
 * LOG2810 : Structures discrètes
 *
 * Permet de d�finir les propri�t�s d'un v�hicule
 */

public class Automobile extends Vehicule {

	public Automobile(Compagnie cie, int qte) {
		super(cie, qte);
	}

	@Override
	public int GetConsommation() {
		return GetCompagnie().GetConsommationAutomobile();
	}
}
