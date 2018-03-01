/**
 * Classe Menu
 * 
 * Samuel D'Amours-Fortier, Justine Lambert et Julie Rousseau
 * LOG2810 : Structures discrètes
 * 
 * Définition du menu principal du programme 
 * 
 **/

import java.util.Scanner;
 
 
public class Menu {
	
	private Carte carte;
	private Scanner s = new Scanner(System.in);
	
	/**
	 * Constructeur
	 */
	public Menu() {
		carte = null;
	}
	
	
	/**
	 * M�thode MettreAJour
	 */
	public void MettreAJour() {
		
		System.out.println( "Entrez le nom du fichier desire:" );
		System.out.println();
		
		String nomFichier = s.nextLine();
		
		// Génère et affiche une nouvelle carte
		FabriqueCarte fabrique = new FabriqueCarte();
		carte = fabrique.CreerGraphe(nomFichier);
		carte.LireGraphe();
	}
	
	
	/**
	 * M�thode PlusCourtChemin
	 */
	public void PlusCourtChemin() {
		if(carte != null) {
		Vehicule vehicule = AfficherChoixVehicule();
		
		Ville villeA = AfficherSelectionVille("de depart: ");
		Ville villeB = AfficherSelectionVille("d'arrivee: ");
		
		carte.PlusCourtChemin(vehicule, villeA, villeB);
		} else {
			System.out.println("Veuillez ajouter une carte.");
		}
	}
	
	
	/**
	 * M�thode AfficherSelectionVille
	 * @param fin: Fin de la question
	 * @return la ville choisie
	 */
	private Ville AfficherSelectionVille(String fin) {
		System.out.println( "Entrez le numero d'ID de la ville "+fin );
		System.out.println();
		String choixEntre = s.nextLine();
		int choix = Integer.parseInt("0" + choixEntre.replaceAll("\\D+",""));
		Ville ville = carte.GetVilleById(choix);
		
		if(ville == null) {
			System.out.println("Erreur: choix invalide");
			System.out.println();
			AfficherSelectionVille(fin);
		}
		return ville;
	}
	
	
	/**
	 * M�thode AfficherChoixVehicule
	 * @return le vehicule choisi
	 */
	private Vehicule AfficherChoixVehicule() {
		System.out.println( "Quel est le type de vehicule utilise?" );
		System.out.println();
		System.out.println( "(1) Automobile" );
		System.out.println();
		System.out.println( "(2) Pick-up" );
		System.out.println();
		System.out.println( "(3) Fourgon" );
		System.out.println();
		System.out.println( "Votre choix: " );
		
		String choixEntre = s.nextLine();
		int choix = Integer.parseInt("0" + choixEntre.replaceAll("\\D+",""));
		
		// Au départ, on considère que l'essence est à 100% et on essaie avec CheapCar
		Compagnie cheapCar = new CheapCar();
	
		// Automobile est par défaut
		Vehicule vehicule = new Automobile(cheapCar, 100);
		
		switch (choix) {
			case 1:
				vehicule = new Automobile(cheapCar, 100);
				break;
			case 2:
				vehicule = new PickUp(cheapCar, 100);
				break;
			case 3:
				vehicule = new Fourgon(cheapCar, 100);
				break;
			default:
				System.out.println("Erreur: choix invalide");
				System.out.println();
				AfficherChoixVehicule();
		}
		return vehicule;
	}
	

	/**
	 * M�thode Afficher
	 */
	public void Afficher() {
		
		String choix = "abc";
		Boolean aQuitte = false;
		while (!aQuitte) {
			
			System.out.println( "Veuillez faire un choix parmi les options suivantes:" );
			System.out.println();
			System.out.println( "(a) Mettre a jour la carte" );
			System.out.println();
			System.out.println( "(b) Determiner le plus court chemin securitaire" );
			System.out.println();
			System.out.println( "(c) Quitter" );
			System.out.println();
			System.out.println( "Option souhaitee: " );
			System.out.println();
			
			choix = s.nextLine();
			
			switch (choix) {
				case "a":
					MettreAJour();
					break;
				case "b":
					PlusCourtChemin();
					break;
				case "c":
					// Quitte le programme
					System.out.println("Vous avez quitte le programme.");
					System.out.println();
					aQuitte = true;
					break;
				default:
					System.out.println("Erreur: choix invalide. SVP recommencer.");
			}
		}
	}		
}