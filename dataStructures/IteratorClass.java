package dataStructures;

public class IteratorClass<V> implements Iterator<V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private V[] array;
	private int counter;

	public IteratorClass(V[] array) {
		this.array = array;
		counter = 0;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return counter < array.length;
	}

	@Override
	public V next() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return array[counter++];
	}

	@Override
	public void rewind() {
		// TODO Auto-generated method stub
		counter = 0;
	}

}
