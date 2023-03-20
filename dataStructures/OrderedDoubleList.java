package dataStructures;

public class OrderedDoubleList<K extends Comparable<K>, V> implements OrderedDictionary<K, V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DListNode<Entry<K, V>> head, tail;
	private int size;

	public OrderedDoubleList() {
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public V find(K key) {

		DListNode<Entry<K, V>> current = head;
		while (current != null) {
			Entry<K, V> element = current.getElement();
			if (element.getKey().equals(key))
				return element.getValue();
			current = current.getNext();
		}

		return null;
	}

	private int findNode(K key) {
		DListNode<Entry<K, V>> node = head;
		int position = 0;
		while (node != null && node.getElement().getKey().compareTo(key) < 0) {
			node = node.getNext();
			position++;
		}
		if (node == null)
			return size;
		else
			return position;
	}

	private DListNode<Entry<K, V>> getNode(int position) {
		DListNode<Entry<K, V>> node;

		if (position <= (size - 1) / 2) {
			node = head;
			for (int i = 0; i < position; i++)
				node = node.getNext();
		} else {
			node = tail;
			for (int i = size - 1; i > position; i--)
				node = node.getPrevious();
		}
		return node;
	}

	private void addMiddle(int position, Entry<K, V> entry) {
		DListNode<Entry<K, V>> prevNode = getNode(position - 1);
		DListNode<Entry<K, V>> nextNode = prevNode.getNext();
		DListNode<Entry<K, V>> newNode = new DListNode<Entry<K, V>>(entry, prevNode, nextNode);
		prevNode.setNext(newNode);
		nextNode.setPrevious(newNode);
		size++;
	}

	@Override
	public V insert(K key, V value) {
		int position = findNode(key);
		DListNode<Entry<K, V>> node = getNode(position);
		if (node != null) {
			Entry<K, V> element = node.getElement();
			V previousValue;
			if (element != null && element.getKey().equals(key)) {
				previousValue = element.getValue();
				element.setValue(value);
				return previousValue;
			}
		}
		Entry<K, V> entry = new EntryClass<K, V>(key, value);
		if (this.isEmpty())
			this.addFirst(entry);
		else {
			if (position == 0)
				this.addFirst(entry);
			else if (position == size)
				this.addLast(entry);
			else
				this.addMiddle(position, entry);
		}
		return null;
	}

	@Override
	public V remove(K key) {
		int position = findNode(key);
		if (position == -1)
			// nao encontrou entry com key K
			return null;
		else if (position == 0)
			return removeFirst();
		else if (position == size - 1)
			return removeLast();
		else
			return remove(position);
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		return new DoublyLLIterator<Entry<K, V>>(head, tail);
	}

	@Override
	public Entry<K, V> minEntry() throws EmptyDictionaryException {
		if (this.isEmpty())
			throw new EmptyDictionaryException();
		return head.getElement();
	}

	@Override
	public Entry<K, V> maxEntry() throws EmptyDictionaryException {
		if (this.isEmpty())
			throw new EmptyDictionaryException();
		return tail.getElement();
	}

	private void addFirst(Entry<K, V> element) {
		DListNode<Entry<K, V>> newNode = new DListNode<Entry<K, V>>(element, null, head);
		if (this.isEmpty())
			tail = newNode;
		else
			head.setPrevious(newNode);
		head = newNode;
		size++;
	}

	private void addLast(Entry<K, V> element) {
		DListNode<Entry<K, V>> newNode = new DListNode<Entry<K, V>>(element, tail, null);
		if (this.isEmpty())
			head = newNode;
		else
			tail.setNext(newNode);
		tail = newNode;
		size++;
	}

	private V removeFirst() throws EmptyListException {
		if (isEmpty())
			return null;
		V removed = head.getElement().getValue();
		head = head.getNext();
		if (head == null)
			// se a lista fica vazia tail passa a null
			tail = null;
		else
			head.setPrevious(null);
		size--;
		return removed;
	}

	private V removeLast() throws EmptyListException {
		if (isEmpty())
			return null;
		V removed = tail.getElement().getValue();
		tail = tail.getPrevious();
		if (tail == null) {
			// se a lista fica vazia head passa a null
			head = null;
		} else
			tail.setNext(null);
		size--;
		return removed;
	}

	private V remove(int position) throws InvalidPositionException {
		if (position < 0 || position >= size)
			return null;

		DListNode<Entry<K, V>> current = getNode(position);
		DListNode<Entry<K, V>> previous = current.getPrevious();
		DListNode<Entry<K, V>> next = current.getNext();
		previous.setNext(current.getNext());
		next.setPrevious(current.getPrevious());
		size--;
		return current.getElement().getValue();
	}

}
