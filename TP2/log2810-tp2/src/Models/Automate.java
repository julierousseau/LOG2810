package Models;

import java.util.ArrayList;

public class Automate{
	
	private String id;
	private Regle premiereRegle;
	
	public Automate(String id, Regle premiereRegle) {
		this.id = id;
		this.premiereRegle = premiereRegle;
	}
	
	public String getId() {
		return id;
	}
	
	public void comparer(ArrayList<Variante> variantes) {
		for (Variante v: variantes)
			premiereRegle.Valider(v);
	}
	
}