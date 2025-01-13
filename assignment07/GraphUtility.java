package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * Contains several methods for solving problems on generic, directed,
 * unweighted, sparse graphs.
 *
 * @author Eric Heisler
 * @version June 20, 2023
 */
public class GraphUtility {
	/**
	 * This method must use the recursive depth-first search algorithm presented in lecture to determine 
	 * whether there is a path from the vertex with srcData to the vertex with dstData in the graph.  
	 * Throws an IllegalArgumentException if there does not exist a vertex in the graph with srcData, 
	 * and likewise for dstData.
	 * @param <Type>
	 * @param sources
	 * @param destinations
	 * @param srcData
	 * @param dstData
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static <Type> boolean areConnected(List<Type> sources, List<Type> destinations, Type srcData, Type dstData) throws IllegalArgumentException {
		if(sources.size() != destinations.size()) {
			throw new IllegalArgumentException("Sources and destinations are not the same size");
		}
		
		if(!sources.contains(srcData) && !destinations.contains(srcData)) {
			throw new IllegalArgumentException("There does not exist a vertex in the graph with one of the provided data");
		}
		
		if(!sources.contains(dstData) && !destinations.contains(dstData)) {
			throw new IllegalArgumentException("There does not exist a vertex in the graph with one of the provided data");
		}
		Graph graph = new Graph(sources, destinations);
		
		return graph.dfs(graph.getVertex(srcData), graph.getVertex(dstData));
	}

	/**
	 * This method must use the breadth-first search algorithm presented in lecture to find a shortest
	 * path from the vertex with srcData to the vertex with dstData in the graph.  Throws an 
	 * IllegalArgumentException if there does not exist a vertex in the graph with srcData, and likewise 
	 * for dstData.  Also, throws an IllegalArgumentException if there does not exist a path between the 
	 * two vertices.
	 * @param <Type>
	 * @param sources
	 * @param destinations
	 * @param srcData
	 * @param dstData
	 * @return
	 * @throws IllegalArgumentException - If there does not exist a path between the two vertices. If sources
	 * and destinations are not the same size. If there does not exist a vertex in the graph with src.data or dst.data.
	 */
	public static <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations, Type srcData, Type dstData) throws IllegalArgumentException {
		if(sources.size() != destinations.size()) {
			throw new IllegalArgumentException("Sources and destinations are not the same size");
		}
		
		if(!sources.contains(srcData) || !destinations.contains(dstData)) {
			throw new IllegalArgumentException("There does not exist a vertex in the graph with one of the provided data");
		}
		
		if(!areConnected(sources, destinations, srcData, dstData)) {
			throw new IllegalArgumentException("There does not exist a path between the two vertices");
		}
		
		Graph graph = new Graph(sources, destinations);
		
		return graph.bfs(graph.getVertex(srcData), graph.getVertex(dstData));
			
	}

	/**
	 * This method must use the topological sort algorithm presented in lecture to generate a sorted 
	 * ordering of the vertices in the graph.  Note that a graph may have more than one valid ordering, 
	 * and any such ordering is accepted.  Throws an IllegalArgumentException if the graph contains a cycle
	 * @param <Type>
	 * @param sources
	 * @param destinations
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) throws IllegalArgumentException {
		Graph graph = new Graph(sources, destinations);
		
		if(graph.sort().size() < graph.getNumVertices()) {
			throw new IllegalArgumentException("Graph contains a cycle");
		}
		
		return graph.sort();
	}

	/**
	 * Builds "sources" and "destinations" lists according to the edges
	 * specified in the given DOT file (e.g., "a -> b"). Assumes that the vertex
	 * data type is String.
	 *
	 * Accepts many valid "digraph" DOT files (see examples posted on Canvas).
	 * --accepts \\-style comments
	 * --accepts one edge per line or edges terminated with ;
	 * --does not accept attributes in [] (e.g., [label = "a label"])
	 *
	 * @param filename - name of the DOT file
	 * @param sources - empty ArrayList, when method returns it is a valid
	 * "sources" list that can be passed to the public methods in this
	 * class
	 * @param destinations - empty ArrayList, when method returns it is a valid
	 * "destinations" list that can be passed to the public methods in
	 * this class
	 */
	public static void buildListsFromDot(String filename, ArrayList<String> sources, ArrayList<String> destinations) {
		Scanner scan = null;
		try {
			scan = new Scanner(new File(filename));
		}
		catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		scan.useDelimiter(";|\n");
		// Determine if graph is directed (i.e., look for "digraph id {").
		String line = "", edgeOp = "";
		while (scan.hasNext()) {
			line = scan.next();
			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");
			if (line.indexOf("digraph") >= 0) {
				edgeOp = "->";
				line = line.replaceFirst(".*\\{", "");
				break;
			}
		}
		if (edgeOp.equals("")) {
			System.out.println("DOT graph must be directed (i.e., digraph).");
			scan.close();
			System.exit(0);
		}
		// Look for edge operator -> and determine the source and destination
		// vertices for each edge.
		while (scan.hasNext()) {
			String[] substring = line.split(edgeOp);
			for (int i = 0; i < substring.length - 1; i += 2) {
				// remove " and trim whitespace from node string on the left
				String vertex1 = substring[0].replace("\"", "").trim();
				// if string is empty, try again
				if (vertex1.equals("")) {
					continue;
				}
				// do the same for the node string on the right
				String vertex2 = substring[1].replace("\"", "").trim();
				if (vertex2.equals("")) {
					continue;
				}
				// indicate edge between vertex1 and vertex2
				sources.add(vertex1);
				destinations.add(vertex2);
			}
			// do until the "}" has been read
			if (substring[substring.length - 1].indexOf("}") >= 0) {
				break;
			}
			line = scan.next();
			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");
		}
		scan.close();
	}
}
