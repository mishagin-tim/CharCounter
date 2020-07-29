package com.foxminded.charcounter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashMap;
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
		Map<Character, Integer> expected = new LinkedHashMap<>();
		expected.put('h', 1);

		Map<Character, Integer> actual = charCounter.count("h");
		
		assertEquals(expected, actual);
	}

	@Test
	void countShouldReturnNumberOfSpaceCharactersWhenStringHasOnlySpaceCharacters() {
		Map<Character, Integer> expected = new LinkedHashMap<>();

		expected.put(' ', 4);

		Map<Character, Integer> actual = charCounter.count("    ");

		assertEquals(expected, actual);
	}

	@Test
	void countShouldReturnMapWithCharCountWhenStringIsPassed() {
		Map<Character, Integer> expected = new LinkedHashMap<>();

		expected.put('h', 1);
		expected.put('e', 1);
		expected.put('l', 3);
		expected.put('o', 2);
		expected.put(' ', 1);
		expected.put('w', 1);
		expected.put('r', 1);
		expected.put('d', 1);
		expected.put('!', 1);

		Map<Character, Integer> actual = charCounter.count("hello world!");

		assertEquals(expected, actual);
	}

	@Test
	void countShouldReturnEqualMapWhenTwoEqualStringsArePassedOneAfterAnother() {
		Map<Character, Integer> first = charCounter.count("Ivan");
		Map<Character, Integer> second = charCounter.count("Ivan");

		boolean isEqual = first.equals(second);

		assertTrue(isEqual, "Maps are not equal");
	}

	@Test
	void countShouldReturnNotEqualMapsWhenTwoNotEqualStringsArePassed() {
		Map<Character, Integer> first = charCounter.count("Ivan");
		Map<Character, Integer> second = charCounter.count("Petr");

		boolean isEqual = first.equals(second);

		assertFalse(isEqual, "Maps are equal");
	}

	@Test
	void countShouldReturnDiffentMapsWhenOneHasUppercaseWhileAnotherHasLowerCase() {
		Map<Character, Integer> first = charCounter.count("abracadabra");
		Map<Character, Integer> second = charCounter.count("AbrAcaDaBRa");

		boolean isEqual = first.equals(second);

		assertFalse(isEqual, "Maps are equal");
	}

	@Test
	void countShouldReturnDiffentMapsWhenOneHasUppercaseWithSpacesWhileAnotherHasLowerCaseWithSpaces() {
		Map<Character, Integer> first = charCounter.count("hello world!");
		Map<Character, Integer> second = charCounter.count("Hello world!");

		boolean isEqual = first.equals(second);

		assertFalse(isEqual, "Maps are equal");
	}
}
