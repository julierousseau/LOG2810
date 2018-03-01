/**
 * Classe Fourgon
 * 
 * Samuel D'Amours-Fortier, Justine Lambert et Julie Rousseau
 * LOG2810 : Structures discrètes
 * 
 * Permet de d�finir les propri�t�s d'un fourgon
 *
 */

public class Fourgon extends Vehicule {

	public Fourgon(Compagnie cie, int qte) {
		super(cie, qte);
	}

	@Override
	public int GetConsommation() {
		return GetCompagnie().GetConsommationFourgon();
	}
}
