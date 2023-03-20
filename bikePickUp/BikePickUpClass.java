package bikePickUp;

import dataStructures.ChainedHashTable;
import dataStructures.Dictionary;
import dataStructures.Iterator;
import exceptions.*;

public class BikePickUpClass implements BikePickUp {

	private static final long serialVersionUID = 1L;
	private Dictionary<String, User> users;
	private Dictionary<String, Bike> bikes;
	private Park park;

	public BikePickUpClass() {
		users = new ChainedHashTable<String, User>();
		bikes = new ChainedHashTable<String, Bike>();
		park = null;
	}

	@Override
	public void addUser(String id, long nif, String mail, long phone, String name, String address)
			throws UserAlreadyExistsException {
		if (users.find(id.toLowerCase()) != null)
			throw new UserAlreadyExistsException();

		users.insert(id, new UserClass(id, nif, mail, phone, name, address));

	}

	@Override
	public void removeUser(String idUser) throws NoUserException, HasBikeException {
		User user = users.find(idUser.toLowerCase());
		if (user == null)
			throw new NoUserException();
		else if (user.hasPickUps())
			throw new HasBikeException();
		users.remove(idUser);
	}

	@Override
	public void addPark(String idPark, String name, String address) throws ParkAlreadyExistsException {
		if (park != null)
			throw new ParkAlreadyExistsException();
		park = new ParkClass(idPark, name, address);
	}

	@Override
	public void addBike(String idBike, String idPark, String plate) throws BikeAlreadyExistsException, NoParkException {
		if (((ParkGets) park).hasBike(idBike))
			throw new BikeAlreadyExistsException();
		else if (park == null || !((ParkGets) park).getId().equalsIgnoreCase(idPark))
			throw new NoParkException();
		Bike bike = new BikeClass(idBike, idPark, plate);
		park.addBike(bike);
		bikes.insert(idBike, bike);
	}

	@Override
	public void removeBike(String idBike) throws NoBikeException, BikeBeingUsedException {
		Bike bike = bikes.find(idBike.toLowerCase());

		if (bike == null)
			throw new NoBikeException();
		else if (((BikeGets) bike).hasPickUps() || ((BikeGets) bike).isOccupied())
			throw new BikeBeingUsedException();
		park.removeBike(idBike);

		bikes.remove(idBike.toLowerCase());

	}

	@Override
	public UserGets getUserInfo(String idUser) throws NoUserException {
		UserGets user = users.find(idUser.toLowerCase());
		if (user == null)
			throw new NoUserException();
		return user;
	}

	@Override
	public ParkGets getParkInfo(String idPark) {
		if (park == null || !((ParkGets) park).getId().equalsIgnoreCase(idPark))
			throw new NoParkException();
		return (ParkGets) park;
	}

	@Override
	public void addBikeToUser(String idBike, String idUser) {
		Bike bike = bikes.find(idBike.toLowerCase());
		User user = users.find(idUser.toLowerCase());
		if (bike == null || !((BikeGets) bike).getIdBike().equalsIgnoreCase(idBike))
			throw new NoBikeException();
		else if (((BikeGets) bike).isOccupied())
			throw new BikeBeingUsedException();
		else if (user == null || !((UserGets) user).getId().equalsIgnoreCase(idUser))
			throw new NoUserException();
		else if (user.hasBike())
			throw new HasBikeException();
		else if (!((UserGets) user).hasBalanceForAride())
			throw new NoBalanceException();
		PickUp pickupUser = new PickUpClass(idBike, bike.getIdPark());
		PickUp pickupBike = new PickUpClass(idUser, bike.getIdPark());
		user.addPickUp(pickupUser);
		bike.addPickUp(pickupBike);
		user.addBike(bike);
		bike.addUser(user);
		park.removeBike(idBike);
		park.addPickup(user.getCurrentPickUp());// CUIDADOOOOOOOOOOOOOOOOOOOOOOOOOO

	}

	@Override
	public int[] pickDown(String idBike, String idPark, int minutes) {
		Bike bike = bikes.find(idBike.toLowerCase());

		if (bike == null)
			throw new NoBikeException();
		else if (!((BikeGets) bike).isOccupied())
			throw new BikeParkedException();
		else if (park == null || !((ParkGets) park).getId().equalsIgnoreCase(idPark))
			throw new NoParkException();
		else if (minutes <= 0)
			throw new InvalidDataException();
		int delay = 0;
		int value = 0;
		if (minutes > 60) {
			delay = minutes - 60;
			value = (delay / 30) + 1;
		}
		User user = users.find(bike.getCurrentUser());
		int[] aux = new int[2];
		aux[0] = user.getBalance();
		aux[1] = user.getPoints();

		bike.finishPickUp(idPark, minutes, delay, value);
		user.finishPickUp(idPark, minutes, delay, value);
		park.addBike(bike);

		return aux;

	}

	@Override
	public int chargeUser(String idUser, int amount) throws InvalidDataException {
		User user = users.find(idUser.toLowerCase());
		if (user == null)
			throw new NoUserException();
		else if (amount <= 0)
			throw new InvalidDataException();
		user.charge(amount);
		return user.getBalance();

	}

	@Override
	public void hasBike(String idBike, String idPark) {
		Bike bike = bikes.find(idBike.toLowerCase());
		if (bike == null)
			throw new NoBikeException();
		if (park == null || !((ParkGets) park).getId().equalsIgnoreCase(idPark))
			throw new NoParkException();
		if (bike.isOccupied())
			throw new BikeBeingUsedException();

	}

	@Override
	public Iterator<PickUp> getUserPickUps(String idUser)
			throws NoUserException, NoPickUpsException, FirstPickUpException {
		User user = users.find(idUser.toLowerCase());
		if (user == null)
			throw new NoUserException();
		else if (((UserGets) user).firstPickUp())
			throw new FirstPickUpException();
		else if (!((UserGets) user).hasPickUps())
			throw new NoPickUpsException();

		return user.getEndedPickups();
	}

	@Override
	public Iterator<PickUp> getBikePickUps(String idBike)
			throws NoBikeException, NoPickUpsException, FirstPickUpException {
		Bike bike = bikes.find(idBike.toLowerCase());
		if (bike == null)
			throw new NoBikeException();
		else if (((BikeGets) bike).firstPickUp())
			throw new FirstPickUpException();
		else if (!((BikeGets) bike).hasPickUps())
			throw new NoPickUpsException();

		return bike.getEndedPickUps();
	}

	@Override
	public ParkGets getFavoritePark() throws NoParkException, NoPickUpsException {
		if (park == null)
			throw new NoParkException();
		else if (park.getNumberOfPickUps() == 0)
			throw new NoPickUpsException();
		return park;
	}
}
