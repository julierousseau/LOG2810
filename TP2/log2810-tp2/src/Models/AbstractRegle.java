package Models;

public abstract class AbstractRegle {
	protected char valeur;
	protected int index;
	
	public AbstractRegle(char valeur) {
		this.valeur = valeur;
		this.index = 0;
	}
	
	abstract void Valider(Variante variante);
}
