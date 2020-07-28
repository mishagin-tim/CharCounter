package com.foxminded.charcounter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CharCounter {
	private Map<String, Map<String, Integer>> cache;

	public CharCounter() {
		cache = new HashMap<>();
	}

	public Map<String, Integer> count(String input) {
		notNullAndNotEmptyRequired(input);

		if (cache.containsKey(input)) {
			return cache.get(input);
		} else {
			Map<String, Integer> newCount = countAndGet(input);

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

	private Map<String, Integer> countAndGet(String str) {
		Map<String, Integer> numbersOfCharInString = new LinkedHashMap<>();

		String[] chars = str.split("");

		for (String it : chars) {
			numbersOfCharInString.merge(it, 1, (prev, one) -> prev + one);
		}

		return numbersOfCharInString;
	}
}
