package ohm.softa.a02.tests;

import ohm.softa.a02.SimpleFilter;
import ohm.softa.a02.SimpleListImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 2) Check the given test suite for an example on
 *    ...how to use an anonymous class with an interface: testFilterAnonymousClass()
 *    ...how an anonymous class can be replaced by a lambda expression: testFilterLambda()
 *
 * 3) Add some test methods and implement another filter logic
 *    (e.g. every third number, or any number smaller than a certain value).
 */
public class SimpleListTest {

	private SimpleListImpl testList;

	@BeforeEach
	void setup(){
		testList = new SimpleListImpl();

		testList.add(1);
		testList.add(2);
		testList.add(3);
		testList.add(4);
		testList.add(5);
	}

	@Test
	void testAddElements(){
		int counter = 0;
		for(Object o : testList){
			counter++;
		}
		assertEquals(5, counter);
	}

	@Test
	void testSize(){
		assertEquals(5, testList.size());
	}

	@Test
	void testFilterAnonymousClass(){
		SimpleListImpl result = (SimpleListImpl) testList.filter(new SimpleFilter() {
			@Override
			public boolean include(Object item) {
				int current = (int)item;
				return current > 2;
			}
		});

		for(Object o : result){
			int i = (int)o;
			assertTrue(i > 2);
		}
	}

	@Test
	void testFilterLambda(){
		SimpleListImpl result = (SimpleListImpl) testList.filter(o -> ((int)o) % 2 == 0);
		for(Object o : result){
			int i = (int)o;
			assertTrue(i % 2 == 0); // ODER: assertEquals(0, i % 2);
		}
	}

	@Test
	void testFilterSmallerFour(){
		SimpleListImpl result = (SimpleListImpl) testList.filter(o -> ((int)o) < 4);
		for(Object o : result){
			int i = (int)o;
			assertTrue(i < 4);
		}
	}
}
