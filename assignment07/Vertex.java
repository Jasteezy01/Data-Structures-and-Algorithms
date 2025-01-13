package assignment07;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/**
 * This class represents a Vertex
 * 
 * @author Sachin Jampala and Jadon Olson
 * @version June 28, 2023
 *
 * @param <Type> - A Generic Type
 */
public class Vertex<Type> {

	private Type data;
	private LinkedList<Vertex<Type>> neighbors;
	private boolean visited; 
	private Vertex cameFrom;
	private int indegree;
	
	/**
	 * This is a constructor for a Vertex
	 * @param data - the data that is stored in the Vertex
	 */
	public Vertex(Type data) {
		this.data = data;
		neighbors = new LinkedList<>();
		visited = false;
		cameFrom = null;
		indegree = 0;
		
		
	}
	
	/**
	 * This method adds a neighbor to the Vertex
	 * @param vertex - the neighboring Vertex
	 */
	public void addNeighbor(Vertex vertex) {
		neighbors.add(vertex);
	}
	
	/**
	 * This method sets visited to true if the Vertex has been visited
	 */
	public void visit() {
		visited = true;
	}
	
	/**
	 * This method returns the visited boolean in the Vertex
	 * @return returns true if visited is true and false if it is false
	 */
	public boolean getVisited() {
		return visited;
	}
	
	/**
	 * This method returns the Vertex that was previously visited
	 * @return returns the Vertex that was previously visited
	 */
	public Vertex getCameFrom() {
		return cameFrom;
	}
	
	/**
	 * This method sets the Vertex that was previously visited
	 * @param cameFrom - cameFrom is the Vertex that was previously visited
	 */
	public void setCameFrom(Vertex cameFrom) {
		this.cameFrom = cameFrom;
	}
	
	/**
	 * This method increases the indegree by 1
	 */
	public void increaseIndegree() {
		indegree++;
	}
	
	/**
	 * This method decreases the indegree by 1
	 */
	public void decreaseIndegree() {
		indegree--;
	}
	
	/**
	 * This method returns the indegree
	 * @return returns the indegree
	 */
	public int getIndegree() {
		return indegree;
	}
	
	/**
	 * This method returns data
	 * @return returns data
	 */
	public Type getData() {
		return data;
	}
	
	/**
	 * Returns an Iterator for the neighbors of this Vertex
	 * @return returns an Iterator for the neighbors of this Vertex
	 */
	public Iterator<Vertex<Type>> edges() {
		return neighbors.iterator();
	}
	
	/**
	 * This method returns the Vector as a String
	 * @return returns the Vector as a String representation
	 */
	public String toString() {
		String s = data + " is adjacent to ";
		Iterator<Vertex<Type>> itr = edges();
		while(itr.hasNext()) {
			s += itr.next().data + " ";
		}
		return s;
	}
	
	/**
	 * This method returns the neighbors of the Vertex as a LinkedList.
	 * @return returns the neighbors of the Vertex in a LinkedList
	 */
	public LinkedList<Vertex<Type>> getNeighbors() {
		return neighbors;
	}
	
}
