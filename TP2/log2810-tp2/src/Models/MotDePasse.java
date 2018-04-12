package Models;

public class MotDePasse extends AbstractRegle {

	public MotDePasse(char valeur, int index) {
		super(valeur, index);
	}

	@Override
	void Valider(Variante variante) {
		if(variante.GetValeur().charAt(index) == valeur) {
			variante.SetValide(true);
		}
	}

	@Override
	void addSuivante(AbstractRegle suivante) {
		Regle nouvelle = new Regle(this);
		this.parent.addSuivante(nouvelle);
		this.parent.getSuivants().remove(this);
		nouvelle.addSuivante(suivante);
	}

	@Override
	AbstractRegle trouverRegle(String primitive) {
		if(primitive.charAt(index) == valeur)
			return this;
		return null;
	}
}
