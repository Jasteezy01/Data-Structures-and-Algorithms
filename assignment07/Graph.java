package assignment07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * This method represents a graph
 * 
 * @author Sachin Jampala and Jadon Olson
 * @version June 28, 2023
 *
 * @param <Type> - A Generic Type
 */
public class Graph<Type> {
	
	private List<Type> sources;
	private List<Type> destinations;
	private HashMap<Type, Vertex> vertices;
	private int numVertices;
	
	/**
	 * This is a constructor for the Graph
	 * @param sources
	 * @param destinations
	 */
	public Graph(List<Type> sources, List<Type> destinations) {
		this.sources = sources;
		this.destinations = destinations;
		this.numVertices = 0;
		this.vertices = new HashMap<>();
		makeGraph();
	}
	
	/**
	 * This method creates the Graph
	 */
	public void makeGraph() {
		for(int i = 0; i < sources.size(); i++)
			addEdge(sources.get(i), destinations.get(i));
		
	}
	
	/**
	 * This method returns the Vertex
	 * @param data - the data in the Vertex that is being returned
	 * @return - A Vertex
	 */
	public Vertex getVertex(Type data) {
		Vertex value = null;
		Type data1 = null;
		for(Map.Entry<Type, Vertex> entry : vertices.entrySet()) {
			 data1 = entry.getKey();
			 value = entry.getValue();
			 if(data1.equals(data)) {
				 return value;
			 }
		}
		
		return null;
	}
	
	/**
	 * This method returns the number of Vertices in a graph
	 */
	public int getNumVertices() {
		return numVertices;
	}
	
	/**
	 * This method creates an edge in the graph
	 * @param data1 - The data of the first Vertex in the edge
	 * @param data2 - The data of the second Vertex in the edge
	 */
	public void addEdge(Type data1, Type data2) {
		Vertex vertex1;
		if(vertices.containsKey(data1)) {
			vertex1 = vertices.get(data1);
		}
		else {
			vertex1 = new Vertex(data1);
			numVertices++;
			vertices.put(data1, vertex1);
		}
		
		Vertex vertex2;
		if(vertices.containsKey(data2)) {
			vertex2 = vertices.get(data2);
		}
		else {
			vertex2 = new Vertex(data2);
			numVertices++;
			vertices.put(data2, vertex2);
		}
		
		vertex1.addNeighbor(vertex2);
		vertex2.increaseIndegree();
		
	}
	
	/**
	 * This method generates a dot representation of the graph
	 * @return - returns a String that is the dot representation of the graph
	 */
	public String generateDot() {
		StringBuilder dot = new StringBuilder("digraph d {\n");
		for(Vertex v : vertices.values()) {
			Iterator<Vertex> edges = v.edges();
			while(edges.hasNext()) {
				dot.append("\t\"" + v.getData() + "\" -> \"" + edges.next().getData() + "\"\n");
			}
		}
		return dot.toString() + "}";
	}
	
	/**
	 * This method returns the graph as a String
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		for(Vertex v : vertices.values()) {
			result.append(v.getData() + "\n");
		}
		return result.toString();
	}

	/**
	 * This method does a depth-first search from the current Vertex to the goal Vertex
	 * @param current - The current Vertex that is being searched from
	 * @param goal - The Vertex that is being searched for 
	 * @return - returns true if the goal Vertex is found and false if it is not
	 */
	public boolean dfs(Vertex current, Vertex goal) {
	    current.visit();
	    
	    if (current.getData().equals(goal.getData())) {
	        return true;
	    }

	    for (Vertex next : ((Vertex<Type>) current).getNeighbors()) {
	        if (!next.getVisited()) {
	            next.setCameFrom(current);
	            if (dfs(next, goal)) {
	                return true;
	            }
	        }
	    }

	    return false;
	}

	/**
	 * This method does a breadth-first search from the start Vertex to the end Vertex and returns the best path
	 * @param start - the Vertex that the search is started from
	 * @param end - the Vertex that the search ends on
	 * @return returns a list of the best path of Vertices
	 */
	public List<Type> bfs(Vertex start, Vertex end) {
	    Queue<Vertex> queue = new LinkedList<>();
	    List<Type> path = new ArrayList<>();

	    queue.offer(start);
	    start.visit();

	    while (!queue.isEmpty()) {
	        Vertex current = queue.poll();
	        if (current == end) {
	            Vertex backtrack = current;
	            while (backtrack != null) {
	                path.add(0, (Type) backtrack.getData());
	                backtrack = backtrack.getCameFrom();
	            }
	            return path;
	        }

	        for (Vertex neighbor : ((Vertex<Type>) current).getNeighbors()) {
	            if (!neighbor.getVisited()) {
	                queue.offer(neighbor);
	                neighbor.visit();
	                neighbor.setCameFrom(current);
	            }
	        }
	    }

	    return null;
	}
	
	/**
	 * This method performs a topological sort on the graph
	 * @return returns a List of the Vertex data that has been topologically sorted
	 */
	public List<Type> sort() {
	    Queue<Vertex<Type>> queue = new LinkedList<>();
	    List<Type> sorted = new ArrayList<>();

	    for (Vertex<Type> v : vertices.values()) {
	        if (v.getIndegree() == 0) {
	            queue.offer(v);
	        }
	    }

	    while (!queue.isEmpty()) {
	        Vertex<Type> u = queue.poll();
	        sorted.add(u.getData());

	        for (Vertex<Type> v : u.getNeighbors()) {
	            v.decreaseIndegree();
	            if (v.getIndegree() == 0) {
	                queue.offer(v);
	            }
	        }
	    }

	    return sorted;
	}
}
