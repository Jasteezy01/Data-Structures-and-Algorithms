package assignment08;

import java.util.*;

/**
 * A class for representing a BinarySearchTree. This class is backed by a linked list
 * it initially is empty, then is filled with the first data being the root and all
 * following insertions being added correctly. (Lesser value nodes to the left of their parent
 * and greater to the right)
 *
 * @author Jadon Olson and Sachin Jampala
 * @version July 5, 2023
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {
    private List<Type> list;
    private BinaryNode<Type> root;
    private int size;

    private ArrayList<Type> arrayList;


    /**
     * A simple constructor for the BinarySearchTree class
     *
     */
    public BinarySearchTree(){
        list = new LinkedList<>();
        root = null;
        size = 0;
        arrayList = new ArrayList();
    }



    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         the input item was actually inserted); otherwise, returns false
     */
    @Override
    public boolean add(Type item) {


        //If root is null, item will be the new root.
        if(root == null){
            root = new BinaryNode<>(item);
            size++;
            arrayList.add(item);
            return true;
        }

        //Loop until correct place is found
        BinaryNode<Type> current = root;
        while(true){
            //Check if value is equal to current, if so return false.
            if(item.compareTo(current.getData()) == 0)
                return false;

            if(item.compareTo(current.getData()) > 0) { //Go down the right tree
                //If we found our place
                if (current.getRightChild() == null) {
                    current.setRightChild(new BinaryNode<>(item));
                    arrayList.add(item);
                    size++;
                    return true;
                }
                current = current.getRightChild();
                continue;
            }

            if(item.compareTo(current.getData()) < 0) { //Go down the left tree
                //If we found our place
                if (current.getLeftChild() == null) {
                    current.setLeftChild(new BinaryNode<>(item));
                    arrayList.add(item);
                    size++;
                    return true;
                }
                current = current.getLeftChild();
            }
        }
    }

    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         any item in the input collection was actually inserted); otherwise,
     *         returns false
     */
    @Override
    public boolean addAll(Collection<? extends Type> items) {
        Type[] arr = (Type[]) items.toArray(new Comparable[items.size()]);//Convert the collection to an array
        boolean ret = false;
        
        for(int i = 0; i < arr.length; i++) //Attempt to add everything using the add method
            if(add(arr[i]))
                ret = true;//If one item was added return true

        return ret; //return false if no items were added
    }

    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */
    @Override
    public void clear() {
        list.clear();
        root = null;
        size = 0;
        arrayList.clear();
    }

    /**
     * Determines if there is an item in this set that is equal to the specified
     * item.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item;
     *         otherwise, returns false
     */
    @Override
    public boolean contains(Type item) {
        BinaryNode<Type> current = root;

        while (current != null) {
            if (item.compareTo(current.getData()) == 0) {
                return true;
            } else if (item.compareTo(current.getData()) < 0) {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }
        }

        return false;
    }



    /**
     * Determines if for each item in the specified collection, there is an item in
     * this set that is equal to it.
     *
     * @param items - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item
     *         in this set that is equal to it; otherwise, returns false
     */
    @Override
    public boolean containsAll(Collection<? extends Type> items) {
        for (Type item : items) {
            if (!contains(item)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Returns the first (i.e., smallest) item in this set.
     *
     * @return the first item in this set, which is the item that would be
     * returned by the next call to the iterator method (if
     * that call were made immediately after this method call)
     * @throws NoSuchElementException if this set is empty
     */
    @Override
    public Type first() {
        if (isEmpty())
            throw new NoSuchElementException("BST is empty");
        BinaryNode<Type> current = root;
        while (current.getLeftChild() != null)
            current = current.getLeftChild();

        return current.getData();
    }


    /**
     * Returns true if this set contains no items.
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Returns the last (i.e., largest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public Type last() throws NoSuchElementException {
    	if (isEmpty())
            throw new NoSuchElementException("BST is empty");
        BinaryNode<Type> current = root;
        while(true){
            if(current.getRightChild() == null)
                return current.getData();
            current = current.getRightChild();
        }
    }

    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         the input item was actually removed); otherwise, returns false
     */
    @Override
    public boolean remove(Type item) {
        // Check if tree is empty
        if (root == null) {
            return false;
        }
        //Return false if node doesn't exist
//        if(!contains(item))
//            return false;

        // Find the node to remove
        BinaryNode<Type> node = root;
        BinaryNode<Type> parent = null;
        boolean isLeftChild = false;
        while (node != null && item.compareTo(node.getData()) != 0) {
            parent = node;
            if (item.compareTo(node.getData()) < 0) {
                node = node.getLeftChild();
                isLeftChild = true;
            } else {
                node = node.getRightChild();
                isLeftChild = false;
            }
        }
        // If node is not found, return false
        if (node == null) {
            return false;
        }
        // If node has two children, find the next highest node and replace the node to be removed
        if (node.getLeftChild() != null && node.getRightChild() != null) {
            BinaryNode<Type> successorParent = node;
            BinaryNode<Type> successor = node.successor();
            
            remove(successor.getData());
            
            node.setData(successor.getData());
            node = successor;
            parent = successorParent;
            isLeftChild = false;
            size--;
            
        }
        // If node has no children, remove it
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            if (node == root) {
                root = null;
            } else if (isLeftChild) {
                parent.setLeftChild(null);
            } else {
                parent.setRightChild(null);
            }
            arrayList.remove(node.getData());
            size--;
        }
        // If node has one child, replace it with its child
        else if (node.getLeftChild() == null) {
            if (node == root) {
                root = node.getRightChild();
            } else if (isLeftChild) {
                parent.setLeftChild(node.getRightChild());
            } else {
                parent.setRightChild(node.getRightChild());
            }
            arrayList.remove(node.getData());
            size--;
        } else {
            if (node == root) {
                root = node.getLeftChild();
            } else if (isLeftChild) {
                parent.setLeftChild(node.getLeftChild());
            } else {
                parent.setRightChild(node.getLeftChild());
            }
            arrayList.remove(node.getData());
            size--;
        }
        return true;
    }


    /**
     * Ensures that this set does not contain any of the items in the specified
     * collection.
     *
     * @param items - the collection of items whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         any item in the input collection was actually removed); otherwise,
     *         returns false
     */
    @Override
    public boolean removeAll(Collection<? extends Type> items) {
        boolean modified = false;
        for (Type item : items) {
            if (remove(item)) {
                modified = true;
            }
        }
        return modified;
    }


    /**
     * Returns the number of items in this set.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an ArrayList containing all of the items in this set, in sorted
     * order.
     */
    @Override
    public ArrayList<Type> toArrayList() {
    	ArrayList<Type> ret = new ArrayList<>();
    	ret.addAll(arrayList);
        Collections.sort(ret);
        return ret;
    }
}
