package assignment06;

import java.net.URL;
import java.util.NoSuchElementException;

/**
 * This class represents a Web Browser
 * @author Sachin Jampala and Jadon Olson
 * @version June 21, 2023
 *
 */
public class WebBrowser {

	private Stack<URL> forward;
	private Stack<URL> backward;
	private URL current;
	
	/**
	 * This constructor creates a new web browser with no previously-visited 
	 * webpages and no webpages to visit next.
	 */
	public WebBrowser() {
		forward = new LinkedListStack<>();
		backward = new LinkedListStack<>();
		current = null;
	}
	
	/**
	 * This constructor creates a new web browser with a preloaded history 
	 * of visited webpages, given as a list of URL objects.  The first webpage in 
	 * the list is the "current" webpage visited, and the remaining webpages are 
	 * ordered from most recently visited to least recently visited.
	 * @param history - list of URLs
	 */
	public WebBrowser(SinglyLinkedList<URL> history) {
		forward = new LinkedListStack<>();
		backward = new LinkedListStack<>();
		current = history.deleteFirst();
		Stack<URL> temp = new LinkedListStack<>();
		
		for(int i = 0; i < history.size(); i++) {
			temp.push(history.get(i));
		}
		
		while(!temp.isEmpty()) {
			backward.push(temp.pop());
		}
	}
	/**
	 * This method simulates visiting a webpage, given as a URL.   
	 * Calling this method clears the forward button stack, since there is no URL 
	 * to visit next.
	 * @param webpage - the webpage being visited
	 */
	public void visit(URL webpage) { 
		forward.clear();
		if(current == null) {
			current = webpage;
			return;
		}
		backward.push(current);
		current = webpage;
	}
	
	/**
	 * This method simulates using the back button, returning the URL visited. 
	 * NoSuchElementException is thrown if there is no previously-visited URL.
	 * @return - returns the previous URL
	 * @throws NoSuchElementException - is thrown if there is no previously-visited URL.
	 */
	public URL back() throws NoSuchElementException { 
		if(backward.isEmpty())
			throw new NoSuchElementException("No previously visted URL");
		forward.push(current);
		current = backward.pop();
		return current;
	}
	
	/**
	 * This method simulates using the forward button, returning the URL visited. 
	 * NoSuchElementException is thrown if there is no URL to visit next.
	 * @return - the next URL
	 * @throws NoSuchElementException - is thrown if there is no URL to visit next.
	 */
	public URL forward() throws NoSuchElementException { 
		if(forward.isEmpty())
			throw new NoSuchElementException("No URL to visit next");
		backward.push(current);
		current = forward.pop();
		return current;
	}
	
	/**
	 * This method generates a history of URLs visited, as a list of URL objects
	 * ordered from most recently visited to least recently visited 
	 * without altering subsequent behavior of this web browser.  "Forward" URLs are 
	 * not included.
	 * @return - a list of previously visited URLs
	 */
	public SinglyLinkedList<URL> history(){
		Stack<URL> temp = new LinkedListStack<URL>();
		SinglyLinkedList hist = new SinglyLinkedList<URL>();
		while(!backward.isEmpty()) {
			temp.push(backward.pop());
		}
		while(!temp.isEmpty()) {
			hist.insertFirst(temp.peek());
			backward.push(temp.pop());
		}
		hist.insertFirst(current);
		return hist;
	}
}
