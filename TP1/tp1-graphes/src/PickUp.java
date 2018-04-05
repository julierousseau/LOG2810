/**
 * Classe PickUp
 * 
 * Samuel D'Amours-Fortier, Justine Lambert et Julie Rousseau
 * LOG2810 : Structures discrètes
 * 
 * Permet de d�finir les propri�t�s pour un PickUp
 *
 */

public class PickUp extends Vehicule {

	public PickUp(Compagnie cie, int qte) {
		super(cie, qte);
	}
	
	@Override
	public int GetConsommation() {
		return GetCompagnie().GetConsommationPickup();
	}

}
