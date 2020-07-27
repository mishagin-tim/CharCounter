package com.foxminded.charcounter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CharCounter {
	private Map<String, Map<String, Integer>> pairsOfStringAndNumberOfCharacterMap;

	public CharCounter() {
		pairsOfStringAndNumberOfCharacterMap = new HashMap<>();
	}

	public String count(String input) {
		if (input == null) {
			throw new IllegalArgumentException("Input arguments can't be null");
		}

		if (input.isEmpty()) {
			throw new IllegalArgumentException("Input string can't be empty");
		}

		Map<String, Integer> numbersOfCharInString = pairsOfStringAndNumberOfCharacterMap.get(input);

		if (numbersOfCharInString != null) {
			return generateRawRepresentation(numbersOfCharInString);
		}

		numbersOfCharInString = calculateCharactersOfString(input);

		return generateRawRepresentation(numbersOfCharInString);
	}

	private Map<String, Integer> calculateCharactersOfString(String str) {
		Map<String, Integer> numbersOfCharInString = new LinkedHashMap<>();

		String[] chars = str.split("");

		for (String it : chars) {
			numbersOfCharInString.merge(it, 1, (prev, one) -> prev + one);
		}

		return numbersOfCharInString;
	}

	private String generateRawRepresentation(Map<String, Integer> numbersOfCharInString) {
		StringBuilder resultString = new StringBuilder("");

		for (Map.Entry<String, Integer> entry : numbersOfCharInString.entrySet()) {
			resultString.append(decorateCharAndNumberForRawRepresentation(entry.getKey(), entry.getValue()));
		}

		return resultString.toString();
	}

	private String decorateCharAndNumberForRawRepresentation(String c, int number) {
		return "\"" + c + "\"" + " - " + number + "\n";
	}
}
