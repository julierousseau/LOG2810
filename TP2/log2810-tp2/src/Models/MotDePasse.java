package Models;

public class MotDePasse extends AbstractRegle {

	public MotDePasse(char valeur) {
		super(valeur);
	}

	@Override
	void Valider(Variante variante) {
		if(variante.GetValeur().charAt(index) == valeur) {
			variante.SetValide(true);
		}
	}
}
