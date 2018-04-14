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
		if (primitive.length() > index && primitive.charAt(index) != valeur)
			return null;
		if (suivants.isEmpty() || (primitive.length() == index+1 && primitive.charAt(index) == valeur))
			return this;
		if (primitive.length() == index)
			return null;
		AbstractRegle regle;
		for (int i = 0 ; i < suivants.size(); i++){
			regle = suivants.get(i).trouverRegle(primitive);
			if(regle != null)
				return regle;
		}
		return null;
	}
	
	@Override
	public void Valider(Variante variante) {
		if(index == 0 || variante.GetValeur().charAt(index -1) == valeur) {
			AbstractRegle prochaine = null;
			for(int i = 0; i < suivants.size(); i++) {
				prochaine = suivants.get(i);
				prochaine.Valider(variante);
			}
		}
	}
}
