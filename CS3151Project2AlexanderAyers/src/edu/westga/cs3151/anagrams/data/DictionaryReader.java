package edu.westga.cs3151.anagrams.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Converts a file into a HashSet.
 * 
 * @author Alexander Ayers
 * @version Spring 2021
 *
 */
public class DictionaryReader {

	/** The dictionary. */
	private HashSet<String> dictionary;

	/**
	 * Zero-parameter constructor.
	 * 
	 * @precondition none
	 * @postcondition getDictionary() == new HashSet
	 */
	public DictionaryReader() {
		this.dictionary = new HashSet<String>();
	}

	/**
	 * Reads file containing a set of words and adding them to the dictionary.
	 * 
	 * @precondition fileName must exist AND fileName != null AND
	 *               !fileName.isBlank()
	 * @postcondition fileName existing IMPLIES getDictionary() containing all words
	 *                within file AND getDictionary().size() == @pre + 1
	 * @param fileName the filename
	 */
	public void readFile(String fileName) {
		try {
			File specifiedFile = new File(fileName);
			Scanner reader = new Scanner(specifiedFile);
			while	(reader.hasNextLine()) {
				String line = reader.nextLine();
				this.dictionary.add(line);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the dictionary.
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the dictionary
	 */
	public HashSet<String> getDictionary() {
		return this.dictionary;
	}
}
