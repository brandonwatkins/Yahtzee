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
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;


public class Bahtzee2 {

	public static void main(String[] args) {
		new Bahtzee2().go();
	}
	
	/******************* GUI SETUP **********************/
	private JFrame frame;
	private JButton rollButton;
	private int firstRollCounter = 0;
	private int gameOverCounter	 = 0;
	private boolean hasBonus	 = false;
	
	private boolean FullHouseButtonPressed = false;
	private boolean ThreeKindButtonPressed = false;
	private boolean FourKindButtonPressed = false;
	private boolean SmStraightButtonPressed = false;
	private boolean LgStraightButtonPressed = false;
	private boolean ChanceButtonPressed = false;
	private boolean YahtzeeButtonPressed = false;
	
	//private boolean ThreesButtonPressed = false;
	
	private JPanel buttonPanel;
	private JPanel middlePanel;
	private int grandTotal;
	private int upperTotal;
	private int upperTotalSum;
	private int lowerTotal;
	private int rollCounter;
	private int results[] = {0, 0, 0, 0, 0, 0};
	private String title = "Yahtzee";
	private String name = "";
	AllButtonListener all = new AllButtonListener();
	
	private final ImageIcon DICE_BLANK	= new ImageIcon( "Yahtzee/images/blank.png");
	private final ImageIcon DICE_1 		= new ImageIcon( "Yahtzee/images/1.png");	
	private final ImageIcon DICE_2	 	= new ImageIcon( "Yahtzee/images/2.png");
	private final ImageIcon DICE_3 	 	= new ImageIcon( "Yahtzee/images/3.png");	
	private final ImageIcon DICE_4	 	= new ImageIcon( "Yahtzee/images/4.png");
	private final ImageIcon DICE_5	 	= new ImageIcon( "Yahtzee/images/5.png");
	private final ImageIcon DICE_6	 	= new ImageIcon( "Yahtzee/images/6.png");
		
	private ImageIcon DICE_IMAGES[] = {
			DICE_1,
			DICE_2,
			DICE_3,
			DICE_4,
			DICE_5,
			DICE_6
	};	
	
	private ArrayList<JToggleButton> diceButtons = new ArrayList<JToggleButton>();
	private ArrayList<JButton> scoreCardButtons = new ArrayList<JButton>();
	private ArrayList<JTextField> scoreCardTextFields = new ArrayList<JTextField>();
	private ArrayList<Dice> dice = new ArrayList<Dice>();
	private ArrayList<ButtonHandler> handlers = new ArrayList<ButtonHandler>();
		
	JTextField lowerTotalTextField = new JTextField("");
	JTextField grandTotalTextField = new JTextField("");

	JTextField upperTotalTextField    = new JTextField("");
	JTextField bonusTextField		  = new JTextField("");
	JTextField upperTotalSumTextField = new JTextField("");
	
	JButton fullHouseButton = new JButton("Full House");
	JButton threeKindButton = new JButton("3 of a kind");
	JButton fourKindButton = new JButton("4 of a kind");
	JButton smStraightButton = new JButton("Sm. Straight");
	JButton lgStraightButton = new JButton("Lg. Straight");
	JButton yahtzeeButton = new JButton("Yahtzee");
	JButton chanceButton = new JButton("Chance");
	
	JButton threesButton = new JButton("Threes");
	
	HallOfFame h = new HallOfFame("/brandonwatkins/Documents/topTenFile.txt");
	
	
	/******************* GUI SETUP **********************/
	
	public void go() {
		
		/******************* GUI SETUP **********************/
		frame = new JFrame("Bahtzee");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
		middlePanel = new JPanel();
		
		askForName();
		
		setUpMenuBar();
		setUpLogo();
		setupUpperSection();
		setupLowerSection();
		
		middlePanel.setLayout(new FlowLayout());
		frame.add(middlePanel, BorderLayout.CENTER);
		
		setupRollSection();

		Constants.center(frame);
		frame.setSize(600, 550);
		frame.setVisible(true);
	
		
		
		/******************* GUI SETUP **********************/
	}


	/******************* GUI SETUP **********************/
	private void setUpMenuBar() {
		JMenuBar menuBar;
		JMenu fileMenu;
		JMenu viewMenu;
		JMenu helpMenu;
		
		menuBar		= new JMenuBar();
		
		fileMenu	= new JMenu("File");
		fileMenu.setMnemonic('f');
		
		viewMenu	= new JMenu("View");
		viewMenu.setMnemonic('v');
		
		helpMenu	= new JMenu("Help");
		helpMenu.setMnemonic('h');
		
		menuBar.add(fileMenu);
		menuBar.add(viewMenu);
		menuBar.add(Box.createHorizontalGlue()); //http://zetcode.com/tutorials/javaswingtutorial/menusandtoolbars/
		menuBar.add(helpMenu);
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic('x');
		exitItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_X, ActionEvent.ALT_MASK));
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
					int result = JOptionPane.showConfirmDialog(null, 
							"Are you sure you'd like to exit?",
							"Wait a second!",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if(result == JOptionPane.YES_OPTION){
						System.exit(0);
					}
			}
		});
		
		
		JMenuItem restartItem = new JMenuItem("Restart");
		restartItem.setMnemonic('r');
		restartItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_R, ActionEvent.ALT_MASK));
		restartItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
					int result = JOptionPane.showConfirmDialog(null, 
							"Are you sure you'd like to restart?",
							"Wait a second!",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if(result == JOptionPane.YES_OPTION){
						resetDice();
						resetScoreCard();
					}
			}
		});
		
		fileMenu.add(restartItem);
		fileMenu.add(exitItem);
		
		JMenuItem HOFItem = new JMenuItem("Hall Of Fame");
		HOFItem.setMnemonic('h');
		HOFItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_H, ActionEvent.ALT_MASK));
		HOFItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JDialog f = h.getHallOfFame();
				f.setLocationRelativeTo(frame);
				f.setVisible(true);
			}
		});
		
		viewMenu.add(HOFItem);
		
		class HelpListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent event) {
				
				JDialog aboutDialog = new JDialog(frame, 
						"Yahtzee Help",
						true);
				JPanel panel = new JPanel();
				JLabel aboutInfo = new JLabel(
						"<html>"
						+ "<h1>How to play: Yahtzee</h1>"
						+ "<h4>Quick Start</h4>"
						+ "<p>1. Click the 'roll' button. If you're happy with your initial score,</p>"
						+ "<p>you can add your score to your card and complete your turn. However, you can</p>"
						+ "<p>also try for a higher score. Select which dice you want to keep and click</p>"
						+ "<p>roll again.</p>"
						+ "<p></p>"
						+ "<p>2. If you're still unhappy with your score, you can roll the dice one more time.</p>"
						+ "<p>Once again, you can re-roll as many dice as you want.</p>"
						+ "<p></p>"						
						+ "<p>3. Keep going until you have filled up the score card (13 turns).</p>"
						+ "<p>As you go, you need to try to fill in all your boxes. Each box can</p>"
						+ "<p>only be filled in once, so make wise choices in terms of how you choose</p>"
						+ "<p>to count your scores.</p>"
						+ "<p></p>"			
						+ "<h4>Scoring in the Upper Section</h4>"
						+ "<p>In the upper section there are six boxes. The score in each of these boxes is</p>"
						+ "<p>determined by adding the total number of dice matching that box..</p>"
						+ "<p>If the total score in the Upper Section is >= 63 you get a BONUS SCORE of an</p>"
						+ "<p>additional 35 points</p>"
						+ "<p></p>"	
						+ "<h4>Scoring in the Lower Section</h4>"
						+ "<p>The lower section contains a number of poker-themed categories with specific point values</p>"
						+ "<p></p>"	
						+ "<h4>Author:</h4>"
						+ "<p>Brandon Watkins</p>"
						+"<h4>Date Created:</h4>"
						+ "<p>9th March 2017</p>"
						+ "</html>");
				
				panel.add(aboutInfo);
				
				aboutDialog.getContentPane().add(panel, BorderLayout.CENTER);
				
				aboutDialog.setSize(630, 630);
				aboutDialog.setVisible(true);
				
			}
			
		}
		
		
		/**
		 * @return Help Menu Items
		 */
		JMenuItem helpAboutItem = new JMenuItem("About");
		helpAboutItem.addActionListener(new HelpListener());
		
		helpMenu.add(helpAboutItem);
		
		frame.setJMenuBar(menuBar);
		
	}

	private void setupRollSection() {	

		buttonPanel = new JPanel();
		
		//Setup buttons
		for(int i = 0; i < 5; i++){
			JToggleButton button = new JToggleButton();
			button.setIcon(DICE_BLANK);
			
			diceButtons.add(button);
			buttonPanel.add(button);
			dice.add(new Dice());
		}
		
		JPanel rollPanel = new JPanel();
		rollPanel.setLayout(new FlowLayout());

		
		rollButton = new JButton("Roll");
		rollButton.addActionListener(new RollButtonListener());
		rollButton.setPreferredSize(new Dimension(90,66));
		
		rollPanel.add(rollButton);
		rollPanel.add(buttonPanel);
		
		frame.getContentPane().add(rollPanel, BorderLayout.SOUTH);
		
	}

	private void askForName() {
		name = JOptionPane.showInputDialog("Enter your name");
		frame.setTitle(title + " - " + name);
	}
	
	private void enableDiceButtons(boolean flag){
		for(JToggleButton b : diceButtons){
			b.setEnabled(flag);
		}
	}
	
	private void enableScoreCardButtons(boolean flag){
		for(JButton b : scoreCardButtons){
			b.setEnabled(flag);
		}
	}
	
	private void rollDice() {
		for(int i = 0; i < diceButtons.size(); i++){
			JToggleButton button = diceButtons.get(i);
			if(!button.isSelected())
				button.setIcon(DICE_IMAGES[dice.get(i).roll() - 1]);
		}
		frame.setTitle(title + " - " + name + " - Roll " + rollCounter);
	}
	
	private void resetDice() {
		for(JToggleButton b : diceButtons){
			b.setEnabled(false);
			b.setSelected(false);
			b.setIcon(DICE_BLANK);
		}
		frame.setTitle(title);
		rollCounter = 0;
		rollButton.setEnabled(true);
	}
	
	private void resetScoreCard(){
		for(JButton b : scoreCardButtons){
			b.setEnabled(true);
			b.setSelected(false);
		}
		
		for(JTextField f : scoreCardTextFields){
			f.setText("");
			f.setEditable(false);
		}
		
		enableScoreCardButtons(false);
		
		firstRollCounter = 0;
		grandTotal = 0;
		grandTotalTextField.setText("");
		upperTotal = 0;
		upperTotalTextField.setText("");
		lowerTotal = 0;
		lowerTotalTextField.setText("");
		upperTotalSum = 0;
		upperTotalSumTextField.setText("");
		gameOverCounter = 0;
		hasBonus = false;
	}
	
	private void setupLowerSection() {
		JPanel LowerSection = new JPanel();
		LowerSection.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		TitledBorder titled = new TitledBorder("Lower Section");
		LowerSection.setBorder(titled);
	
		threeKindButton.addActionListener(all);
		threeKindButton.setPreferredSize(new Dimension(100, 30));
		gbc.gridx = 0;
		gbc.gridy = 0;
		threeKindButton.setEnabled(false);
		scoreCardButtons.add(threeKindButton);
		LowerSection.add(threeKindButton, gbc);

		fourKindButton.addActionListener(all);
		fourKindButton.setPreferredSize(new Dimension(100, 30));
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 1;
		fourKindButton.setEnabled(false);
		scoreCardButtons.add(fourKindButton);
		LowerSection.add(fourKindButton, gbc);

		fullHouseButton.addActionListener(all);
		fullHouseButton.setPreferredSize(new Dimension(100, 30));
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 2;
		fullHouseButton.setEnabled(false);
		scoreCardButtons.add(fullHouseButton);
		LowerSection.add(fullHouseButton, gbc);

		smStraightButton.addActionListener(all);
		smStraightButton.setPreferredSize(new Dimension(100, 30));
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 3;
		smStraightButton.setEnabled(false);
		scoreCardButtons.add(smStraightButton);
		LowerSection.add(smStraightButton, gbc);
	
		lgStraightButton.addActionListener(all);
		lgStraightButton.setPreferredSize(new Dimension(100, 30));
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 4;
		lgStraightButton.setEnabled(false);
		scoreCardButtons.add(lgStraightButton);
		LowerSection.add(lgStraightButton, gbc);
		
		yahtzeeButton.addActionListener(all);
		yahtzeeButton.setPreferredSize(new Dimension(100, 30));
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 5;
		yahtzeeButton.setEnabled(false);
		scoreCardButtons.add(yahtzeeButton);
		LowerSection.add(yahtzeeButton, gbc);
		
		chanceButton.addActionListener(all);
		chanceButton.setPreferredSize(new Dimension(100, 30));
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 6;
		chanceButton.setEnabled(false);
		scoreCardButtons.add(chanceButton);
		LowerSection.add(chanceButton, gbc);
		
		//Corresponding TextFields 
		JTextField threeKindTextField = new JTextField("");
		threeKindTextField.setPreferredSize(new Dimension(80, 30));
		threeKindTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 0;
		scoreCardTextFields.add(threeKindTextField);
		LowerSection.add(threeKindTextField, gbc);
		
		JTextField fourKindTextField = new JTextField("");
		fourKindTextField.setPreferredSize(new Dimension(80, 30));
		fourKindTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 1;
		scoreCardTextFields.add(fourKindTextField);
		LowerSection.add(fourKindTextField, gbc);
		
		JTextField fullHouseTextField = new JTextField("");
		fullHouseTextField.setPreferredSize(new Dimension(80, 30));
		fullHouseTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 2;
		scoreCardTextFields.add(fullHouseTextField);
		LowerSection.add(fullHouseTextField, gbc);
		
		JTextField smStraightTextField = new JTextField("");
		smStraightTextField.setPreferredSize(new Dimension(80, 30));
		smStraightTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 3;
		scoreCardTextFields.add(smStraightTextField);
		LowerSection.add(smStraightTextField, gbc);
		
		JTextField lgStraightTextField = new JTextField("");
		lgStraightTextField.setPreferredSize(new Dimension(80, 30));
		lgStraightTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 4;
		scoreCardTextFields.add(lgStraightTextField);
		LowerSection.add(lgStraightTextField, gbc);
		
		JTextField yahtzeeTextField = new JTextField("");
		yahtzeeTextField.setPreferredSize(new Dimension(80, 30));
		yahtzeeTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 5;
		scoreCardTextFields.add(yahtzeeTextField);
		LowerSection.add(yahtzeeTextField, gbc);
		
		JTextField chanceTextField = new JTextField("");
		chanceTextField.setPreferredSize(new Dimension(80, 30));
		chanceTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 6;
		scoreCardTextFields.add(chanceTextField);
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
		
		lowerTotalTextField.setPreferredSize(new Dimension(80, 30));
		lowerTotalTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 7;
		LowerSection.add(lowerTotalTextField, gbc);
		
		grandTotalTextField.setPreferredSize(new Dimension(80, 30));
		grandTotalTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 8;
		LowerSection.add(grandTotalTextField, gbc);
		
		//Load handlers
		handlers.add(new ThreeOfAKindButtonHandler(threeKindButton, threeKindTextField));
		handlers.add(new FourOfAKindButtonHandler(fourKindButton, fourKindTextField));
		handlers.add(new FullHouseButtonHandler(fullHouseButton, fullHouseTextField));
		handlers.add(new SmallStraightButtonHandler(smStraightButton, smStraightTextField));
		handlers.add(new LargeStraightButtonHandler(lgStraightButton, lgStraightTextField));
		handlers.add(new YahtzeeButtonHandler(yahtzeeButton, yahtzeeTextField));
		handlers.add(new ChanceButtonHandler(chanceButton, chanceTextField));
		
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
		acesButton.addActionListener(all);
		gbc.gridx = 0;
		gbc.gridy = 0;
		acesButton.setPreferredSize(new Dimension(80, 30));
		acesButton.setEnabled(false);
		scoreCardButtons.add(acesButton);
		UpperSection.add(acesButton, gbc);

		JButton twosButton = new JButton("Twos");
		twosButton.addActionListener(all);
		twosButton.setPreferredSize(new Dimension(80, 30));
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 1;
		twosButton.setEnabled(false);
		scoreCardButtons.add(twosButton);
		UpperSection.add(twosButton, gbc);

		//JButton threesButton = new JButton("Threes");
		threesButton.addActionListener(all);
		threesButton.setPreferredSize(new Dimension(80, 30));
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 2;
		threesButton.setEnabled(false);
		scoreCardButtons.add(threesButton);
		UpperSection.add(threesButton, gbc);

		JButton foursButton = new JButton("Fours");
		foursButton.addActionListener(all);
		foursButton.setPreferredSize(new Dimension(80, 30));
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 3;
		foursButton.setEnabled(false);
		scoreCardButtons.add(foursButton);
		UpperSection.add(foursButton, gbc);
	
		JButton fivesButton = new JButton("Fives");
		fivesButton.addActionListener(all);
		fivesButton.setPreferredSize(new Dimension(80, 30));
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 4;
		fivesButton.setEnabled(false);
		scoreCardButtons.add(fivesButton);
		UpperSection.add(fivesButton, gbc);
		
		JButton sixesButton = new JButton("Sixes");
		sixesButton.addActionListener(all);
		sixesButton.setPreferredSize(new Dimension(80, 30));
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 5;
		sixesButton.setEnabled(false);
		scoreCardButtons.add(sixesButton);
		UpperSection.add(sixesButton, gbc);
		
		//Corresponding TextFields 
		JTextField acesTextField = new JTextField("");
		acesTextField.setPreferredSize(new Dimension(80, 30));
		acesTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 0;
		scoreCardTextFields.add(acesTextField);
		UpperSection.add(acesTextField, gbc);
		
		JTextField twosTextField = new JTextField("");
		twosTextField.setPreferredSize(new Dimension(80, 30));
		twosTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 1;
		scoreCardTextFields.add(twosTextField);
		UpperSection.add(twosTextField, gbc);
		
		JTextField threesTextField = new JTextField("");
		threesTextField.setPreferredSize(new Dimension(80, 30));
		threesTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 2;
		scoreCardTextFields.add(threesTextField);
		UpperSection.add(threesTextField, gbc);
		
		JTextField foursTextField = new JTextField("");
		foursTextField.setPreferredSize(new Dimension(80, 30));
		foursTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 3;
		scoreCardTextFields.add(foursTextField);
		UpperSection.add(foursTextField, gbc);
		
		JTextField fivesTextField = new JTextField("");
		fivesTextField.setPreferredSize(new Dimension(80, 30));
		fivesTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 4;
		scoreCardTextFields.add(fivesTextField);
		UpperSection.add(fivesTextField, gbc);
		
		JTextField sixesTextField = new JTextField("");
		sixesTextField.setPreferredSize(new Dimension(80, 30));
		sixesTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 5;
		scoreCardTextFields.add(sixesTextField);
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
		
		//TextFields
		upperTotalTextField.setPreferredSize(new Dimension(80, 30));
		upperTotalTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 6;
		UpperSection.add(upperTotalTextField, gbc);
		
		bonusTextField.setPreferredSize(new Dimension(80, 30));
		bonusTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 7;
		UpperSection.add(bonusTextField, gbc);
		
		upperTotalSumTextField.setPreferredSize(new Dimension(80, 30));
		upperTotalSumTextField.setEditable(false);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 8;
		UpperSection.add(upperTotalSumTextField, gbc);
		
		//Load handlers
		handlers.add(new AcesButtonHandler(acesButton, acesTextField));
		handlers.add(new TwosButtonHandler(twosButton, twosTextField));
		handlers.add(new ThreesButtonHandler(threesButton, threesTextField));
		handlers.add(new FoursButtonHandler(foursButton, foursTextField));
		handlers.add(new FivesButtonHandler(fivesButton, fivesTextField));
		handlers.add(new SixesButtonHandler(sixesButton, sixesTextField));
		
		middlePanel.add(UpperSection);
	}

	private void setUpLogo() {

		ImageIcon logoImage = new ImageIcon("Yahtzee/images/YahtzeeLogo300.png");
		
		JLabel logoLabel = new JLabel("", logoImage, JLabel.CENTER);
		JPanel logoPanel = new JPanel(new BorderLayout());
		logoPanel.add( logoLabel, BorderLayout.CENTER );
		
		frame.add(logoPanel, BorderLayout.NORTH);
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
	
	private void disableIfSelected(){
		if(FullHouseButtonPressed == true) {
			fullHouseButton.setEnabled(false);
		}
		
		if(ThreeKindButtonPressed == true) {
			threeKindButton.setEnabled(false);
		}
		
		if(FourKindButtonPressed == true) {
			fourKindButton.setEnabled(false);
		}
		
		if(SmStraightButtonPressed == true) {
			smStraightButton.setEnabled(false);
		}
		
		if(LgStraightButtonPressed == true) {
			lgStraightButton.setEnabled(false);
		}
		
		if(YahtzeeButtonPressed == true) {
			yahtzeeButton.setEnabled(false);
		}
		
		if(ChanceButtonPressed == true) {
			chanceButton.setEnabled(false);
		}
		
	}
	
	 private void updateBonusScore() {
		 if(upperTotal >= 63 && hasBonus == false){
			 bonusTextField.setText("35");
			 upperTotalSum += 35;
			 upperTotalSum += upperTotal;
			 upperTotalTextField.setText("");
			 upperTotalSumTextField.setText(upperTotalSum + "");
			 hasBonus = true;
		 }
	}
	
	class RollButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(firstRollCounter == 0){
				enableScoreCardButtons(true);
			}
			
			enableScoreCardButtons(true);
			disableIfSelected();
			
			if (rollCounter == 0){
				enableDiceButtons(true);
			}
				rollCounter++;

			if (rollCounter == 3) {
				rollButton.setEnabled(false);
			}
			
			rollDice();
			findResults();
			
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
			resetDice();
			if(hasBonus == false){
				grandTotal = lowerTotal + upperTotal;
				lowerTotalTextField.setText(lowerTotal + "");
				grandTotalTextField.setText(grandTotal + "");

			} else {
				grandTotal = lowerTotal + upperTotalSum;
				lowerTotalTextField.setText(lowerTotal + "");
				grandTotalTextField.setText(grandTotal + "");			
			}
			enableScoreCardButtons(false);
		}

	}
	
	/******************* Super Class - HANDLER SETUP **********************/

	/******************* Upper Section Super Class - HANDLER SETUP **********************/
 class UpperButtonHandler extends ButtonHandler {

	 	int score;
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
			
			score = results[myVal - 1] * myVal;
			myTextField.setText(String.valueOf(score));
			
			if(hasBonus == false) {
				upperTotal += score;
				upperTotalTextField.setText(upperTotal + "");
			} else {
				upperTotalSum += score;
				upperTotalSumTextField.setText(upperTotalSum + "");
			}

			updateBonusScore();
			super.updateScore();
		}
		
	}
 
	/******************* Upper Section Super Class - HANDLER SETUP **********************/

 	/******************* Upper Section Child Classes - HANDLER SETUP **********************/
 	class AcesButtonHandler extends UpperButtonHandler {

		public AcesButtonHandler(JButton button, JTextField text) {
			super(button, text);
			myVal = 1;
			//acesButtonPressed = true;
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
			//ThreesButtonPressed = true;
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
			
			ChanceButtonPressed = true;
			myTextField.setText(String.valueOf(sum));
			lowerTotal += sum;
			super.updateScore();
		}
		
	}
	
	class YahtzeeButtonHandler extends ButtonHandler {

		public YahtzeeButtonHandler(JButton button, JTextField text) {
			super(button, text);
		}

		@Override
		public boolean validRoll() {
			for(int i = 0; i < results.length; i++){
				if(results[i] == 5){
					myTextField.setText(String.valueOf("50"));
					break;
				} else if (results[i] > 0){
					myTextField.setText(String.valueOf("0"));
					return false;
				}
			}
			return true;
		}
		
		@Override
		public void updateScore() {
			if(validRoll()){
				lowerTotal += 50;
				super.updateScore();
			}  else myButton.setEnabled(false);
			YahtzeeButtonPressed = true;
		}
		
	}
	
	
	class LargeStraightButtonHandler extends ButtonHandler {

		public  LargeStraightButtonHandler(JButton button, JTextField text) {
			super(button, text);
		}
		int sum = 0;
		@Override
		public boolean validRoll() {
			boolean oneFlag 	= false;
			boolean twoFlag 	= false;
			boolean threeFlag	= false;
			boolean fourFlag 	= false;
			boolean fiveFlag 	= false;
			
			for(int i = 0; i < results.length; i++){
				if(results[i] == 0){
					continue;
				}
				if(results[i] == 1 && oneFlag == false){
					oneFlag   = true;
					continue;
				}
				if(results[i] == 1 && twoFlag == false){
					twoFlag   = true;
					continue;
				}
				if(results[i] == 1 && threeFlag == false){ 
					threeFlag = true;
					continue;
				}
				if(results[i] == 1 && fourFlag == false){ 
					fourFlag  = true;
					continue;
				}
				if(results[i] == 1 && fiveFlag == false){
					fiveFlag  = true;
				}
				
			
				if(oneFlag && twoFlag && threeFlag && fourFlag && fiveFlag == true){
					sum += 40;
					myTextField.setText(String.valueOf(sum));
					break;
					} else if (threeFlag == false) {
						myTextField.setText(String.valueOf("0"));
						return false;
					}
				}
				return true;
			}
	
	
		
		@Override
		public void updateScore() {
			if(validRoll()){
				lowerTotal += sum;
				super.updateScore();
			}  else myButton.setEnabled(false);
			LgStraightButtonPressed = true;
		}
		
	}
	
	class SmallStraightButtonHandler extends ButtonHandler {

		public  SmallStraightButtonHandler(JButton button, JTextField text) {
			super(button, text);
		}
		int sum = 0;
		@Override
		public boolean validRoll() {
			boolean oneFlag 	= false;
			boolean twoFlag 	= false;
			boolean threeFlag	= false;
			boolean fourFlag 	= false;
			
			for(int i = 0; i < results.length; i++){
				if(results[i] >= 3){
					myTextField.setText(String.valueOf("0"));
				}
				if(results[i] == 0){
					continue;
				}
				if(((results[i] == 1) || (results[i] == 2)) && oneFlag == false){
					oneFlag   = true;
					continue;
				}
				if(((results[i] == 1) || (results[i] == 2)) && twoFlag == false){
					twoFlag   = true;
					continue;
				}
				if(((results[i] == 1) || (results[i] == 2)) && threeFlag == false){ 
					threeFlag = true;
					continue;
				}
				if(((results[i] == 1) || (results[i] == 2)) && fourFlag == false){ 
					fourFlag  = true;
				}
			
				if(oneFlag && twoFlag && threeFlag && fourFlag == true){
					sum += 30;
					myTextField.setText(String.valueOf(sum));
					break;
					} else if((oneFlag || twoFlag || threeFlag || fourFlag) == false) {
						myTextField.setText(String.valueOf("0"));
						return false;
					}
				}
				return true;
			}
		
		@Override
		public void updateScore() {
			if(validRoll()){
				lowerTotal += sum;
			super.updateScore();
			}  else myButton.setEnabled(false);
			SmStraightButtonPressed = true;	
		}
		
	}
	
	class FullHouseButtonHandler extends ButtonHandler {

		public  FullHouseButtonHandler(JButton button, JTextField text) {
			super(button, text);
		}
		int sum = 0;
		@Override
		public boolean validRoll() {
			boolean firstCheck 	= false;
			boolean secondCheck = false;
			for(int i = 0; i < results.length; i++){
				if(results[i] == 3){
					firstCheck = true;
				}
				if(results[i] == 2){
					secondCheck = true;
				}
				if(firstCheck && secondCheck == true){
					sum += 25;
					myTextField.setText(String.valueOf(sum));
					break;
					} else if(firstCheck || secondCheck == false){
						myTextField.setText(String.valueOf("0"));
					}
			}
			return true;
		}
		
		@Override
		public void updateScore() {
			if(validRoll()){
				lowerTotal += sum;
				super.updateScore();
			} 
			FullHouseButtonPressed = true;
		}
		
	}
	
	class FourOfAKindButtonHandler extends ButtonHandler {

		public  FourOfAKindButtonHandler(JButton button, JTextField text) {
			super(button, text);
		}
		int sum = 0;
		@Override
		public boolean validRoll() {
			for(int i = 0; i < results.length; i++){
				if(results[i] >= 4){
					for(Dice d : dice){
						sum += d.getFaceValue();
					}
					myTextField.setText(String.valueOf(sum));
					break;
				} else {
					myTextField.setText(String.valueOf("0"));
				}
			}
			return true;
		}
		
		@Override
		public void updateScore() {
			if(validRoll()){
				lowerTotal += sum;
				super.updateScore();
			}
			FourKindButtonPressed = true;
		}
		
	}
	
	class ThreeOfAKindButtonHandler extends ButtonHandler {

		public  ThreeOfAKindButtonHandler(JButton button, JTextField text) {
			super(button, text);
		}

		int sum = 0;
		@Override
		public boolean validRoll() {
			for(int i = 0; i < results.length; i++){
				if(results[i] >= 3){
					for(Dice d : dice){
						sum += d.getFaceValue();
					}
					myTextField.setText(String.valueOf(sum));
					break;
				} else {
					myTextField.setText(String.valueOf("0"));
				}
			}
			return true;
		}
		
		@Override
		public void updateScore() {
			if(validRoll()){
				lowerTotal += sum;
				super.updateScore();
			}
			ThreeKindButtonPressed = true;		
		}
		
	}
	
	/******************* Lower Section Child Classes - HANDLER SETUP **********************/
	
	/******************* Action Listeners - HANDLER SETUP **********************/
	class AllButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton clickedButton = (JButton) e.getSource();
			for(ButtonHandler handler : handlers){
				if(handler.canHandle(clickedButton)){
					handler.updateScore();
					gameOverCounter++;
					break;
				}
			}
			
			if(gameOverCounter == 13){
				int result = JOptionPane.showConfirmDialog(null, 
						"Your Score: " + grandTotal + "!" + "\nWould you like to save?",
						"Game Over",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if(result == JOptionPane.YES_OPTION){
					JDialog f = h.getHallOfFame(name, grandTotal);
					resetDice();
					resetScoreCard();
				}
			}
		}

	}
	
	/******************* Action Listeners - HANDLER SETUP **********************/
}