/**
 * Brandon Watkins
 * 16 Feb 2017
 * Simple Bahtzee program class that
 * simulates a dice rolling
 */

public class Dice {

	private final static int NUM_FACES = 6;
	private static int faceValue;
	private static int numRolls = 0;

	public static int getNumRolls() {
		return numRolls;
	}

	public static void setNumRolls(int numRolls) {
		Dice.numRolls = numRolls;
	}

	public Dice() {
		faceValue = 1;
	}

	public Dice(int value) {
		faceValue = value;
	}


	public static int roll() {
		faceValue = (int)(Math.random() * NUM_FACES) + 1;
		numRolls++;
		return faceValue;
	}


	public void setFaceValue (int value) {
		faceValue = value;
	}


	public static int getFaceValue() {
		//String result = Integer.toString(faceValue);
		return faceValue;
	}



}

