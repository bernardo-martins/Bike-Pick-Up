package bikePickUp;



import dataStructures.Iterator;

public interface Bike extends BikeGets {

	/**
	 * Metodo adiciona um objeto User ao objeto bicicleta
	 * 
	 * @param user
	 */
	void addUser(User user);

	/**
	 * Finaliza um PickUp e adiciona o mesmo a colecao de pickUps
	 * 
	 * @param finalIdPark
	 * @param duration
	 * @param delay
	 * @param value
	 */
	void finishPickUp(String finalIdPark, int duration, int delay, int value);

	/**
	 * Adiciona um pickUp inicial nao finalizado, registando-o na variavel
	 * currentPickUp
	 * 
	 * @param pu
	 */
	void addPickUp(PickUp pu);

	/**
	 * Metodo devolve um iterador para os pickUps que ja foram finalizados
	 * 
	 * @return iterador
	 */
	Iterator<PickUp> getEndedPickUps();

	/**
	 * Verifica se o id passado como parametro e igual ao id da propria bicicleta
	 * 
	 * @param id
	 * @return true caso sejam iguais e false caso nao sejam
	 */
	boolean equals(String id);

}
