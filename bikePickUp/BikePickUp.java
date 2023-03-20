package bikePickUp;

import java.io.Serializable;

import dataStructures.Iterator;
import exceptions.*;

public interface BikePickUp extends Serializable {

	/**
	 * Adiciona um utilizador a aplicacao chamando o construtor da classe UserClass
	 * 
	 * @param id
	 * @param nif
	 * @param mail
	 * @param phone
	 * @param name
	 * @param address
	 * @throws UserAlreadyExistsException
	 */
	void addUser(String id, long nif, String mail, long phone, String name, String address)
			throws UserAlreadyExistsException;

	/**
	 * Remove o utilizador com id idUser da aplicacao
	 * 
	 * @param idUser
	 * @throws NoUserException
	 * @throws HasBikeException
	 */
	void removeUser(String idUser) throws NoUserException, HasBikeException;

	/**
	 * Adiciona o parque com os parametros seguintes chamando o construtor da classe
	 * ParkClass
	 * 
	 * @param idPark
	 * @param name
	 * @param address
	 * @throws ParkAlreadyExistsException
	 */
	void addPark(String idPark, String name, String address) throws ParkAlreadyExistsException;

	/**
	 * Adiciona a bicicleta com os parametros seguintes chamando o construtor da
	 * classe BikeClass
	 * 
	 * @param idBike
	 * @param idPark
	 * @param plate
	 * @throws BikeAlreadyExistsException
	 * @throws NoParkException
	 */
	void addBike(String idBike, String idPark, String plate) throws BikeAlreadyExistsException, NoParkException;

	/**
	 * Remove a bicicleta com id idBike da aplicacao
	 * 
	 * @param idBike
	 * @throws NoBikeException
	 * @throws BikeBeingUsedException
	 */
	void removeBike(String idBike) throws NoBikeException, BikeBeingUsedException;

	/**
	 * Adiciona um pickUp a bicicleta com id idBike e ao utilizador com id idUser,
	 * chamando o construtor da classe PickUpClass e metodos do TAD User e BikeClass
	 * 
	 * @param idBike
	 * @param idUser
	 */
	void addBikeToUser(String idBike, String idUser);

	/**
	 * Estaciona a bicicleta com id idBike no parque com id idPark informando do
	 * atraso da propria viagem devolvendo ainda um vetor de numeros inteiros
	 * correspondente ao dinheiro que o utilizador tem e ao numero de pontos que o
	 * mesmo tem chamando ainda metodos do TAD Bike, Park e User
	 * 
	 * @param idBike
	 * @param idPark
	 * @param minutes
	 * @return vetor de inteiros
	 */
	int[] pickDown(String idBike, String idPark, int minutes);

	/**
	 * Carrega a conta do utilizador com id idUser com o valor passado como
	 * argumento chamando metodos do TAD User e devolvendo o dinheiro atual da conta
	 * 
	 * @param idUser
	 * @param amount
	 * @return saldo da conta do utilizador
	 */
	int chargeUser(String idUser, int amount);

	/**
	 * Verifica se a bicicleta com id idBike esta estacionada no parque com id
	 * idPark
	 * 
	 * @param idBike
	 * @param idPark
	 */
	void hasBike(String idBike, String idPark);

	/**
	 * Metodo retorna o iterador com os pickups finalizados de um utilizador com id
	 * idUser, chamando metodos do TAD User
	 * 
	 * @param idUser
	 * @return
	 * @throws NoBikeException
	 * @throws NoPickUpsException
	 * @throws FirstPickUpException
	 */
	Iterator<PickUp> getUserPickUps(String idUser) throws NoBikeException, NoPickUpsException, FirstPickUpException;

	/**
	 * Metodo retorna o iterador com os pickups finalizados de uma bicicleta com id
	 * idBike, chamando metodos do TAD Bike
	 * 
	 * @param idBike
	 * @return
	 * @throws NoBikeException
	 * @throws NoPickUpsException
	 * @throws FirstPickUpException
	 */
	Iterator<PickUp> getBikePickUps(String idBike) throws NoBikeException, NoPickUpsException, FirstPickUpException;

	/**
	 * Devolve a informacao do parque com idPark
	 * 
	 * @param idPark
	 * @return
	 * @throws NoParkException
	 */
	ParkGets getParkInfo(String idPark) throws NoParkException;

	/**
	 * 
	 * @return parque com maior numero de PickUps
	 * @throws NoParkException
	 * @throws NoPickUpsException
	 */
	ParkGets getFavoritePark() throws NoParkException, NoPickUpsException;

	/**
	 * Devolve a informacao do utilizador com idUser
	 * 
	 * @param idUser
	 * @return
	 */
	UserGets getUserInfo(String idUser);

}
