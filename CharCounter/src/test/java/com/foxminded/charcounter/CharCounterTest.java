package com.foxminded.charcounter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

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
		String expected = "{h=1}";

		String actual = charCounter.count("h").toString();
		
		assertEquals(expected, actual);
	}

	@Test
	void countShouldReturnNumberOfEmptyCharactersWhenStringHasOnlyEmptyCharacters() {
		String expected = "{ =4}";

		String actual = charCounter.count("    ").toString();

		assertEquals(expected, actual);
	}

	@Test
	void countShouldReturnMapWithCharCountWhenStringIsPassed() {
		String expected = "{h=1, e=1, l=3, o=2,  =1, w=1, r=1, d=1, !=1}";

		String actual = charCounter.count("hello world!").toString();

		assertEquals(expected, actual);
		
		expected = "{g=1, o=2, d=1, b=1, y=1, e=1, !=1}";

		actual = charCounter.count("goodbye!").toString();

		assertEquals(expected, actual);
	}

	@Test
	void countShouldReturnEqualMapWhenTwoEqualStringsArePassedOneAfterAnother() {
		Map<String, Integer> first = charCounter.count("Ivan");
		Map<String, Integer> second = charCounter.count("Ivan");

		boolean isEqual = first.equals(second);

		assertTrue("Strings are not equal", isEqual);
	}

	@Test
	void countShouldReturnNotEqualMapsWhenTwoNotEqualStringsArePassed() {
		Map<String, Integer> first = charCounter.count("Ivan");
		Map<String, Integer> second = charCounter.count("Petr");

		boolean isEqual = first.equals(second);

		assertFalse("Strings are equal", isEqual);
	}

	@Test
	void countShouldReturnDiffentMapsWhenOneHasUppercaseWhileAnotherHasLowerCase() {
		Map<String, Integer> first = charCounter.count("abracadabra");
		Map<String, Integer> second = charCounter.count("AbrAcaDaBRa");

		boolean isEqual = first.equals(second);

		assertFalse("Strings are equal", isEqual);

		first = charCounter.count("hello world!");
		second = charCounter.count("Hello world!");

		isEqual = first.equals(second);

		assertFalse("Strings are equal", isEqual);
	}
}
