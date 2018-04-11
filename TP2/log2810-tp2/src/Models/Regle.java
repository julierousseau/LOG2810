package Models;

import java.util.ArrayList;

public class Regle extends AbstractRegle {
	private ArrayList<AbstractRegle> suivants;
	
	public Regle(char valeur) {
		super(valeur);
		suivants = new ArrayList<AbstractRegle>();
	}

	public void addSuivante(char valeur) {
		suivants.add(new Regle(valeur));
	}
	public void addMotDePasse(char valeur) {
		suivants.add(new MotDePasse(valeur));
	}
	
	@Override
	void Valider(Variante variante) {
		if(variante.GetValeur().charAt(index) == valeur) {
			AbstractRegle prochaine = null;
			for(int i = 0; i < suivants.size(); i++) {
				prochaine = suivants.get(i);
				prochaine.index = this.index + 1;
				prochaine.Valider(variante);
			}
		}
	}
}
