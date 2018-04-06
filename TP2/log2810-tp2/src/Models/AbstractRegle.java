package Models;

public abstract class AbstractRegle {
	protected char valeur;
	
	public AbstractRegle(char valeur) {
		this.valeur = valeur;
	}
	
	abstract void Valider(Variante variante);
}
