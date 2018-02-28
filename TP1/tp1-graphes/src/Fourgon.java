/**
 * Classe Fourgon
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
