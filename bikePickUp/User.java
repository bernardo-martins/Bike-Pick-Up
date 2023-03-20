package bikePickUp;

import dataStructures.Iterator;

public interface User extends UserGets {
	/**
	 * Adiciona uma bicicle ao utilizador
	 * 
	 * @param bike
	 */
	void addBike(Bike bike);

	/**
	 * Adiciona um PickUp ao utilizador
	 * 
	 * @param pu
	 */
	void addPickUp(PickUp pu);

	/**
	 * Termina o PickUp atual
	 * 
	 * @param finalIdPark
	 * @param duration
	 * @param delay
	 * @param value
	 */
	void finishPickUp(String finalIdPark, int duration, int delay, int value);

	/**
	 * Cobra ao utilizador o amount
	 * 
	 * @param amount
	 */
	void charge(int amount);

	/**
	 * Lista os PickUps efetuados pelo utilizador
	 * 
	 * @return
	 */
	Iterator<PickUp> getEndedPickups();

}
