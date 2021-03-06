package com.leetcode.oj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Substring with Concatenation of All Words
 * 
 * You are given a string, s, and a list of words, words, that are all of the same length. 
 * Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
 * 
 * For example, given: s: "barfoothefoobarman" words: ["foo", "bar"]
 * 
 * You should return the indices: [0,9]. (order does not matter).
 * 
 * @author rekinyz
 *
 */
public class SubstringConcatenationAllWords {
	public List<Integer> findSubstring(String s, String[] words) {
		List<Integer> result = new ArrayList<>();
		int wordsNum = words.length;
		if (wordsNum != 0) {
			int len = words[0].length();
			int start = 0, end = len * wordsNum;
			while (end < s.length()) {
				for (String combi : permutation(words)) {
					if (s.substring(start, end).equals(combi)) {
						result.add(start);
					}
				}
				start += len;
				end += len;
			}
		}
		return result;
	}

	public Set<String> permutation(String[] words) {
		Set<String> set = new HashSet<>();
		permutation("", words, set);
		return set;
	}
	
	private void permutation(String prefix, String[] words, Set<String> set) {
		int len = words.length;
		if (len == 0) {
			set.add(prefix);
		} else {
			for (int i = 0; i < len; i++) {
				String[] head = Arrays.copyOfRange(words, 0, i);
				String[] tail = Arrays.copyOfRange(words, i + 1, len);
				permutation(prefix + words[i], concat(head, tail), set);
			}
		}
	}

	public String[] concat(String[] a, String[] b) {
		int aLen = a.length;
		int bLen = b.length;
		String[] c = new String[aLen + bLen];
		System.arraycopy(a, 0, c, 0, aLen);
		System.arraycopy(b, 0, c, aLen, bLen);
		return c;
	}
}
