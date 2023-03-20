package bikePickUp;


public interface Park extends ParkGets {

	/**
	 * Adiciona um objeto Bike ao parque
	 * 
	 * @param b
	 */
	void addBike(Bike b);

	/**
	 * Adiciona um objeto pickUp ja finalizado a um parque
	 * 
	 * @param pickup
	 */
	void addPickup(PickUp pickup);

	/**
	 * Remove uma bicicleta com id idBike do parque
	 * 
	 * @param idBike
	 */
	void removeBike(String idBike);

}
