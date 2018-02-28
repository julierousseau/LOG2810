/**
 * Classe CheapCar
 * 
 * Permet de définir les propriétés de la compagnie CheapCar
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
