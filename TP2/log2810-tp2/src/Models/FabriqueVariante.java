package Models;
 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


 public class FabriqueVariante {
	 
	 public ArrayList<Variante> TraiterLesEntrees(String nomFichier) {
		 
		 ArrayList<Variante> variantes = new ArrayList<Variante>();
			
		 try (BufferedReader lecture = new BufferedReader(new FileReader(nomFichier))) {
			 	
			// On lit la première ligne pour obtenir l'id de l'automate
			String idAutomate = lecture.readLine();
			
			// On lit les variantes et on les ajoute à la liste
			String str;
			while ((str = lecture.readLine()) != null) {
				variantes.add(new Variante(idAutomate, str)); 
			}
			
			// Réussite de la lecture
			System.out.println("Les entrees ont ete traitees.");
			System.out.println();

		} catch (IOException e) {
			
			// Échec de la lecture
			System.out.println("Erreur: le fichier n'a pas ete trouve.");
			System.out.println();
			variantes = null;
		}

		 return variantes;
	 }
		 
 }