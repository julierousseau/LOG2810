package Models;

import java.util.ArrayList;

public class Regle extends AbstractRegle {
	private ArrayList<AbstractRegle> suivants;
	
	public Regle(char valeur, int index) {
		super(valeur, index);
		suivants = new ArrayList<AbstractRegle>();
	}
	
	public Regle(MotDePasse nouvelle) {
		super(nouvelle.valeur, nouvelle.index);
		suivants = new ArrayList<AbstractRegle>();
		parent = nouvelle.parent;
	}
	
	public ArrayList<AbstractRegle> getSuivants(){
		return suivants;
	}
	
	@Override
	public void addSuivante(AbstractRegle suivante) {
		suivants.add(suivante);
		suivante.parent = this;
	}
	
	@Override
	public AbstractRegle trouverRegle(String primitive) {
		if (primitive.charAt(index) != valeur)
			return null;
		if (suivants.isEmpty())
			return this;
		for (int i = 0 ; i < suivants.size(); i++)
			if(suivants.get(i).trouverRegle(primitive) != null)
				return suivants.get(i).trouverRegle(primitive);
		return null;
	}
	
	@Override
	void Valider(Variante variante) {
		if(variante.GetValeur().charAt(index) == valeur) {
			AbstractRegle prochaine = null;
			for(int i = 0; i < suivants.size(); i++) {
				prochaine = suivants.get(i);
				prochaine.Valider(variante);
			}
		}
	}
}
