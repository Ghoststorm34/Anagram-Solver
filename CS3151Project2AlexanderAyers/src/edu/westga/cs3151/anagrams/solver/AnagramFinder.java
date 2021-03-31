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
		char[] lettersArray = letters.toCharArray();
		/**
		 * First as a separate private helper method, check if each word in the
		 * dictionary is contained within letters. If not, remove word, if it is keep
		 * it. Loop through using each string as a list of characters, if not all of the
		 * characters from the dictionary word are found, remove it.
		 * 
		 * Secondly, make another private helper method that accepts parameter of
		 * result. Loop through dictionary, on match use recursion to go through again
		 * with word added to array list. Once letters is empty, add anagram to result.
		 * If nothing is found, exit execution. Maintain current result as a parameter.
		 */
		this.solveAnagram(result, dictionaryForSolve, lettersArray);
		return result;
	}

	private HashSet<String> removeUnmatchedWords(String letters) {
		char[] lettersArray = letters.toCharArray();
		HashSet<String> dictionaryForSolve = new HashSet<String>();
		for (String word : this.dictionary) {
			boolean contained = this.containsLetters(word, lettersArray);
			if (contained) {
				dictionaryForSolve.add(word);
			}
		}
		return dictionaryForSolve;
	}
	
	private void solve() {
		
	}
	
	private void solveAnagram(ArrayList<ArrayList<String>> result, HashSet<String> dictionary, char[] lettersArray) {
		for (String word : dictionary) {
			if (this.containsLetters(word, lettersArray)) {
				// TODO Remove letters from word from letters array, could use the string of
				// letters array.
				// TODO Recursive Call
				// TODO If/Else statement, if lettersArray.size() == empty, return, else add
				// result to new phrase. Add the result to a new ArrayList.
			}
		}
	}

	private boolean contains(char item, char[] checkedArray) {
		for (char current : checkedArray) {
			if (item == current) {
				return true;
			}
		}
		return false;
	}
	
	private void removeLetters(char[] wordArray, char[] letterArray) {
		
	}

	private boolean containsLetters(String word, char[] letters) {
		char[] wordArray = word.toCharArray();
		boolean contained = false;

		for (char currentChar : wordArray) {
			if (this.contains(currentChar, letters)) {
				contained = false;
				break;
			} else {
				contained = true;
			}
		}

		return contained;
	}
}
