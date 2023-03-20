package bikePickUp;

import dataStructures.*;

class BikeClass implements Bike {

	private static final long serialVersionUID = 1L;

	private String idBike, idPark, plate;
	private User user;
	private List<PickUp> pickups;
	private PickUp currentPickUp;

	public BikeClass(String idBike, String idPark, String plate) {
		this.idBike = idBike;
		this.idPark = idPark;
		this.plate = plate;
		this.user = null;
		pickups = new DoublyLinkedList<PickUp>();
		currentPickUp = null;
	}

	/**
	 * 
	 * @return id da propria bicicleta
	 */
	@Override
	public String getIdBike() {
		// TODO Auto-generated method stub
		return idBike;
	}

	/**
	 * 
	 * @return id do parque onde pertence a bicicleta
	 */
	@Override
	public String getIdPark() {
		// TODO Auto-generated method stub
		return idPark;
	}

	/**
	 * 
	 * @return matricula da bicicleta
	 */
	@Override
	public String getPlate() {
		// TODO Auto-generated method stub
		return plate;
	}

	/**
	 * Metodo adiciona um objeto User ao objeto bicicleta
	 * 
	 * @param user
	 */
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		this.user = user;
	}

	/**
	 * Verifica se a bicicleta esta ocupada ou nao
	 * 
	 * @return true caso esteja ocupada e false caso nao esteja
	 */
	@Override
	public boolean isOccupied() {
		// TODO Auto-generated method stub
		return currentPickUp != null;
	}

	/**
	 * Adiciona um pickUp inicial nao finalizado, registando-o na variavel
	 * currentPickUp
	 * 
	 * @param pu
	 */
	@Override
	public void addPickUp(PickUp pu) {
		// TODO Auto-generated method stub
		this.currentPickUp = pu;

	}

	/**
	 * Finaliza um PickUp e adiciona o mesmo a colecao de pickUps
	 * 
	 * @param finalIdPark
	 * @param duration
	 * @param delay
	 * @param value
	 */
	@Override
	public void finishPickUp(String finalIdPark, int duration, int delay, int value) {
		// TODO Auto-generated method stub

		currentPickUp.setDelay(delay);
		currentPickUp.setFinalIdPark(finalIdPark);
		currentPickUp.setDuration(duration);
		currentPickUp.setValue(value);
		pickups.addLast(currentPickUp);
		currentPickUp = null;
		user = null;
	}

	/**
	 * Metodo devolve um iterador para os pickUps que ja foram finalizados
	 * 
	 * @return iterador
	 */
	@Override
	public Iterator<PickUp> getEndedPickUps() {
		// TODO Auto-generated method stub
		return pickups.iterator();
	}

	/**
	 * Verifica se a bicicleta ja foi alvo de pickups por parte de utilizadores
	 * 
	 * @return true caso esta tenha pickups e false caso nao
	 */
	@Override
	public boolean hasPickUps() {
		// TODO Auto-generated method stub
		return pickups.size() >= 1;
	}

	/**
	 * Verifica se o id passado como parametro e igual ao id da propria bicicleta
	 * 
	 * @param id
	 * @return true caso sejam iguais e false caso nao sejam
	 */
	@Override
	public boolean equals(String id) {
		return this.getIdBike().equals(id);
	}

	/**
	 * Verifica se a bicicleta esta a ser alvo do seu primeiro pickup por parte de
	 * um utilizador
	 * 
	 * @return true caso esteja e false caso nao esteja
	 */
	@Override
	public boolean firstPickUp() {
		// TODO Auto-generated method stub
		return pickups.isEmpty() && user != null;
	}

	@Override
	public String getCurrentUser() {
		// TODO Auto-generated method stub
		
		return currentPickUp.getId();
	}

}
