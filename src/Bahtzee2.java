/**
 * Brandon Watkins
 * 16 Feb 2017
 * This is a GUI Yahtzee Program
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;


public class Bahtzee2 {

	public static void main(String[] args) {
		new Bahtzee2().go();
	}
	
	/******************* GUI SETUP **********************/
	private JFrame frame;
	private JButton rollButton;
	private JButton resetButton;
	private int numRolls = 0; 
	
	private JPanel buttonPanel;
	private JPanel middlePanel;
	private int rollCounter;
	private int results[] = {0, 0, 0, 0, 0, 0};
	private String title = "Yahtzee";
	
	private final ImageIcon DICE_BLANK	= new ImageIcon( "images/blank.png");
	private final ImageIcon DICE_1 		= new ImageIcon( "images/1.png");	
	private final ImageIcon DICE_2	 	= new ImageIcon( "images/2.png");
	private final ImageIcon DICE_3 	 	= new ImageIcon( "images/3.png");	
	private final ImageIcon DICE_4	 	= new ImageIcon( "images/4.png");
	private final ImageIcon DICE_5	 	= new ImageIcon( "images/5.png");
	private final ImageIcon DICE_6	 	= new ImageIcon( "images/6.png");
		
	private ImageIcon DICE_IMAGES[] = {
			DICE_1,
			DICE_2,
			DICE_3,
			DICE_4,
			DICE_5,
			DICE_6
	};	
	
	private ArrayList<JToggleButton> buttons = new ArrayList<JToggleButton>();
	private ArrayList<Dice> dice = new ArrayList<Dice>();
	/******************* GUI SETUP **********************/
	
	public void go() {
		
		/******************* GUI SETUP **********************/
		frame = new JFrame("Bahtzee");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
		middlePanel = new JPanel();
		
		setUpLogo();
		setupUpperSection();
		setupLowerSection();
		
		middlePanel.setLayout(new FlowLayout());
		frame.add(middlePanel, BorderLayout.CENTER);
		
		setupRollSection();

		Constants.center(frame);
		frame.setSize(600, 530);
		frame.setVisible(true);
		/******************* GUI SETUP **********************/
	}
	
	/******************* GUI SETUP **********************/
	private void setupRollSection() {	

		buttonPanel = new JPanel();
		
		//Setup buttons
		for(int i = 0; i < 5; i++){
			JToggleButton button = new JToggleButton();
			button.setIcon(DICE_BLANK);
			
			buttons.add(button);
			buttonPanel.add(button);
			dice.add(new Dice());
		}
		
		JPanel rollPanel = new JPanel();
		rollPanel.setLayout(new FlowLayout());

		
		rollButton = new JButton("Roll");
		rollButton.addActionListener(new RollButtonListener());
		rollButton.setPreferredSize(new Dimension(90,66));
		
		resetButton = new JButton("Reset");
		resetButton.setEnabled(false);
		resetButton.setPreferredSize(new Dimension(90,66));
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				resetDice();
			}
		});

		rollPanel.add(resetButton);
		rollPanel.add(rollButton);
		rollPanel.add(buttonPanel);
		
		frame.getContentPane().add(rollPanel, BorderLayout.SOUTH);
		
	}

	
	private void enableDiceButtons(boolean flag){
		for(JToggleButton b : buttons){
			b.setEnabled(flag);
		}
	}
	
	private void rollDice() {
		for(int i = 0; i < buttons.size(); i++){
			JToggleButton button = buttons.get(i);
			if(!button.isSelected())
				button.setIcon(DICE_IMAGES[dice.get(i).roll() - 1]);
		}
		frame.setTitle(title + " - Roll " + rollCounter);
	}
	
	private void resetDice() {
		for(JToggleButton b : buttons){
			b.setEnabled(false);
			b.setSelected(false);
			b.setIcon(DICE_BLANK);
		}
		frame.setTitle(title);
		rollCounter = 0;
		rollButton.setEnabled(true);
	}
	
	private void setupLowerSection() {
		JPanel LowerSection = new JPanel();
		LowerSection.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		TitledBorder titled = new TitledBorder("Lower Section");
		LowerSection.setBorder(titled);

		JButton threeKindButton = new JButton("3 of a kind");
		gbc.gridx = 0;
		gbc.gridy = 0;
		threeKindButton.setPreferredSize(new Dimension(100, 30));
		LowerSection.add(threeKindButton, gbc);

		JButton fourKindButton = new JButton("4 of a kind");
		fourKindButton.setPreferredSize(new Dimension(100, 30));
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 1;
		LowerSection.add(fourKindButton, gbc);

		JButton fullHouseButton = new JButton("Full House");
		fullHouseButton.setPreferredSize(new Dimension(100, 30));
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 2;
		LowerSection.add(fullHouseButton, gbc);

		JButton smStraightButton = new JButton("Sm. Straight");
		smStraightButton.setPreferredSize(new Dimension(100, 30));
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 3;
		LowerSection.add(smStraightButton, gbc);
	
		JButton lgStraightButton = new JButton("Lg. Straight");
		lgStraightButton.setPreferredSize(new Dimension(100, 30));
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 4;
		LowerSection.add(lgStraightButton, gbc);
		
		JButton yahtzeeButton = new JButton("Yahtzee");
		yahtzeeButton.setPreferredSize(new Dimension(100, 30));
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 5;
		LowerSection.add(yahtzeeButton, gbc);
		
		JButton chanceButton = new JButton("Chance");
		chanceButton.setPreferredSize(new Dimension(100, 30));
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 6;
		LowerSection.add(chanceButton, gbc);
		
		//Corresponding TextFields 
		JTextField threeKindTextField = new JTextField("");
		threeKindTextField.setPreferredSize(new Dimension(80, 30));
		threeKindTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 0;
		LowerSection.add(threeKindTextField, gbc);
		
		JTextField fourKindTextField = new JTextField("");
		fourKindTextField.setPreferredSize(new Dimension(80, 30));
		fourKindTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 1;
		LowerSection.add(fourKindTextField, gbc);
		
		JTextField fullHouseTextField = new JTextField("");
		fullHouseTextField.setPreferredSize(new Dimension(80, 30));
		fullHouseTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 2;
		LowerSection.add(fullHouseTextField, gbc);
		
		JTextField smStraightTextField = new JTextField("");
		smStraightTextField.setPreferredSize(new Dimension(80, 30));
		smStraightTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 3;
		LowerSection.add(smStraightTextField, gbc);
		
		JTextField lgStraightTextField = new JTextField("");
		lgStraightTextField.setPreferredSize(new Dimension(80, 30));
		lgStraightTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 4;
		LowerSection.add(lgStraightTextField, gbc);
		
		JTextField yahtzeeTextField = new JTextField("");
		yahtzeeTextField.setPreferredSize(new Dimension(80, 30));
		yahtzeeTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 5;
		LowerSection.add(yahtzeeTextField, gbc);
		
		JTextField chanceTextField = new JTextField("");
		chanceTextField.setPreferredSize(new Dimension(80, 30));
		chanceTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 6;
		LowerSection.add(chanceTextField, gbc);
		
		//Totals
		JLabel lowerTotalLabel = new JLabel("Lower Section Total:");
		gbc.weightx = 1;
		gbc.gridx = 0;
		gbc.gridy = 7;
		LowerSection.add(lowerTotalLabel, gbc);
		
		JLabel grandTotalLabel = new JLabel("GRAND TOTAL:");
		gbc.weightx = 1;
		gbc.gridx = 0;
		gbc.gridy = 8;
		LowerSection.add(grandTotalLabel, gbc);
		
		JTextField lowerTotalTextField = new JTextField("");
		lowerTotalTextField.setPreferredSize(new Dimension(80, 30));
		lowerTotalTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 7;
		LowerSection.add(lowerTotalTextField, gbc);
		
		JTextField grandTotalTextField = new JTextField("");
		grandTotalTextField.setPreferredSize(new Dimension(80, 30));
		grandTotalTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 8;
		LowerSection.add(grandTotalTextField, gbc);
		
		middlePanel.add(LowerSection);		
	}

	private void setupUpperSection() {
		JPanel UpperSection = new JPanel();
		UpperSection.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		TitledBorder titled = new TitledBorder("Upper Section");
		UpperSection.setBorder(titled);

		//Buttons
		JButton acesButton = new JButton("Aces");
		gbc.gridx = 0;
		gbc.gridy = 0;
		acesButton.setPreferredSize(new Dimension(80, 30));
		UpperSection.add(acesButton, gbc);

		JButton twosbutton = new JButton("Twos");
		twosbutton.setPreferredSize(new Dimension(80, 30));
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 1;
		UpperSection.add(twosbutton, gbc);

		JButton threesButton = new JButton("Threes");
		threesButton.setPreferredSize(new Dimension(80, 30));
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 2;
		UpperSection.add(threesButton, gbc);

		JButton foursButton = new JButton("Fours");
		foursButton.setPreferredSize(new Dimension(80, 30));
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 3;
		UpperSection.add(foursButton, gbc);
	
		JButton fivesButton = new JButton("Fives");
		fivesButton.setPreferredSize(new Dimension(80, 30));
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 4;
		UpperSection.add(fivesButton, gbc);
		
		JButton sixesButton = new JButton("Sixes");
		sixesButton.setPreferredSize(new Dimension(80, 30));
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 5;
		UpperSection.add(sixesButton, gbc);
		
		//Corresponding TextFields 
		JTextField onesTextField = new JTextField("");
		onesTextField.setPreferredSize(new Dimension(80, 30));
		onesTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 0;
		UpperSection.add(onesTextField, gbc);
		
		JTextField twosTextField = new JTextField("");
		twosTextField.setPreferredSize(new Dimension(80, 30));
		twosTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 1;
		UpperSection.add(twosTextField, gbc);
		
		JTextField threesTextField = new JTextField("");
		threesTextField.setPreferredSize(new Dimension(80, 30));
		threesTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 2;
		UpperSection.add(threesTextField, gbc);
		
		JTextField foursTextField = new JTextField("");
		foursTextField.setPreferredSize(new Dimension(80, 30));
		foursTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 3;
		UpperSection.add(foursTextField, gbc);
		
		JTextField fivesTextField = new JTextField("");
		fivesTextField.setPreferredSize(new Dimension(80, 30));
		fivesTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 4;
		UpperSection.add(fivesTextField, gbc);
		
		JTextField sixesTextField = new JTextField("");
		sixesTextField.setPreferredSize(new Dimension(80, 30));
		sixesTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 5;
		UpperSection.add(sixesTextField, gbc);
		
		//Totals
		JLabel upperTotalLabel = new JLabel("Total Score:");
		gbc.weightx = 1;
		gbc.gridx = 0;
		gbc.gridy = 6;
		UpperSection.add(upperTotalLabel, gbc);
		
		JLabel bonusLabel = new JLabel("Bonus:");
		gbc.weightx = 1;
		gbc.gridx = 0;
		gbc.gridy = 7;
		UpperSection.add(bonusLabel, gbc);
		
		JLabel upperTotalSumLabel = new JLabel("Upper Section Total:");
		gbc.weightx = 1;
		gbc.gridx = 0;
		gbc.gridy = 8;
		UpperSection.add(upperTotalSumLabel, gbc);
		
		JTextField upperTotalTextField = new JTextField("");
		upperTotalTextField.setPreferredSize(new Dimension(80, 30));
		upperTotalTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 6;
		UpperSection.add(upperTotalTextField, gbc);
		
		JTextField bonusTextField = new JTextField("");
		bonusTextField.setPreferredSize(new Dimension(80, 30));
		bonusTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 7;
		UpperSection.add(bonusTextField, gbc);
		
		JTextField upperTotalSumTextField = new JTextField("");
		upperTotalSumTextField.setPreferredSize(new Dimension(80, 30));
		upperTotalSumTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 8;
		UpperSection.add(upperTotalSumTextField, gbc);
		
		middlePanel.add(UpperSection);
	}

	private void setUpLogo() {

		ImageIcon logoImage = new ImageIcon("images/YahtzeeLogo300.png");
		
		JLabel logoLabel = new JLabel("", logoImage, JLabel.CENTER);
		JPanel logoPanel = new JPanel(new BorderLayout());
		logoPanel.add( logoLabel, BorderLayout.CENTER );
		
		frame.add(logoPanel, BorderLayout.NORTH);
		//frame.pack();
	}

	
	
	
	private int chanceScore(){
		int score = 0;
		for(int i = 0; i < results.length; i++){
			score += (i + 1) * results[i];
		}
		return score;
	}
	
	private boolean hasYahtzee() {
		
		boolean hasFive = false;
		for(int i = 0; i < results.length; i++){
			if(results[i] == 5){
				hasFive = true;
				break;
			} else if (results[i] > 0){
				break;
			}
		}
		return hasFive;
	}
	
	private void findResults() {
		clearResults();
		for(Dice d : dice){
			results[d.getFaceValue() - 1]++;
		}
	}
	
	private void clearResults() {
		for (int i = 0; i < results.length; i++){
			results[i] = 0;
		}
	}
	
	
	class RollButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (rollCounter == 0) enableDiceButtons(true);
				rollCounter++;
				
			if (rollCounter == 3) {
				rollButton.setEnabled(false);
				resetButton.setEnabled(true);
			}
			
			rollDice();
			
			findResults();
			System.out.println("Has yahtzee? " + hasYahtzee());
			System.out.println("Chance score: " + chanceScore());
			System.out.println(Arrays.toString(results));
			//System.out.println(Dice.getFaceValue());
			
			}
				
		}
		
	/******************* GUI SETUP **********************/
	
	/******************* Super Class - HANDLER SETUP **********************/
	abstract class ButtonHandler {
		protected JButton myButton;
		protected JTextField myTextField;
		
		public ButtonHandler(JButton button, JTextField text){
			myButton	= button;
			myTextField	= text;
		}
		
		/** 
		 * @param button that was pressed
		 * @return True if I can handle this button, false otherwise
		 */
		public boolean canHandle(JButton button) {
			return button == myButton;
		}
		
		/**
		 * Is this roll valid? i.e., does it meet the requirements of 
		 * this category
		 * @return True if it is valid, false otherwise
		 */
		public abstract boolean validRoll();
		
		/**
		 * Reset my button and text field for a new game
		 */
		public void reset() {
			myButton.setEnabled(true);
			myTextField.setText("");
		}
				
		public void updateScore() {
			myButton.setEnabled(false);
			//resetDice();
			//update grand total?
			
		}
	}
	
	/******************* Super Class - HANDLER SETUP **********************/

	/******************* Upper Section Super Class - HANDLER SETUP **********************/
 class UpperButtonHandler extends ButtonHandler {

		protected int myVal;
		public UpperButtonHandler(JButton button, JTextField text) {
			super(button, text);
			myVal = 0;
		}
		
		@Override
		public boolean validRoll() { return true; }
		
		@Override
		public void updateScore() {
			findResults();
			
			int score = results[myVal - 1] * myVal;
			
			myTextField.setText(String.valueOf(score));
			
			//updateLowerScore();
			super.updateScore();
		}
		
	}
	/******************* Upper Section Super Class - HANDLER SETUP **********************/

 	/******************* Upper Section Child Classes - HANDLER SETUP **********************/
 	class OnesButtonHandler extends UpperButtonHandler {

		public OnesButtonHandler(JButton button, JTextField text) {
			super(button, text);
			myVal = 1;
		}
		
	}
	
	class TwosButtonHandler extends UpperButtonHandler {

		public TwosButtonHandler(JButton button, JTextField text) {
			super(button, text);
			myVal = 2;
		}
		
	}
	
	class ThreesButtonHandler extends UpperButtonHandler {

		public ThreesButtonHandler(JButton button, JTextField text) {
			super(button, text);
			myVal = 3;
		}
		
	}
	
	class FoursButtonHandler extends UpperButtonHandler {

		public FoursButtonHandler(JButton button, JTextField text) {
			super(button, text);
			myVal = 4;
		}
		
	}
	
	class FivesButtonHandler extends UpperButtonHandler {

		public FivesButtonHandler(JButton button, JTextField text) {
			super(button, text);
			myVal = 5;
		}
		
	}
	
	class SixesButtonHandler extends UpperButtonHandler {

		public SixesButtonHandler(JButton button, JTextField text) {
			super(button, text);
			myVal = 6;
		}
		
	}
	/******************* Upper Section Child Classes - HANDLER SETUP **********************/
	
	/******************* Lower Section Child Classes - HANDLER SETUP **********************/
	class ChanceButtonHandler extends ButtonHandler {

		public ChanceButtonHandler(JButton button, JTextField text) {
			super(button, text);
		}

		@Override
		public boolean validRoll() {
			return true;
		}
		
		@Override
		public void updateScore() {
			
			
			int sum = 0;
			for(Dice d : dice){
				sum += d.getFaceValue();
			}
			
			myTextField.setText(String.valueOf(sum));

			//update lower total
			super.updateScore();
		}
		
	}
	
	class YahtzeeButtonHandler extends ButtonHandler {

		public YahtzeeButtonHandler(JButton button, JTextField text) {
			super(button, text);
		}

		@Override
		public boolean validRoll() {
			return true;
		}
		
		@Override
		public void updateScore() {
			
			for(int i = 0; i < results.length; i++){
				if(results[i] == 5){
					myTextField.setText(String.valueOf("50"));
					break;
				} else if (results[i] > 0){
					break;
				}
			}
			//update lower total
			super.updateScore();
		}
		
	}
	
	
	class LargeStraightButtonHandler extends ButtonHandler {

		public  LargeStraightButtonHandler(JButton button, JTextField text) {
			super(button, text);
		}

		@Override
		public boolean validRoll() {
			return true;
		}
		
		@Override
		public void updateScore() {
			
			
			//update lower total
			super.updateScore();
		}
		
	}
	
	class SmallStraightButtonHandler extends ButtonHandler {

		public  SmallStraightButtonHandler(JButton button, JTextField text) {
			super(button, text);
		}

		@Override
		public boolean validRoll() {
			return true;
		}
		
		@Override
		public void updateScore() {
			
			
			//update lower total
			super.updateScore();
		}
		
	}
	
	class FullHouseButtonHandler extends ButtonHandler {

		public  FullHouseButtonHandler(JButton button, JTextField text) {
			super(button, text);
		}

		@Override
		public boolean validRoll() {
			return true;
		}
		
		@Override
		public void updateScore() {
			
			
			//update lower total
			super.updateScore();
		}
		
	}
	
	class FourOfAKindButtonHandler extends ButtonHandler {

		public  FourOfAKindButtonHandler(JButton button, JTextField text) {
			super(button, text);
		}

		@Override
		public boolean validRoll() {
			return true;
		}
		
		@Override
		public void updateScore() {
			
			
			//update lower total
			super.updateScore();
		}
		
	}
	
	class ThreeOfAKindButtonHandler extends ButtonHandler {

		public  ThreeOfAKindButtonHandler(JButton button, JTextField text) {
			super(button, text);
		}

		@Override
		public boolean validRoll() {
			return true;
		}
		
		@Override
		public void updateScore() {
			
			
			//update lower total
			super.updateScore();
		}
		
	}
	
	/******************* Lower Section Child Classes - HANDLER SETUP **********************/

}