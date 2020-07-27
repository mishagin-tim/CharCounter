package com.foxminded.charcounter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CharCounterTest {
	private CharCounter charCounter;

	@BeforeEach
	void setUp() {
		charCounter = new CharCounter();
	}

	@Test
	void countShouldThrowIllegalArgumentExceptionWhenNullIsPassed() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> charCounter.count(null));

		String expected = "Input arguments can't be null";

		String actual = exception.getMessage();

		assertEquals(expected, actual);
	}
	
	@Test
	void countShouldThrowIllegalArgumentExceptionWhenEmptyStringIsPassed() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> charCounter.count(""));

		String expected = "Input string can't be empty";

		String actual = exception.getMessage();

		assertEquals(expected, actual);
	}

	@Test
	void countShouldReturnOneCharWhenOneCharStringIsPassed() {
		String expected = "\"h\" - 1\n";

		String actual = charCounter.count("h");
		
		assertEquals(expected, actual);
	}

	@Test
	void countShouldReturnMapWithCharCountWhenStringIsPassed() {
		String expected = "\"h\" - 1\n" +
						  "\"e\" - 1\n" +
						  "\"l\" - 3\n" +
						  "\"o\" - 2\n" +
						  "\" \" - 1\n" +
						  "\"w\" - 1\n" +
						  "\"r\" - 1\n" +
						  "\"d\" - 1\n" +
						  "\"!\" - 1\n";

		String actual = charCounter.count("hello world!");

		assertEquals(expected, actual);
		
		expected = "\"g\" - 1\n" + 
				   "\"o\" - 2\n" + 
				   "\"d\" - 1\n" + 
				   "\"b\" - 1\n" + 
				   "\"y\" - 1\n" + 
				   "\"e\" - 1\n" + 
				   "\"!\" - 1\n";

		actual = charCounter.count("goodbye!");

		assertEquals(expected, actual);
	}

	@Test
	void countShouldReturnEqualMapWhenTwoEqualStringsArePassedOneAfterAnother() {
		String first = charCounter.count("Ivan");
		String second = charCounter.count("Ivan");

		boolean isEqual = first.equals(second);

		assertTrue("Strings are not equal", isEqual);
	}

	@Test
	void countShouldReturnNotEqualMapsWhenTwoNotEqualStringsArePassed() {
		String first = charCounter.count("Ivan");
		String second = charCounter.count("Petr");

		boolean isEqual = first.equals(second);

		assertFalse("Strings are equal", isEqual);
	}

	@Test
	void countShouldReturnDiffentMapsWhenOneHasUppercaseWhileAnotherHasLowerCase() {
		String first = charCounter.count("abracadabra");
		String second = charCounter.count("AbrAcaDaBRa");

		boolean isEqual = first.equals(second);

		assertFalse("Strings are equal", isEqual);

		first = charCounter.count("hello world!");
		second = charCounter.count("Hello world!");

		isEqual = first.equals(second);

		assertFalse("Strings are equal", isEqual);
	}
}
