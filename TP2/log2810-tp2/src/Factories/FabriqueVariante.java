package Factories;
 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Models.Variante;


 public class FabriqueVariante {
	 
	 /**
	  * Methode TraiterLesEntrees
	 */
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
			System.out.println("Les variantes ont ete lues.");
			System.out.println();

		}catch (IOException e) {
				// Echec de la lecture
				System.out.println("Erreur: Le fichier n'a pas pu �tre lu.");
				System.out.println();
				variantes = null;
		}catch (Exception e) {
			// Echec de l'interpretation
			System.out.println("Erreur: fichier invalide.");
			System.out.println();
			variantes = null;
		}

		 return variantes;
	 }
		 
}