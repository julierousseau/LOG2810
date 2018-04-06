package UI;

import java.util.Scanner;
import java.util.ArrayList;
import Models.Variante;
import Models.FabriqueVariante;
import Models.Automate;
import Models.FabriqueAutomate;

public class Menu {

	private ArrayList<Automate> automates;
	private ArrayList<Variante> variantes;
	private Scanner s = new Scanner(System.in);
	
	/**
	 * Constructeur
	 */
	public Menu() {
		automates = new ArrayList<Automate>();
		variantes = new ArrayList<Variante>();
	}
	
	
	/**
	 * M?thode CreerAutomate
	 */
	private void CreerAutomate() {
		
		System.out.println( "Entrez le nom du fichier de regles desire:" );
		System.out.println();

		String nomFichier = s.nextLine();
		
		FabriqueAutomate fabrique = new FabriqueAutomate();
		
		// Cr�e et ajoute l'automate � la liste
		Automate nouvelAutomate = fabrique.CreerAutomate(nomFichier);
		automates.add(automate);
	}
	
	
	/**
	 * M?thode TrouverMotDePasse
	 */
	private String TrouverMotDePasse(ArrayList<Variante> variantes, Automate automate) {
		
		String motDePasseTrouve = "coucou";
		return motDePasseTrouve;
		
		// TO DO
		
	}
	
	
	/**
	 * M?thode TraiterRequetes
	 */
	private void TraiterRequetes() {
		
		System.out.println( "Entrez le nom du fichier de variantes desire:" );
		System.out.println();

		String nomFichier = s.nextLine();
		
		FabriqueVariante fabrique = new FabriqueVariante();
		
		// Obtient les variantes trait�es
		ArrayList<Variante> variantesTraitees = fabrique.TraiterLesEntrees(nomFichier);
		
		// Les ajoute � la liste
		for ( Variante v : variantesTraitees )
			variantes.add(v);
	
		// Trouve l'automate � utiliser
		String idAutomateCherche = variantesTraitees.get(0).GetIdAutomate();
		Automate automateAssocie = null;
		for ( Automate aut : automates) {
			if (idAutomateCherche == aut.GetId())
				automateAssocie = aut;
		}
		
		// Recherche un mot de passe parmi les variantes trait�es
		String motDePasseTrouve = TrouverMotDePasse(variantesTraitees, automateAssocie);
		
		// Affiche le mot de passe trouv�
		System.out.println( "Resultat de la requete: " );
		System.out.println( "Le mot de passe " + motDePasseTrouve + " a ete trouve." );
		System.out.println();
	}
	
	
	/**
	 * M?thode AfficherMotsDePasse
	 */
	private void AfficherMotsDePasse() {
		
		System.out.println( "Mots de passe valides obtenus jusqu'� pr�sent: " );
		System.out.println();
		
		for (Variante v : variantes) {
			if (v.GetValide())
				System.out.println(v.GetValeur() + " avec l'automate " + v.GetIdAutomate());
		}
	}
	
	
	/**
	 * M?thode Afficher
	 */
	public void Afficher() {
		
		String choix = "abc";
		Boolean aQuitte = false;
		while (!aQuitte) {
			
			System.out.println( "Veuillez faire un choix parmi les options suivantes:" );
			System.out.println();
			System.out.println( "(a) Creer l'automate" );
			System.out.println();
			System.out.println( "(b) Traiter des requetes" );
			System.out.println();
			System.out.println( "(c) Afficher les mots de passe valides obtenus" );
			System.out.println();
			System.out.println( "(d) Quitter" );
			System.out.println();
			System.out.println( "Option souhaitee: " );
			System.out.println();
			
			choix = s.nextLine();
			
			switch (choix) {
				case "a":
					CreerAutomate();
					break;
				case "b":
					TraiterRequetes();
					break;
				case "c":
					AfficherMotsDePasse(); 
					break;
				case "d":
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
