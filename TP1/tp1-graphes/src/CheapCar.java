/**
 * Classe CheapCar
 * 
 * Permet de d�finir les propri�t�s de la compagnie CheapCar
 * 
 **/
public class CheapCar extends Compagnie {

	@Override
	int GetConsommationAutomobile() {
		return 5;
	}

	@Override
	int GetConsommationPickup() {
		return 7;
	}

	@Override
	int GetConsommationFourgon() {
		return 8;
	}

}
