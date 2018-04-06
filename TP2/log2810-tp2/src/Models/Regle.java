package Models;

import java.util.ArrayList;

public class Regle extends AbstractRegle {
	private ArrayList<AbstractRegle> suivants;
	
	public Regle(char valeur) {
		super(valeur);
		suivants = new ArrayList<AbstractRegle>();
	}

	@Override
	void Valider(Variante variante) {
		// TODO Auto-generated method stub
		
	}
}
