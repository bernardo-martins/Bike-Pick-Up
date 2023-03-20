
public enum Commands {

	ADDUSER("Add user"), ADDBIKE("Add bike"), ADDPARK("Add park"), REMOVEBIKE("Remove bike"), REMOVEUSER("Remove user"),
	GETPARKINFO("Get park info"), PICKUP("Pick up"), PICKDOWN("Pick down"), BIKEPICKUPS("Bike pick ups"),
	USERPICKUPS("Bike pick ups"), PARKEDBIKE("Parked bike"), LISTDELAYED("List delayed"),
	FAVORITEPARKS("Favorite parks"), CHARGEUSER("Charge user"), XS("Gravando e terminando..."), GETUSERINFO("User info"),
	UKNOWN("");

	private String description;

	private Commands(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

}
