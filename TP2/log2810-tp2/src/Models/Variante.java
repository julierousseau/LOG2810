package Models;

public class Variante {
	
	private String valeur;
	private boolean estValide;
	private String idAutomate;
	
	public Variante(String idAutomate, String valeur) {
		 estValide = false;
		 this.idAutomate = idAutomate;
		 this.valeur = valeur;
	}
	
	public String GetValeur() {
		return valeur;
	}
	
	public boolean GetValide() {
		 return estValide;
	}
	 
	public String GetIdAutomate() {
		 return idAutomate;
	}
	
	public void SetValide(boolean valide) {
		this.estValide = valide;
	}
}
