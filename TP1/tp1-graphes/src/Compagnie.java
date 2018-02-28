/**
 * Classe abstraite Compagnie
 * 
 * Permet de définir les propriétés d'une compagnie du système.
 * 
 **/
public abstract class Compagnie {
	/**
	 * Méthode GetConsommationAutomobile 
	 * @return: retourne la valeur sur 100 de la consommation d'une automobile
	 **/
	abstract int GetConsommationAutomobile();
	/**
	 * Méthode GetConsommationPickup 
	 * @return: retourne la valeur sur 100 de la consommation d'un pick-up
	 **/
	abstract int GetConsommationPickup();
	/**
	 * Méthode GetConsommationFourgon 
	 * @return: retourne la valeur sur 100 de la consommation d'un fourgon
	 **/
	abstract int GetConsommationFourgon();
}