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
		 
		 Carte carte = new Carte();
		 
		 try (BufferedReader lecture = new BufferedReader(new FileReader(nomFichier))) {
			 	
			// Lit la liste des sommets
			int idVille;
			boolean aStationService;
			int stationService;
			String str;
			while ((str = lecture.readLine()).length() != 0) {
				
				// Conversion vers int
				String[] tmp = str.split(",");
				idVille = Integer.parseInt(tmp[0]);
				stationService = Integer.parseInt(tmp[1]);	
				// Conversion int à boolean
				aStationService = (stationService != 0);
				
				carte.AjouterVille(new Ville(idVille, aStationService));
			}
			
			// Lit la liste des arcs
			int sommet1;
			int sommet2;
			int tempsParcours;
			while ((str = lecture.readLine()) != null) {
				
				// Conversion vers int
				String[] tmp = str.split(",");
				sommet1 = Integer.parseInt(tmp[0]);
				sommet2 = Integer.parseInt(tmp[1]);
				tempsParcours = Integer.parseInt(tmp[2]);
				
				
			}
			
			// Réussite de la lecture
			System.out.println("La carte a ete mise a jour.");
			System.out.println();

		} catch (IOException e) {
			
			// Échec de la lecture
			System.out.println("Erreur: le fichier n'a pas été trouvé.");
			System.out.println();
		}

		 return carte;
	 }
 }