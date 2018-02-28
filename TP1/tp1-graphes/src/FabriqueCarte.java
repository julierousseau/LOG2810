/**
 * Classe FabriqueCarte
 * 
 * Samuel D'Amours-Fortier, Justine Lambert et Julie Rousseau
 * LOG2810 : Structures discrètes
 * 
 * Permet de générer le graphe représentant la carte
 **/
 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


 public class FabriqueCarte {
	 
	 // Constructeur
	 public FabriqueCarte() {
			
   	}
	 
	 // Lit le fichier afin de générer le graphe
	 public Carte CreerGraphe(String nomFichier) {
		
		 // Work in progress!! 
		 
		 Carte carte = new Carte();
		 
		 try (BufferedReader lecture = new BufferedReader(new FileReader(nomFichier))) {
			 	
			// Lit la liste des sommets
			int idVille;
			boolean aStationService;
			int stationService;
			
			while (lecture.read() != -1) {
				idVille = lecture.read();
				stationService = lecture.read();
				// TEST LECTURE
				System.out.println(idVille + "," + stationService);
				// Conversion int à boolean
				aStationService = (stationService != 0);
				carte.AjouterVille(new Ville(idVille, aStationService));
			}
			
			// Lit la liste des arcs
			
			
			// Réussite de la lecture
			System.out.println("La carte a ete mise a jour.");
			System.out.println();

		} catch (IOException e) {
			System.out.println("Erreur: le fichier n'a pas été trouvé.");
			System.out.println();
		}

		 return carte;
	 }
 }