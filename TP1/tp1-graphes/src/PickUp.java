/**
 * Classe PickUp
 * 
 * Permet de définir les propriétés pour un PickUp
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
