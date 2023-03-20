package dataStructures;

public class InfixoIterator<K, V> implements Iterator<Entry<K, V>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BSTNode<K, V> root;
	private Stack<BSTNode<K, V>> stack;

	public InfixoIterator(BSTNode<K, V> root) {
		this.root = root;
		stack = new StackInList<BSTNode<K, V>>();
		rewind();
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub

		return !stack.isEmpty();
	}

	@Override
	public Entry<K, V> next() throws NoSuchElementException {
		// TODO Auto-generated method stub
		BSTNode<K, V> node = stack.pop();
		if (node.getRight() != null)
			initialize(node.getRight());
		return node.getEntry();

	}

	private void initialize(BSTNode<K, V> node) {

		while (node.getLeft() != null) {
			stack.push(node);
			node = node.getLeft();

		}
	}

	@Override
	public void rewind() {
		// TODO Auto-generated method stub
		stack = new StackInList<BSTNode<K, V>>();
		initialize(root);
	}

}
