
/*
 * Bitcamp 2017
 * 
 * Lum, Clark, Zack, Rahul
 * 
 * markov chain that holds what notes and lengths of notes are most likely to come after another note
 */

import java.util.ArrayList;

public class MarkovChain {

	// instance variables
	// values holds note value (0-36)
	// length holds length of note as a double
	// position in outside arraylist corresponds to a note value
	// position on inner arraylist corresponds to a possible next note
	private ArrayList<ArrayList<Integer>> noteValues;
	private ArrayList<ArrayList<Double>> noteLengths;
	public static int[][] table;

	public MarkovChain() {

		table = new int[10][12];
		int count = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 12; j++) {
				table[i][j] = count;
				count++;
			}
		}

		noteValues = new ArrayList<ArrayList<Integer>>();
		noteLengths = new ArrayList<ArrayList<Double>>();

		// initializes a new array list for every note
		for (int numberOfNotes = 0; numberOfNotes < 128; numberOfNotes++) {
			noteValues.add(new ArrayList<Integer>());
			noteLengths.add(new ArrayList<Double>());
		}

	}// end constructor

	// adds a note (value and length) to the chain
	// note is added as a possible next note for a previous note previousValue
	public void addNextNote(int previousValue, int value, double length) {
		noteValues.get(previousValue).add(value);
		noteLengths.get(previousValue).add(length);
	}// end addNextNote

	/// returns a list of notes that make a song
	// formated as alternating values and lengths
	// value, length, value, length,...
	public ArrayList<Double> getValuesOfSong(int sizeOfSong) {
		// notes stored in this arraylist
		ArrayList<Double> answer = new ArrayList<Double>();

		// assigns starting note value to current value
		int currentValue = (int) (128 * Math.random());
		while (noteValues.get(currentValue).size() == 0) {
			currentValue = (int) (128 * Math.random());
		}

		// loops through creating sizeOfSong number of notes
		for (int x = 0; x < sizeOfSong; x++) {

			// position in inner arraylist that
			int randomNumber = (int) ((noteValues.get(currentValue).size()) * Math.random());
			int next = 0;

			// if the note currentValue has a possible next note
			if (noteValues.get(currentValue).size() != 0)
				next = noteValues.get(currentValue).get(randomNumber);
			else {
				// chooses a random note to go to

				while (noteValues.get(currentValue).size() == 0) {
					currentValue = (int) (128 * Math.random());
				}

				// reset the random number so that it will not be out of bounds
				// of its new arraylist
				randomNumber = (int) ((noteValues.get(currentValue).size()) * Math.random());
			}
			// add value to array list
			answer.add((double) (next));
			// add length to array list
			answer.add(noteLengths.get(currentValue).get(randomNumber));

			currentValue = next;
		}

		return answer;

	}// end getValuesOfSong

	// clark
	public static int tableValue(String s) {
		int col = 0;
		if (s.substring(0, 1).equals("C")) {
			col = 0;
		} else if (s.substring(0, 2).equals("Db")) {
			col = 1;
		} else if (s.substring(0, 1).equals("D")) {
			col = 2;
		} else if (s.substring(0, 2).equals("Eb")) {
			col = 3;
		} else if (s.substring(0, 1).equals("E")) {
			col = 4;
		} else if (s.substring(0, 1).equals("F")) {
			col = 5;
		} else if (s.substring(0, 2).equals("Gb")) {
			col = 6;
		} else if (s.substring(0, 1).equals("G")) {
			col = 7;
		} else if (s.substring(0, 2).equals("Ab")) {
			col = 8;
		} else if (s.substring(0, 1).equals("A")) {
			col = 9;
		} else if (s.substring(0, 2).equals("Bb")) {
			col = 10;
		} else if (s.substring(0, 1).equals("B")) {
			col = 11;
		}
		return table[Integer.parseInt(s.substring(s.length() - 1, s.length()))][col];
	}

}// end class
