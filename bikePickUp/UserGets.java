package bikePickUp;

import java.io.Serializable;

public interface UserGets extends Serializable {
	/**
	 * 
	 * @return id do utilizador
	 */
	String getId();

	/**
	 * 
	 * @return nif do utilizador
	 */
	long getNif();

	/**
	 * 
	 * @return mail do utilizador
	 */
	String getMail();

	/**
	 * 
	 * @return numero de telemovel do utilizador
	 */
	long getPhone();

	/**
	 * 
	 * @return nome do utilizador
	 */
	String getName();

	/**
	 * 
	 * @return morada do utilizador
	 */
	String getAddress();

	/**
	 * Verifica se o utilizador tem uma bicicleta
	 * 
	 * @return true se o utilizador tem uma bicicleta
	 */
	boolean hasBike();

	/**
	 * 
	 * @return saldo do utilizador
	 */
	int getBalance();

	/**
	 * 
	 * @return pontos do utilizador
	 */
	int getPoints();

	/**
	 * Verifica se o utilizador tem id igual ao idUser
	 * 
	 * @param idUser
	 * @return true se o utilizador tem id igual ao idUser
	 */
	boolean equals(String idUser);

	/**
	 * Verifica se o utilizador efetuou PickUps
	 * 
	 * @return true se o utilizador efetuou PickUps
	 */
	boolean hasPickUps();

	/**
	 * Verifica se o utilizador se encontra no primeiro PickUp
	 * 
	 * @return true se o utilizador se encontra no primeiro PickUp
	 */
	boolean firstPickUp();

	/**
	 * Verifica se o utilizador tem saldo para um PickUp
	 * 
	 * @return true se o utilizador tem saldo para um PickUp
	 */
	boolean hasBalanceForAride();

	/**
	 * 
	 * @return PickUp atual
	 */
	PickUp getCurrentPickUp();

}
