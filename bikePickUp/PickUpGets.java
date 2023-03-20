package bikePickUp;

import java.io.Serializable;

public interface PickUpGets extends Serializable {
	/**
	 * 
	 * @return id do parque em que o PickUp foi iniciado
	 */
	String getInitialIdPark();

	/**
	 * 
	 * @return id do parque em que o PickUp foi terminado
	 */
	String getFinalIdPark();

	/**
	 * 
	 * @return id do PickUp
	 */
	String getId();

	/**
	 * 
	 * @return duracao do PickUp
	 */
	int getDuration();

	/**
	 * 
	 * @return atraso do PickUp
	 */
	int getDelay();

	/**
	 * 
	 * @return valor do PickUp
	 */
	int getValue();

}
