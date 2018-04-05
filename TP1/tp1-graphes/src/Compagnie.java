/**
 * Classe abstraite Compagnie
 * 
 * Samuel D'Amours-Fortier, Justine Lambert et Julie Rousseau
 * LOG2810 : Structures discrètes
 * 
 * Permet de d�finir les propri�t�s d'une compagnie du syst�me.
 * 
 **/

public abstract class Compagnie {
	/**
	 * M�thode GetConsommationAutomobile 
	 * @return: retourne la valeur sur 100 de la consommation d'une automobile
	 **/
	abstract int GetConsommationAutomobile();
	/**
	 * M�thode GetConsommationPickup 
	 * @return: retourne la valeur sur 100 de la consommation d'un pick-up
	 **/
	abstract int GetConsommationPickup();
	/**
	 * M�thode GetConsommationFourgon 
	 * @return: retourne la valeur sur 100 de la consommation d'un fourgon
	 **/
	abstract int GetConsommationFourgon();
}