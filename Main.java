import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import bikePickUp.BikePickUp;
import bikePickUp.BikePickUpClass;
import bikePickUp.ParkGets;
import bikePickUp.PickUp;
import bikePickUp.PickUpGets;
import bikePickUp.UserGets;
import dataStructures.Iterator;
import exceptions.*;

public class Main {

	private static final String INVALID_COMMAND = "Comando invalido.";
	// mensagens de output
	private static final String USER_ALREADY_EXISTS = "Utilizador existente.";
	private static final String USER_ADDED = "Insercao de utilizador com sucesso.";
	private static final String USER_USED_SYSTEM = "Utilizador ja utilizou o sistema.";
	private static final String USER_REMOVED = "Utilizador removido com sucesso.";
	private static final String PARK_ALREADY_EXISTS = "Parque existente.";
	private static final String PARK_ADDED = "Parque adicionado com sucesso.";
	private static final String BIKE_STOPPED = "Bicicleta parada.";
	private static final String INSUFFICIENT_FUNDS = "Saldo insuficiente.";
	private static final String BIKE_IN_MOVEMENT = "Bicicleta em movimento.";
	private static final String SUCCESSFULL_PICKUP = "PickUp com sucesso.";
	private static final String INVALID_DATA = "Dados invalidos.";
	private static final String BIKE_NOT_PARKED = "Bicicleta nao esta em parque.";
	private static final String BIKE_PARKED = "Bicicleta estacionada no parque.";
	private static final String NO_PARKS = "Nao ha parques no sistema.";
	private static final String NO_PICKUPS = "Nao foram efetuados pickups.";
	private static final String INEXISTENT_PARK = "Parque inexistente.";
	private static final String BIKE_ALREADY_EXISTS = "Bicicleta existente.";
	private static final String BIKE_ADDED = "Bicicleta adicionada com sucesso.";
	private static final String BIKE_ALREADY_USED = "Bicicleta ja foi utilizada.";
	private static final String BIKE_REMOVED = "Bicicleta removida com sucesso.";
	private static final String FIRST_BIKE_PICKUP = "Bicicleta em movimento em primeiro pickup.";
	private static final String BIKE_NOT_USED = "Bicicleta nao foi utilizada.";
	private static final String FIRST_USER_PICKUP = "Utilizador em primeiro PickUp.";
	private static final String NO_PICKUP_BY_USER = "Utilizador nao utilizou o sistema.";
	private static final String INEXISTENT_USER = "Utilizador inexistente.";
	private static final String NO_DELAYS = "Nao se registaram atrasos.";
	private static final String INEXISTENT_BICYCLE = "Bicicleta inexistente.";

	// constantes
	private static final String FILE_NAME = "stored_bike_pick_upp.txt";
	private static final int DEFAULT = 0;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		BikePickUp bpu = load();
		Commands command = getCommand(in);
		while (command != Commands.XS) {

			readCommand(command, in, bpu);
			command = getCommand(in);
		}
		save(bpu);
		System.out.println(command.getDescription());
		System.out.println();
	}

	// Carrega os dados do programa de um ficheiro para o proprio programa
	private static BikePickUp load() {
		try {
			ObjectInputStream file = new ObjectInputStream(new FileInputStream(FILE_NAME));
			BikePickUp bpu = (BikePickUp) file.readObject();
			file.close();
			return bpu;
		} catch (IOException e) {
			return new BikePickUpClass();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	// Carrega os dados do programa num ficheiro
	private static void save(BikePickUp bpu) {
		try {
			ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
			file.writeObject(bpu);
			file.flush();
			file.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	private static Commands getCommand(Scanner in) {
		try {
			return Commands.valueOf(in.next().toUpperCase());
		} catch (IllegalArgumentException e) {
			return Commands.UKNOWN;
		}
	}

	// Verifica qual foi o comando a ser chamado
	private static void readCommand(Commands command, Scanner in, BikePickUp bpu) {

		switch (command) {
		case FAVORITEPARKS:
			favoriteParks(in, bpu);
			break;
		case ADDPARK:
			addPark(in, bpu);
			break;
		case REMOVEBIKE:
			removeBike(in, bpu);
			break;
		case REMOVEUSER:
			removeUser(in, bpu);
			break;
		case GETPARKINFO:
			infoPark(in, bpu);
			break;
		case PICKUP:
			startPickUp(in, bpu);
			break;
		case PICKDOWN:
			pickDown(in, bpu);
			break;
		case BIKEPICKUPS:
			bikePickUps(in, bpu);
			break;
		case PARKEDBIKE:
			parkedBike(in, bpu);
			break;
		case CHARGEUSER:
			chargeUser(in, bpu);
			break;
		case LISTDELAYED:
			listDelayed(in, bpu);
			break;
		case USERPICKUPS:
			userPickUps(in, bpu);
			break;
		case ADDUSER:
			addUser(in, bpu);
			break;
		case ADDBIKE:
			addBike(in, bpu);
			break;
		case GETUSERINFO:
			userInfo(in, bpu);
			break;
		default:
			System.out.println(INVALID_COMMAND);
			break;
		}
		System.out.println();

	}

	// Lista de atrasos
	private static void listDelayed(Scanner in, BikePickUp bpu) {
		try {
			System.out.println(NO_DELAYS);
		} catch (NoDelaysException e) {
			System.out.println(NO_DELAYS);
		}
	}

	// Metodo lista todos os PickUps de um utilizador
	private static void userPickUps(Scanner in, BikePickUp bpu) {
		try {
			String idUser = in.nextLine().trim();
			Iterator<PickUp> it = bpu.getUserPickUps(idUser);
			PickUpGets pu = null;
			it.rewind();
			while (it.hasNext()) {
				pu = it.next();
				System.out.println(pu.getId() + " " + pu.getInitialIdPark() + " " + pu.getFinalIdPark() + " "
						+ pu.getDuration() + " " + pu.getDelay() + " " + pu.getValue());
			}
		} catch (NoUserException e) {
			System.out.println(INEXISTENT_USER);
		} catch (NoPickUpsException e) {
			System.out.println(NO_PICKUP_BY_USER);
		} catch (FirstPickUpException e) {
			System.out.println(FIRST_USER_PICKUP);
		}
	}

	// Metodo lista todos os PickUps de uma bicicleta
	private static void bikePickUps(Scanner in, BikePickUp bpu) {
		try {
			String idBike = in.nextLine().trim();
			Iterator<PickUp> it = bpu.getBikePickUps(idBike);

			PickUpGets pu = null;
			while (it.hasNext()) {
				pu = it.next();
				System.out.println(pu.getId() + " " + pu.getInitialIdPark() + " " + pu.getFinalIdPark() + " "
						+ pu.getDuration() + " " + pu.getDelay() + " " + pu.getValue());
			}

		} catch (NoBikeException e) {
			System.out.println(INEXISTENT_BICYCLE);
		} catch (NoPickUpsException e) {
			System.out.println(BIKE_NOT_USED);
		} catch (FirstPickUpException e) {
			System.out.println(FIRST_BIKE_PICKUP);
		}
	}

	// Metodo acede a todos os dados de um utilizador
	private static void userInfo(Scanner in, BikePickUp bpu) {
		try {
			String idUser = in.nextLine().trim();
			UserGets user = bpu.getUserInfo(idUser);
			System.out.println(user.getName() + ": " + user.getNif() + ", " + user.getAddress() + ", " + user.getMail()
					+ ", " + user.getPhone() + ", " + user.getBalance() + ", " + user.getPoints());
		} catch (NoUserException e) {
			System.out.println(INEXISTENT_USER);
		}
	}

	// Metodo remove a bicicleta do sistema dado o seu id
	private static void removeBike(Scanner in, BikePickUp bpu) {
		try {

			String idBike = in.nextLine().trim();
			bpu.removeBike(idBike);
			System.out.println(BIKE_REMOVED);

		} catch (NoBikeException e) {
			System.out.println(INEXISTENT_BICYCLE);
		} catch (BikeBeingUsedException e) {
			System.out.println(BIKE_ALREADY_USED);
		}
	}

	// Metodo adiciona uma bicicleta ao sistema dados o seu id, o id do parque onde
	// esta vai pertencer inicialmente e por fim a sua matricula
	private static void addBike(Scanner in, BikePickUp bpu) {
		try {
			String idBike = in.next().trim();
			String idPark = in.next().trim();
			String plate = in.nextLine().trim();
			bpu.addBike(idBike, idPark, plate);
			System.out.println(BIKE_ADDED);
		} catch (BikeAlreadyExistsException e) {
			System.out.println(BIKE_ALREADY_EXISTS);
		} catch (NoParkException e) {
			System.out.println(INEXISTENT_PARK);
		}
	}

	// Metodo lista os dados dos parques com mais PickUps
	private static void favoriteParks(Scanner in, BikePickUp bpu) {
		try {
			ParkGets favorite = bpu.getFavoritePark();
			System.out
					.println(favorite.getName() + ": " + favorite.getAddress() + ", " + favorite.getNumberOfPickUps());
		} catch (NoParkException e) {
			System.out.println(NO_PARKS);
		} catch (NoPickUpsException e) {
			System.out.println(NO_PICKUPS);
		}
	}

	// Metodo verifica se uma bicicleta idBike esta num parque idPark
	private static void parkedBike(Scanner in, BikePickUp bpu) {
		try {
			String idBike = in.next().trim();
			String idPark = in.next().trim();
			bpu.hasBike(idBike, idPark);
			System.out.println(BIKE_PARKED);
		} catch (NoBikeException e) {
			System.out.println(INEXISTENT_BICYCLE);
		} catch (NoParkException e) {
			System.out.println(INEXISTENT_PARK);
		} catch (BikeBeingUsedException e) {
			System.out.println(BIKE_NOT_PARKED);
		}
	}

	// Metodo carrega o saldo de um utilizador com id idUser com montante amount
	// dados no input
	private static void chargeUser(Scanner in, BikePickUp bpu) {
		try {
			String idUser = in.next().trim();
			int amount = in.nextInt();
			in.nextLine();
			int balance = bpu.chargeUser(idUser, amount);
			System.out.println("Saldo: " + balance + " euros");

		} catch (NoUserException e) {
			System.out.println(INEXISTENT_USER);
		} catch (InvalidDataException e) {
			System.out.println(INVALID_DATA);
		}
	}

	// Metodo inicia um pickUp de uma bicicleta com id idBike para um utilizador com
	// id idUser
	private static void startPickUp(Scanner in, BikePickUp bpu) {
		try {

			String idBike = in.next().trim();
			String idUser = in.nextLine().trim();
			bpu.addBikeToUser(idBike, idUser);

			System.out.println(SUCCESSFULL_PICKUP);
		} catch (NoBikeException e) {
			System.out.println(INEXISTENT_BICYCLE);
		} catch (BikeBeingUsedException e) {
			System.out.println(BIKE_IN_MOVEMENT);
		} catch (NoUserException e) {
			System.out.println(INEXISTENT_USER);
		} catch (HasBikeException e) {
			System.out.println("Utilizador em movimento.");
		} catch (NoBalanceException e) {
			System.out.println(INSUFFICIENT_FUNDS);
		}
	}

	// Metodo estaciona uma bicicleta com id idBike num parque com id idPark
	private static void pickDown(Scanner in, BikePickUp bpu) {
		try {

			String idBike = in.next().trim();
			String idPark = in.next().trim();
			int minutes = in.nextInt();
			in.nextLine();
			int[] aux = bpu.pickDown(idBike, idPark, minutes);

			System.out.println("Pickdown com sucesso: " + aux[DEFAULT] + " euros, " + aux[1] + " pontos");

		} catch (NoBikeException e) {
			System.out.println(INEXISTENT_BICYCLE);
		} catch (BikeParkedException e) {
			System.out.println(BIKE_STOPPED);
		} catch (NoParkException e) {
			System.out.println(INEXISTENT_PARK);
		} catch (InvalidDataException e) {
			System.out.println(INVALID_DATA);
		}
	}

	// Metodo acede a informacao de um parque com id idPark
	private static void infoPark(Scanner in, BikePickUp bpu) {
		try {
			String idPark = in.nextLine().trim();
			ParkGets park = bpu.getParkInfo(idPark);
			System.out.println(park.getName() + ": " + park.getAddress() + ", " + park.getNbikes());

		} catch (NoParkException e) {
			System.out.println(INEXISTENT_PARK);
		}
	}

	// Metodo adiciona um parque ao sistema com atributos como o seu id, nome e
	// morada
	private static void addPark(Scanner in, BikePickUp bpu) {
		try {
			String idPark = in.next().trim();
			String name = in.nextLine().trim();
			String address = in.nextLine();
			bpu.addPark(idPark, name, address);
			System.out.println(PARK_ADDED);

		} catch (ParkAlreadyExistsException e) {
			System.out.println(PARK_ALREADY_EXISTS);
		}
	}

	// Metodo remove um utilizador com id idUser do sistema
	private static void removeUser(Scanner in, BikePickUp bpu) {
		try {
			String idUser = in.next().trim();
			bpu.removeUser(idUser);
			System.out.println(USER_REMOVED);

		} catch (HasBikeException e) {
			System.out.println(USER_USED_SYSTEM);
		} catch (NoUserException e) {
			System.out.println(INEXISTENT_USER);
		}

	}

	// Metodo adiciona um utilizador ao sistema com atributos como id, nif, mail,
	// phone, name
	private static void addUser(Scanner in, BikePickUp bpu) {
		try {
			String id = in.next().trim();
			long nif = in.nextLong();
			String mail = in.next().trim();
			long phone = in.nextLong();
			String name = in.nextLine().trim();

			String address = in.nextLine();
			bpu.addUser(id, nif, mail, phone, name, address);
			System.out.println(USER_ADDED);
		} catch (UserAlreadyExistsException e) {
			System.out.println(USER_ALREADY_EXISTS);
		}

	}

}
