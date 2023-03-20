package bikePickUp;

 class PickUpClass implements PickUp {

	private static final long serialVersionUID = 1L;
	private static final int DEFAULT = 0;

	private String id;
	protected String initialIdPark, finalIdPark;
	protected int duration, delay, value;

	public PickUpClass(String id, String initialIdPark) {

		this.initialIdPark = initialIdPark;
		this.finalIdPark = null;
		this.duration = DEFAULT;
		this.delay = DEFAULT;
		this.value = DEFAULT;
		this.id = id;

	}

	@Override
	public String getInitialIdPark() {
		return initialIdPark;
	}

	@Override
	public String getFinalIdPark() {
		return finalIdPark;
	}

	@Override
	public int getDuration() {
		return duration;
	}

	@Override
	public int getDelay() {
		return delay;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public void setFinalIdPark(String idPark) {
		this.finalIdPark = idPark;

	}

	@Override
	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public void setDelay(int delay) {
		this.delay = delay;
	}

	public String getId() {
		return id;
	}
}
