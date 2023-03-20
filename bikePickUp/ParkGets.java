package bikePickUp;

import java.io.Serializable;

public interface ParkGets extends Serializable {
	/**
	 * 
	 * @return id do parque
	 */
	String getId();

	/**
	 * 
	 * @return nome do parque
	 */
	String getName();

	/**
	 * 
	 * @return morada do parque
	 */
	String getAddress();

	/**
	 * 
	 * @return no de bicicletas no parque
	 */
	int getNbikes();

	/**
	 * 
	 * @return numero de PickUps efetuados no parque
	 */
	int getNumberOfPickUps();

	/**
	 * Verifica se a bicicleta com id idBike se encontra estacionada no parque
	 * 
	 * @param idBike
	 * @return true se a bicicleta com id idBike se encontra estacionada no parque
	 */
	boolean hasBike(String idBike);

}
