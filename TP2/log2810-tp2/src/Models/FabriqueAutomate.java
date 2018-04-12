package Models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FabriqueAutomate {
	 
	 public Automate creerAutomate(String nomFichier) {
		 Automate automate = new Automate();
		 try (BufferedReader lecture = new BufferedReader(new FileReader(nomFichier))) {
			 	
				// On lit la premiere ligne pour obtenir l'id de l'automate
				String idAutomate = lecture.readLine();
				
				// On cree la premiere regle
				String str = lecture.readLine().split("=")[1];
				Regle S = new Regle(str.charAt(1), 0);
				
				// On lit les regle suivantes
				String [] tmp;
				while ((str = lecture.readLine()) != null) {
					tmp = str.split("=");
					AbstractRegle r = S.trouverRegle(tmp[0].substring(1));
					r.addSuivante(new MotDePasse(tmp[1].charAt(tmp[1].length()-1), r.index + 1));
				}
				
				// On cree l'automate
				automate = new Automate(idAutomate, S);
				
				// Reussite de la lecture
				System.out.println("L'automate a ete cree.");
				System.out.println();

			} catch (IOException e) {
				// Echec de la lecture
				System.out.println("Erreur: le fichier n'a pas ete trouve.");
				System.out.println();
				automate = null;
			}
		 return automate;
	 }
	 
}