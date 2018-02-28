/**
 * Classe Automobile
 *
 * Permet de définir les propriétés d'un véhicule
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
