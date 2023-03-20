package dataStructures;

/**
 * Chained Hash table implementation
 * 
 * @author AED Team
 * @version 1.0
 * @param <K>
 *            Generic Key, must extend comparable
 * @param <V>
 *            Generic Value
 */

public class ChainedHashTable<K extends Comparable<K>, V> extends HashTable<K, V> {
	/**
	 * Serial Version UID of the Class.
	 */
	static final long serialVersionUID = 0L;

	/**
	 * The array of dictionaries.
	 */
	protected Dictionary<K, V>[] table;

	/**
	 * Constructor of an empty chained hash table, with the specified initial
	 * capacity. Each position of the array is initialized to a new ordered list
	 * maxSize is initialized to the capacity.
	 * 
	 * @param capacity
	 *            defines the table capacity.
	 */
	@SuppressWarnings("unchecked")
	public ChainedHashTable(int capacity) {
		int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
		// Compiler gives a warning.
		table = new Dictionary[arraySize];
		for (int i = 0; i < arraySize; i++)
			table[i] = new OrderedDoubleList<K, V>();
		maxSize = capacity;
		currentSize = 0;
	}

	public ChainedHashTable() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Returns the hash value of the specified key.
	 * 
	 * @param key
	 *            to be encoded
	 * @return hash value of the specified key
	 */
	protected int hash(K key) {
		return Math.abs(key.hashCode()) % table.length;
	}

	@Override
	public V find(K key) {
		return table[this.hash(key)].find(key);
	}

	@Override
	public V insert(K key, V value) {
		if (this.isFull())
			this.rehash();
		V replaced = table[this.hash(key)].insert(key, value);
		if (replaced == null)
			super.currentSize++;
		return replaced;
	}

	@SuppressWarnings("unchecked")
	private void rehash() {
		// Compiler gives a warning.
		Dictionary<K, V>[] newTable = new Dictionary[table.length * 2];
		for (int i = 0; i < newTable.length; i++)
			newTable[i] = new OrderedDoubleList<K, V>();
		IteratorChainedHashTable<K, V> it = (IteratorChainedHashTable<K, V>) iterator();
		while (it.hasNext()) {
			Entry<K, V> e = it.next();
			newTable[hashSpec(e.getKey(), table.length * 2)].insert(e.getKey(), e.getValue());
		}

		table = newTable;
	}

	//Metodo privado que calcula o indice na ChainedHashTable considerando a nova dimensao da mesma
	private int hashSpec(K key, int length) {
		return Math.abs(key.hashCode()) % length;
	}

	@Override
	public V remove(K key) {
		if (!this.isEmpty()) {
			int index = this.hash(key);
			V value = table[index].remove(key);
			if (value != null)
				currentSize--;
			return value;
		}
		return null;
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {

		return new IteratorChainedHashTable<K, V>(table);
	}
}
