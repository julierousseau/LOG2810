package Models;

public abstract class AbstractRegle {
	protected char valeur;
	protected int index;
	protected Regle parent;
	
	public AbstractRegle(char valeur, int index) {
		this.valeur = valeur;
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}
	
	public abstract void addSuivante(AbstractRegle regle);
	public abstract void Valider(Variante variante);
	public abstract AbstractRegle trouverRegle(String primitive);

}
