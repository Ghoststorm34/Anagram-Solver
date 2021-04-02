package edu.westga.cs3151.anagrams.solver;

import java.util.ArrayList;
import java.util.HashSet;

import edu.westga.cs3151.anagrams.data.DictionaryReader;

/**
 * The Class AnagramFinder
 * 
 * @author CS3151
 * @version Spring 2021
 */
public class AnagramFinder {

	private DictionaryReader reader;
	private HashSet<String> dictionary;

	/**
	 * Instantiates a new anagram finder
	 * 
	 * The dictionary file must be a plain text file. Each line in the file must
	 * contain exactly one word.
	 * 
	 * @param filename the name of the dictionary file used by this anagram finder
	 */
	public AnagramFinder(String filename) {
		this.reader = new DictionaryReader();
		this.reader.readFile(filename);
		this.dictionary = this.reader.getDictionary();
	}

	/**
	 * Returns a list of anagrams that can be formed from the specified letters and
	 * that are formed from words of this anagram-finder's dictionary
	 * 
	 * @param letters the letters to be used by the anagrams
	 * @return a list of anagrams where each anagram is a list of strings
	 */
	public ArrayList<ArrayList<String>> findAnagrams(String letters) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		HashSet<String> dictionaryForSolve = this.removeUnmatchedWords(letters);

		this.solveAnagram(result, new ArrayList<String>(), letters, dictionaryForSolve);
		return result;
	}

	private void solveAnagram(ArrayList<ArrayList<String>> result, ArrayList<String> currentWords, String phrase,
			HashSet<String> dictionaryForSolve) {
		for (String word : dictionaryForSolve) {
			String newPhrase = this.preparePhraseForSolve(phrase, word);

			if (this.isValidAnagram(phrase, word, newPhrase)) {
				ArrayList<String> newPath = new ArrayList<String>(currentWords);
				newPath.add(word);
				this.solveAnagram(result, newPath, newPhrase, dictionaryForSolve);
			}
		}
		if (this.recursionOver(currentWords, phrase)) {
			result.add(currentWords);
		}
	}

	private String preparePhraseForSolve(String phrase, String word) {
		String tempPhrase = phrase;

		for (int i = 0; i < word.length(); i++) {
			char currLetter = word.charAt(i);
			tempPhrase = tempPhrase.replaceFirst(currLetter + "", "");
		}

		return tempPhrase;
	}

	private boolean isValidAnagram(String phrase, String word, String newPhrase) {
		return phrase.length() - newPhrase.length() == word.length();
	}

	private boolean recursionOver(ArrayList<String> currentWords, String phrase) {
		return phrase.length() == 0 && !currentWords.isEmpty();
	}

	private boolean contains(char item, char[] checkedArray) {
		for (char current : checkedArray) {
			if (item == current) {
				return true;
			}
		}
		return false;
	}


	private HashSet<String> removeUnmatchedWords(String letters) {
		char[] lettersArray = letters.toCharArray();
		HashSet<String> dictionaryForSolve = new HashSet<String>();
		for (String word : this.dictionary) {
			char[] wordArray = word.toCharArray();
			boolean contained = false;
			for (char currChar : wordArray) {
				if (!this.contains(currChar, lettersArray)) {
					contained = false;
					break;
				} else {
					contained = true;
				}
				if (contained) {
					dictionaryForSolve.add(word);
				}
			}
		}
		return dictionaryForSolve;
	}
}
