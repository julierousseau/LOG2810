/**
 * Classe SuperCar
 * 
 * Samuel D'Amours-Fortier, Justine Lambert et Julie Rousseau
 * LOG2810 : Structures discrètes
 * 
 * Permet de d�finir les propri�t�s de la compagnie SuperCar
 * 
 **/

public class SuperCar extends Compagnie {

	@Override
	int GetConsommationAutomobile() {
		return 3;
	}

	@Override
	int GetConsommationPickup() {
		return 4;
	}

	@Override
	int GetConsommationFourgon() {
		return 6;
	}

}
