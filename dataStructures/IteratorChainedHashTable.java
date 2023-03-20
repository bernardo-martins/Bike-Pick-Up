package dataStructures;

public class IteratorChainedHashTable<K, V> implements Iterator<Entry<K, V>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int DEFAULT = 0;

	private Dictionary<K, V>[] dictionary;
	private Iterator<Entry<K, V>> iterator;
	private int aux;

	public IteratorChainedHashTable(Dictionary<K, V>[] dictionary) {

		this.dictionary = dictionary;
		aux = DEFAULT;
		this.rewind();

	}

	private Iterator<Entry<K, V>> nextIterator() {
		iterator = null;
		while (aux < dictionary.length && iterator == null) {

			if (!dictionary[aux].isEmpty())
				iterator = dictionary[aux].iterator();
			aux++;
		}

		return iterator;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		if (iterator != null && iterator.hasNext())
			return true;
		else if (nextIterator() == null)
			return false;
		else
			return true;
	}

	@Override
	public Entry<K, V> next() throws NoSuchElementException {
		// TODO Auto-generated method stub

		return iterator.next();
	}

	@Override
	public void rewind() {
		// TODO Auto-generated method stub
		aux = 0;

	}

}
