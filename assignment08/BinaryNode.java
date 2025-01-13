package assignment08;

/**
 * Represents a generically-typed binary tree node. Each binary node contains
 * data, a reference to the left child, and a reference to the right child.
 * 
 * @author 2420 Instructors, Sachin Jampala & Jadon Olson
 * @version Jun 30, 2023
 */
public class BinaryNode<Type> {
	
	private Type data;
	
	private BinaryNode<Type> leftChild;
	
	private BinaryNode<Type> rightChild;
	
	
	/**
	 * Constructor for a binary node, that sets children.
	 * 
	 * @param data - the data stored in 'this' node
	 * @param leftChild - the left child of 'this' node
	 * @param rightChild - the right child of 'this' node
	 */
	public BinaryNode(Type data, BinaryNode<Type> leftChild, BinaryNode<Type> rightChild) {
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	/**
	 * Constructor for a binary node.
	 * 
	 * @param data - the data stored in this node
	 */
	public BinaryNode(Type data) {
		this(data, null, null);
	}
	
	/**
	 * @return the node data
	 */
	public Type getData() {
		return data;
	}
	
	/**
	 * @param data - the node data to be set
	 */
	public void setData(Type data) {
		this.data = data;
	}
	
	/**
	 * @return reference to the left child node
	 */
	public BinaryNode<Type> getLeftChild() {
		return leftChild;
	}
	
	/**
	 * @param leftChild - reference of the left child node to be set
	 */
	public void setLeftChild(BinaryNode<Type> leftChild) {
		this.leftChild = leftChild;
	}
	
	/**
	 * @return reference to the right child node
	 */
	public BinaryNode<Type> getRightChild() {
		return rightChild;
	}
	
	/**
	 * @param rightChild - reference of the right child node to be set
	 */
	public void setRightChild(BinaryNode<Type> rightChild) {
		this.rightChild = rightChild;
	}
	
	/**
	 * @return reference to the leftmost node in the binary tree rooted at this node
	 */
	public BinaryNode<Type> getLeftmostNode() {
		if(leftChild == null)
			return this;
		
		return leftChild.getLeftmostNode();
	}
	
	/**
	 * @return reference to the rightmost node in the binary tree rooted at this node
	 */
	public BinaryNode<Type> getRightmostNode() {
		if(rightChild == null)
			return this;
		
		return rightChild.getRightmostNode();
	}
	
	/**
	 * @return the height of the binary tree rooted at this node
	 * 
	 * The height of a tree is the length of the longest path to a leaf
	 * node. Consider a tree with a single node to have a height of zero.
	 */
	public int height() {
		//Base Case
		if(leftChild == null && rightChild == null)
			return 0;
		
		int left = 0;
		int right = 0;
		
		//Null Checks
		if(leftChild != null)
			left = leftChild.height() + 1;
			
		if(rightChild != null)
			right = rightChild.height() + 1;
		
		//Comparison return
		if(left > right)
			return left;
		return right;
	}
	
	/**
	 * @return the successor of this Binary Node
	 */
	public BinaryNode<Type> successor(){
		if(rightChild == null)
			return null;
		return rightChild.getLeftmostNode();
	}
}
