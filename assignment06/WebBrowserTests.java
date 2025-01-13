package assignment06;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WebBrowserTests {
	
	WebBrowser wb;
	
	@BeforeEach
	void setup() {
		wb = new WebBrowser();
	}

	@Test
	void testBack() {
		try {
			wb.visit(new URL("https://a"));
			wb.visit(new URL("https://b"));
			wb.visit(new URL("https://c"));
			assertEquals("https://b", wb.back().toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testBackEmpty() {
		assertThrows(NoSuchElementException.class, () -> { wb.back().toString(); });
		}
	
	@Test
	void testForwardEmpty() {
		assertThrows(NoSuchElementException.class, () -> { wb.forward().toString(); });
		}
	
	@Test
	void testForward() {
		try {
			wb.visit(new URL("https://a"));
			wb.visit(new URL("https://b"));
			wb.visit(new URL("https://c"));
			wb.back();
			assertEquals("https://c", wb.forward().toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testHistory() {
		try {
			wb.visit(new URL("https://a"));
			wb.visit(new URL("https://b"));
			wb.visit(new URL("https://c"));
			wb.visit(new URL("https://d"));
			SinglyLinkedList<URL> list = wb.history();
			assertEquals("https://d", list.get(0).toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testHistory1() {
		try {
			wb.visit(new URL("https://a"));
			wb.visit(new URL("https://b"));
			wb.visit(new URL("https://c"));
			wb.visit(new URL("https://d"));
			SinglyLinkedList<URL> list = wb.history();
			assertEquals("https://c", list.get(1).toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testHistory2() {
		try {
			wb.visit(new URL("https://a"));
			wb.visit(new URL("https://b"));
			wb.visit(new URL("https://c"));
			wb.visit(new URL("https://d"));
			SinglyLinkedList<URL> list = wb.history();
			assertEquals("https://b", list.get(2).toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testHistory3() {
		try {
			wb.visit(new URL("https://a"));
			wb.visit(new URL("https://b"));
			wb.visit(new URL("https://c"));
			wb.visit(new URL("https://d"));
			SinglyLinkedList<URL> list = wb.history();
			assertEquals("https://a", list.get(3).toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testHistoryConstructor() {
		try {
			wb.visit(new URL("https://a"));
			wb.visit(new URL("https://b"));
			wb.visit(new URL("https://c"));
			wb.visit(new URL("https://d"));
			SinglyLinkedList<URL> list = wb.history();
			WebBrowser wb1 = new WebBrowser(list);
			assertEquals("https://c", wb1.back().toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}
}
