package bikePickUp;

import dataStructures.*;

class UserClass implements User {

	private static final long serialVersionUID = 1L;

	private String id, mail, name, address;
	private long nif, phone;
	private Bike bike;
	private int balance;
	private int points;
	private boolean hasBike;
	private List<PickUp> pickups;
	private PickUp currentPickUp;

	public UserClass(String id, long nif, String mail, long phone, String name, String address) {
		this.id = id;
		this.nif = nif;
		this.mail = mail;
		this.phone = phone;
		this.name = name;
		bike = null;
		balance = 5;
		points = 0;
		this.address = address;
		this.hasBike = false;
		pickups = new DoublyLinkedList<PickUp>();
		currentPickUp = null;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public long getNif() {
		return nif;
	}

	@Override
	public String getMail() {
		return mail;
	}

	@Override
	public long getPhone() {
		return phone;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void addBike(Bike bike) {
		this.bike = bike;
		this.hasBike = true;

	}

	@Override
	public String getAddress() {
		return this.address;
	}

	@Override
	public boolean hasBike() {
		return this.hasBike;
	}

	@Override
	public int getBalance() {
		return balance;
	}

	@Override
	public int getPoints() {
		return points;
	}

	@Override
	public boolean hasBalanceForAride() {
		return balance >= 5;
	}

	@Override
	public void addPickUp(PickUp pu) {
		this.currentPickUp = pu;
		hasBike = true;

	}

	@Override
	public void finishPickUp(String finalIdPark, int duration, int delay, int value) {
		currentPickUp.setDelay(delay);
		currentPickUp.setFinalIdPark(finalIdPark);
		currentPickUp.setDuration(duration);
		currentPickUp.setValue(value);
		pickups.addLast(currentPickUp);
		currentPickUp = null;
		bike = null;
		hasBike = false;
	}

	@Override
	public void charge(int amount) {
		balance += amount;
	}

	@Override
	public Iterator<PickUp> getEndedPickups() {
		return pickups.iterator();
	}

	@Override
	public boolean equals(String idUser) {
		return this.getId().equals(idUser);
	}

	@Override
	public boolean hasPickUps() {
		return pickups.size() >= 1;
	}

	@Override
	public boolean firstPickUp() {
		return pickups.isEmpty() && bike != null;
	}

	@Override
	public PickUp getCurrentPickUp() {
		return this.currentPickUp;
	}

}
