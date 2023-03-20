package dataStructures;

public class EntryClass<K, V> implements Entry<K, V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private V value;
	private K key;

	public EntryClass(K key, V value) {
		this.value = value;
		this.key = key;
	}

	@Override
	public K getKey() {
		// TODO Auto-generated method stub
		return key;
	}

	@Override
	public V getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public void setValue(V value) {
		// TODO Auto-generated method stub
		this.value = value;
	}

	@Override
	public void setKey(K key) {
		// TODO Auto-generated method stub
		this.key = key;
	}

}
