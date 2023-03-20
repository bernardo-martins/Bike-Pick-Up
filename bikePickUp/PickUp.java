package bikePickUp;

public interface PickUp extends PickUpGets {
	/**
	 * Adiciona ao PickUp o id do parque
	 * 
	 * @param idPark
	 */
	void setFinalIdPark(String idPark);

	/**
	 * Adiciona ao PickUP a sua duracao
	 * 
	 * @param duration
	 */
	void setDuration(int duration);

	/**
	 * Adiciona o valor do PickUp
	 * 
	 * @param value
	 */
	void setValue(int value);

	/**
	 * Adiciona o atraso do PickUp
	 * 
	 * @param delay
	 */
	void setDelay(int delay);

}
