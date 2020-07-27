package com.foxminded.charcounter;

import java.util.LinkedHashMap;
import java.util.Map;

public class CharCounter {
	public String count(String input) {
		Map<String, Integer> numbersOfCharInString = new LinkedHashMap<>();

		String[] chars = input.split("");

		for (String it : chars) {
			numbersOfCharInString.merge(it, 1, (prev, one) -> prev + one);
		}

		return generateRawRepresentation(numbersOfCharInString);
	}

	private String generateRawRepresentation(Map<String, Integer> numbersOfCharInString) {
		String result = "";

		for (Map.Entry<String, Integer> entry : numbersOfCharInString.entrySet()) {
			result += decorateCharAndNumberForRawRepresentation(entry.getKey(), entry.getValue());
		}

		return result;
	}

	private String decorateCharAndNumberForRawRepresentation(String c, int number) {
		return "\"" + c + "\"" + " - " + number + "\n";
	}
}
