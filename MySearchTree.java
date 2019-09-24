import java.util.ArrayList;

public class MySearchTree<AnyType extends Comparable<? super AnyType>> {
	
	// the root element of the tree
	private TreeNode<AnyType> root;

	// constructor to construct tree and make the root null
	public MySearchTree() {
		root = null;
	}
	
	// calls in another add method that adds it
	public void add(AnyType insertElement) {
		root = add(insertElement, root);
	}
	
	// calls in another method to find 
	public boolean find(AnyType currentElement) {
		return find(currentElement, root);
	}
	
	// call in another function to find the number of leafs in the tree
	public int leafCount() {
		return leafCount(root);
	}
	
	// call in another function to find the number of parents in the list
	public int parentCount() {
		return parentCount(root);
	}
	
	// call in another function to find the height of the tree
	public int height() {
		return height(root);
	}
	
	// Call in another function to find the ancestors, and print out the ancestors
	public void ancestors(AnyType insertElement){
		ArrayList<AnyType> list = new ArrayList<>();
		list = ancestors(root,insertElement, list);
		for(int i = 0; i < list.size(); ++i) {
			System.out.print(list.get(i) + " ");
		}
	}
	
	// calls in another function to cehck of the tree is perfect
	public boolean isPerfect() {
		int depth = 0;
		int level = 0;
		depth = depth(root, depth);
		return isPerfect(root, depth, level);
	}
	
	public int depth(TreeNode<AnyType> currentRoot, int depth) {
		if(currentRoot == null) {
			return depth;
		}
		else {
			depth++;
			return depth(currentRoot.left, depth);
		}
	}
	
	// calls in another method to print inOrder
	public void inOrderPrint() {
		inOrderPrint(root);
	}
	
	// call in another function to print PreOrder
	public void preOrderPrint() {
		preOrderPrint(root);
	}

	// adds a node into the tree
	private TreeNode<AnyType> add(AnyType insertedElement, TreeNode<AnyType> currentRoot){
		// if the root is null, add the node at the root
		if(currentRoot == null) {
			return new TreeNode<>(insertedElement, null, null);
		}
		
		// compare the values of the new insertedElement vs the 
		// currentRoot Element.
		int compareNodes = insertedElement.compareTo(currentRoot.element);
		
		// if the inserted element is lower than the root, go left
		if(compareNodes < 0) {
			currentRoot.left = add(insertedElement, currentRoot.left);
		}
		// if the element is bigger, add go right
		else if(compareNodes > 0) {
			currentRoot.right = add(insertedElement, currentRoot.right);
		}
		else {
			// duplicate values accepted?
		}
		// return the current root
		return currentRoot;
	}
	
	// method to find the a element the user inputs
	private boolean find(AnyType currentElement, TreeNode<AnyType> currentRoot) {
		// if the root is empty, return false, means the rtree is emopty
		if(currentRoot == null) {
			return false;
		}
		
		// compare the values of the new insertedElement vs the 
		// currentRoot Element.
		int comapreResult = currentElement.compareTo(currentRoot.element);
		
		// if the value we are trying to find is less than the root, move left
		if(comapreResult < 0) {
			return find(currentElement, currentRoot.left);
		}
		// if the value is larger than the root, go right
		else if(comapreResult > 0) {
			return find(currentElement, currentRoot.right);
		}
		// the value is in the list
		else if(comapreResult == 0) {
			return true;
		}
		// the value is not in the list
		else {
			return false;
		}
	}
	
	// method to find the number of leaves in the tree
	private int leafCount(TreeNode<AnyType> currentRoot) {
		// if the root is null return 0
		if(currentRoot == null) {
			return 0;
		}
		// if the left and right node of a current root is empty, than the current root is a leaf, add one
		else if((currentRoot.left == null) && (currentRoot.right == null)) {
			return 1;
		}
		// keep going through the tree, until you find all the leaves in the list
		else {
			return leafCount(currentRoot.left) + leafCount(currentRoot.right);
		}
	}
	
	// find the parents of the tree
	private int parentCount(TreeNode<AnyType> currentRoot) {
		// if the root is empty, then there is no tree, then return 0
		if(currentRoot == null) {
			return 0;
		}
		// if the left and right nodes are empty from the currentroot, the current root is a leave
		else if((currentRoot.left == null) && (currentRoot.right == null)) {
			return 0;
		}
		// keep going the tree, in order to to find all the parents in the tree
		else {
			return 1 + parentCount(currentRoot.left) + parentCount(currentRoot.right);
		}
	}
	
	// used from the authors text
	private int height(TreeNode<AnyType> currentRoot) {
		// if the root is null, then the tree is empty
		if(currentRoot == null) {
			return 0;
		}
		// else find the max of the each right and left sub tree
		else {
			return 1 + Math.max(height(currentRoot.left), height(currentRoot.right));
		}
	}
	
	// return an array list with the vales of the ancestors
	private ArrayList<AnyType> ancestors(TreeNode<AnyType> currentRoot, AnyType currentElement, ArrayList<AnyType> list){
		// if the array list is empty, return 0, or empty list
		if(currentRoot == null) {
			return list;
		}
		// if the current element is the current root then you will return the value
		else if(currentRoot.element == currentElement) {
			list.add(currentElement);
			return list;
		}
		
		// compare the values of the currentElement vs the currentRoot
		int compareNodes = currentElement.compareTo(currentRoot.element);
		
		// if the value is less, go left
		if(compareNodes < 0) {
			list.add(currentRoot.element);
			ancestors(currentRoot.left, currentElement, list);
		}
		// if the value is larger, go right
		else if(compareNodes > 0) {
			list.add(currentRoot.element);
			ancestors(currentRoot.right, currentElement, list);
		}
		else {
			
		}
		return list;
	}
	
	// return if the tree is perfect or not
	private boolean isPerfect(TreeNode<AnyType> currentRoot, int depth, int level) {
		// if the list is empty
		if(currentRoot == null) {
			return true;
		}
		
		if((currentRoot.right == null) && (currentRoot.left == null)) {
			return(depth == level + 1);
		}
		
		if((root.right == null) || (root.left == null)) {
			return false;
		}
		
		return isPerfect(currentRoot.right, depth, level + 1) && isPerfect(currentRoot.left, depth, level + 1);
	}
	
	// print out the tree inOrder
	private void inOrderPrint(TreeNode<AnyType> currentRoot) {
		if(currentRoot == null) {
			return;
		}
		inOrderPrint(currentRoot.left);
		System.out.print(currentRoot.element + " ");
		inOrderPrint(currentRoot.right);
	}
	
	// print the tree in pre order
	private void preOrderPrint(TreeNode<AnyType> currentRoot) {
		if(currentRoot == null) {
			return;
		}
		System.out.print(currentRoot.element + " ");
		preOrderPrint(currentRoot.left);
		preOrderPrint(currentRoot.right);
	}
	
	// the node class, used from the authors source code
	private class TreeNode<AnyType>{
		AnyType element; // the data in the node
		TreeNode<AnyType> left; // the left child
		TreeNode<AnyType> right; // the right child
		
		// these are the constructors
		TreeNode(AnyType currentElement){
			this(currentElement, null, null);
		}

		TreeNode(AnyType currentElement, TreeNode<AnyType> leftChild, TreeNode<AnyType> rightChild) {
			element = currentElement;
			left = leftChild;
			right = rightChild;
		}
		
	}
	
	// main method, in in order to demonstrate the tree capabilities
	public static void main(String[] args) {
		// create a new tree class
		MySearchTree<Integer> tree = new MySearchTree<Integer>();
		MySearchTree<Integer> tree2 = new MySearchTree<Integer>();
		
		// Demonstrate the adding elements in the tree
		System.out.println("In this tree example, I will add the values 60, 55, 100, "
				+ "107, 67, 57, 102, 45, 59, 103 in that order");
		
		tree.add(60);
		tree.add(55);
		tree.add(100);
		tree.add(107);
		tree.add(67);
		tree.add(57);
		tree.add(102);
		tree.add(45);
		tree.add(59);
		tree.add(103);
		
		// demonstrate the printing in certain order methods
		System.out.print("\nWe will print out the elements in Inorder: ");
		tree.inOrderPrint();
		System.out.println("");
		System.out.print("We will print out the elements in Preorder: ");
		tree.preOrderPrint();
		
		System.out.println("");
		System.out.println();
		
		// next we will demeostrate if a number is on the list
		System.out.print("We will try to see if a number is on the list, We will see if the number 60 is in the tree: ");
		if(tree.find(60)) {
			System.out.println("The number on the list");
		}
		else {
			System.out.println("The number is not on the list");
		}
		
		System.out.print("We will try to see if a number is on the list, We will see if the number 0 is in the tree: ");
		if(tree.find(0)) {
			System.out.println("The number on the list");
		}
		else {
			System.out.println("The number is not on the list");
		}
		
		// Find the height, leaves, and parents ot the tree
		System.out.println("\nWe will then print the height, number of leaves, and number of parents in the tree");
		System.out.println("The height of the tree is: " + tree.height());
		System.out.println("The number of leaves in the tree is: " + tree.leafCount());
		System.out.println("The number of parents in the tree is: " + tree.parentCount());
		
		// find the ancestors of the tree
		System.out.print("\nWe will find the ancestors of 45 in the tree: ");
		tree.ancestors(45);
		
		// find if the tree is a perfect tree
		System.out.println("\n\nWe will see if the tree is Perfect");
		if(tree.isPerfect()) {
			System.out.println("The tree is perfect");
		}
		else {
			System.out.println("The tree is not perfect");
		}

		// example is the a perfect tree
		System.out.println("\nMade a new tree with the values 5, 4, 6 in order to see if the tree is perfect:");
		
		tree2.add(5);
		tree2.add(4);
		tree2.add(6);
		
		if(tree2.isPerfect()) {
			System.out.println("The tree is perfect");
		}
		else {
			System.out.println("The tree is not perfect");
		}

	}
	
}
