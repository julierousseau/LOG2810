/**
 * Classe Menu
 * 
 * Samuel D'Amours-Fortier, Justine Lambert et Julie Rousseau
 * LOG2810 : Structures discrètes
 * 
 * Définition du menu principal du programme 
 **/

import java.util.Scanner;
 
 
public class Menu {
	private Carte carte;
	
	// Constructeur
	public Menu() {
		carte = null;
	}
	
	
	// Permet de créer un nouveau graphe ou mettre à jour
	// un graphe existant
	public void MettreAJour() {
		
		System.out.println( "Entrez le nom du fichier desire:" );
		System.out.println();
		
		Scanner s = new Scanner(System.in);
		String nomFichier = s.next();
		// Gestion d'exceptions
		
		FabriqueCarte fabrique = new FabriqueCarte();
		carte = fabrique.CreerGraphe(nomFichier);
		carte.LireGraphe();
	}
	
	
	// Permet d'obtenir le plus court chemin à partir des
	// différents paramètres demandés
	public void PlusCourtChemin() {
		if(carte != null) {
		Vehicule vehicule = AfficherChoixVehicule();
		
		Ville villeA = AfficherSelectionVille("de depart: ");
		Ville villeB = AfficherSelectionVille("d'arrivee: ");
		
		carte.PlusCourtChemin(vehicule, villeA, villeB);
		}else {
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
		Scanner s = new Scanner(System.in);
		String choixEntre = s.nextLine();
		int choix = Integer.parseInt("0" + choixEntre.replaceAll("\\D+",""));
		
		// Gérer les cas d'erreurs et exceptions (mauvais type)
		// False - temporaire
		return new Ville(choix, false);
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
		
		Scanner s = new Scanner(System.in);
		String choixEntre = s.nextLine();
		int choix = Integer.parseInt("0" + choixEntre.replaceAll("\\D+",""));
		
		// Au départ, on considère que l'essence est à 100% et on essaie avec CheapCar
		Compagnie cheapCar = new CheapCar();
	
		// Je mets automobile par défaut
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
	
	
	// Affiche le menu dans la console et prend les 
	// entrées de l'utilisateur
	public int Afficher() {
		
		String choix = "abc";
		while (choix != "c") {
			
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
			
			Scanner s = new Scanner(System.in);
			choix = s.next();
			
			switch (choix) {
				case "a":
					MettreAJour();
					break;
				case "b":
					PlusCourtChemin();
					break;
				case "c":
					System.out.println("Vous avez quitte le programme.");
					System.out.println();
					return 0;
				default:
					System.out.println("Erreur: choix invalide. SVP recommencer.");
			}
		}

		return 0;
	}
		
}