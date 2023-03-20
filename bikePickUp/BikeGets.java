package bikePickUp;
import java.io.Serializable;

public interface BikeGets extends Serializable{

	
	/**
	 * 
	 * @return id da propria bicicleta
	 */
	String getIdBike();
	
	/**
	 * 
	 * @return
	 */
	String getCurrentUser();

	/**
	 * 
	 * @return id do parque onde pertence a bicicleta
	 */
	String getIdPark();

	/**
	 * 
	 * @return matricula da bicicleta
	 */
	String getPlate();

	/**
	 * Verifica se a bicicleta esta ocupada ou nao
	 * @return true caso esteja ocupada e false caso nao esteja
	 */
	boolean isOccupied();

	/**
	 * Verifica se a bicicleta ja foi alvo de pickups por parte de utilizadores
	 * @return true caso esta tenha pickups e false caso nao
	 */
	boolean hasPickUps();

	/**
	 * Verifica se a bicicleta esta a ser alvo do seu primeiro pickup por parte de um utilizador
	 * @return true caso esteja e false caso nao esteja
	 */
	boolean firstPickUp();

}
