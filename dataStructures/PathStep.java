package dataStructures;

public class PathStep<K, V> {

	// The parent of the node.
	public BSTNode<K, V> parent;
	// The node is the left or the right child of parent.
	public boolean isLeftChild;

	public PathStep(BSTNode<K, V> theParent, boolean toTheLeft) {
		parent = theParent;
		isLeftChild = toTheLeft;
	}

	public void set(BSTNode<K, V> newParent, boolean toTheLeft) {
		parent = newParent;
		isLeftChild = toTheLeft;
	}

	public boolean isLeftChild() {
		return isLeftChild;
	}

	public BSTNode<K, V> getParent() {
		return parent;
	}

}
