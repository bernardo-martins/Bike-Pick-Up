package dataStructures;

public class BSTBreadthFirstIterator<K, V> implements Iterator<Entry<K, V>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BSTNode<K, V> currentNode, root;
	private Queue<BSTNode<K, V>> queue;

	public BSTBreadthFirstIterator(BSTNode<K, V> root) {
		queue = new QueueInList<BSTNode<K, V>>();
		this.root = root;
		rewind();
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub

		return !queue.isEmpty();
	}

	@Override
	public Entry<K, V> next() throws NoSuchElementException {
		// TODO Auto-generated method stub
		currentNode = queue.dequeue();
		if (currentNode.getLeft() != null)
			queue.enqueue(currentNode.getLeft());
		if (currentNode.getRight() != null)
			queue.enqueue(currentNode.getRight());
		return currentNode.getEntry();

	}

	@Override
	public void rewind() {
		// TODO Auto-generated method stubs
		queue = new QueueInList<BSTNode<K, V>>();
		queue.enqueue(root);
	}

}
