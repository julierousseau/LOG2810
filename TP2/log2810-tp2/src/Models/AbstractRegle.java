package Models;

public abstract class AbstractRegle {
	protected char valeur;
	protected int index;
	protected Regle parent;
	
	public AbstractRegle(char valeur, int index) {
		this.valeur = valeur;
		this.index = index;
	}
	
	abstract void addSuivante(AbstractRegle regle);
	abstract void Valider(Variante variante);
	abstract AbstractRegle trouverRegle(String primitive);

}
