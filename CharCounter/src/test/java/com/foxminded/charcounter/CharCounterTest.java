package com.foxminded.charcounter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CharCounterTest {
	private CharCounter charCounter;

	@BeforeEach
	void setUp() {
		charCounter = new CharCounter();
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
	}
}
