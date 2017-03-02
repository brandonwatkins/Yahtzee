/**
 * Brandon Watkins
 * 16 Feb 2017
 * Simple Bahtzee program class that
 * simulates a dice rolling
 */

public class Dice {

	private final static int NUM_FACES = 6;
	private int faceValue;
	private int numRolls = 0;

	public int getNumRolls() {
		return numRolls;
	}

	public void setNumRolls(int numRolls) {
		this.numRolls = numRolls;
	}

	public Dice() {
		faceValue = 1;
	}

	public Dice(int value) {
		faceValue = value;
	}


	public int roll() {
		faceValue = (int)(Math.random() * NUM_FACES) + 1;
		numRolls++;
		return faceValue;
	}


	public void setFaceValue (int value) {
		faceValue = value;
	}


	public int getFaceValue() {
		//String result = Integer.toString(faceValue);
		return faceValue;
	}



}

