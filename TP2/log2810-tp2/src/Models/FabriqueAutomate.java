package Models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FabriqueAutomate {
	 
	 public Regle creerAutomate(String nomFichier) {
		 
		 try (BufferedReader lecture = new BufferedReader(new FileReader(nomFichier))) {
			 	
				// On lit la premiere ligne pour obtenir l'id de l'automate
				String idAutomate = lecture.readLine();
				
				//On cree la premiere regle
				String str = lecture.readLine().split("=")[1];
				Regle S = new Regle(str.charAt(1));
				
				// On lit les regle suivantes
				String [] tmp;
				while ((str = lecture.readLine()) != null) {
					tmp = str.split("=");
					
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
		 
	 }
	 
}