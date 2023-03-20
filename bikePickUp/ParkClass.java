package bikePickUp;

import dataStructures.DoublyLinkedList;

class ParkClass implements Park {

	private static final long serialVersionUID = 1L;

	private String idPark, name, address;
	private Bike bike;
	private int counter;
	private DoublyLinkedList<PickUp> pickups;

	public ParkClass(String idPark, String name, String address) {
		this.idPark = idPark;
		this.name = name;
		bike = null;
		this.address = address;
		counter = 0;
		pickups = new DoublyLinkedList<PickUp>();
	}

	@Override
	public String getId() {
		return idPark;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getAddress() {
		return address;
	}

	/**
	 * Adiciona um objeto Bike ao parque
	 * 
	 * @param b
	 */
	@Override
	public void addBike(Bike b) {
		bike = b;
		counter++;
	}

	/**
	 * Remove uma bicicleta com id idBike do parque
	 * 
	 * @param idBike
	 */
	@Override
	public void removeBike(String idBike) {
		bike = null;
		counter--;
	}

	@Override
	public int getNbikes() {
		return counter;
	}

	@Override
	public boolean hasBike(String idBike) {
		if (bike == null)
			return false;
		else
			return bike.getIdBike().equalsIgnoreCase(idBike);

	}

	@Override
	public int getNumberOfPickUps() {
		return pickups.size();
	}

	/**
	 * Adiciona um objeto pickUp ja finalizado a um parque
	 * 
	 * @param pickup
	 */
	@Override
	public void addPickup(PickUp pickup) {
		pickups.addLast(pickup);
	}

}
