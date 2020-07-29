package com.foxminded.charcounter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CharCounter {
	private Map<String, Map<Character, Integer>> cache;

	public CharCounter() {
		cache = new HashMap<>();
	}

	public Map<Character, Integer> count(String input) {
		notNullAndNotEmptyRequired(input);

		if (cache.containsKey(input)) {
			return cache.get(input);
		} else {
			Map<Character, Integer> newCount = countAndGet(input);

			cache.put(input, newCount);

			return newCount;
		}
	}

	private void notNullAndNotEmptyRequired(String input) {
		if (input == null) {
			throw new IllegalArgumentException("Input arguments can't be null");
		}

		if (input.isEmpty()) {
			throw new IllegalArgumentException("Input string can't be empty");
		}
	}

	private Map<Character, Integer> countAndGet(String str) {
		Map<Character, Integer> numbersOfCharInString = new LinkedHashMap<>();

		char[] chars = str.toCharArray();

		for (Character it : chars) {
			numbersOfCharInString.merge(it, 1, (prev, one) -> prev + one);
		}

		return numbersOfCharInString;
	}
}
