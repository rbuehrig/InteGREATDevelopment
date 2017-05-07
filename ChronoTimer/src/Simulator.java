import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
//////////////////////////////////////////
//InteGREAT Development
//Class: CS 361
//
//
//Description: Simulator serves as the 
//interface for console or file input.
//////////////////////////////////////////
public class Simulator {
	//Global variables
	static Chronotimer timmy = new Chronotimer();
	static JTextPane textPane_2;
	static Date date;
	static JFrame frame;	
	static int menuVersion;
	static String timeStamp;
	static int functionNumber;
	static boolean cmdFile;
	static Scanner scan;
	static boolean funcCompleted;
	static boolean power;
	static boolean canToggle;
	static boolean numbersEntered;
	static String eventType;
	static String[] didTheyFinish;


	/**
	 * Main either preps for file input
	 * or opens the GUI
	 * 
	 * @param args --Not used
	 */
	public static void main(String[] args) {		
		try{	
			scan = new Scanner(new File("CTS4R1.txt"));
			cmdFile = true;
		}
		catch(FileNotFoundException e){cmdFile = false;}	

		if (cmdFile){
			while(scan.hasNextLine()){
				commands(scan.nextLine(),timmy);
			}
		}
		else {
			//Initialize global variables
			cmdFile = false;
			menuVersion = 0;
			functionNumber = 1;
			funcCompleted = false;
			power = false;
			canToggle = true;
			numbersEntered = false;
			didTheyFinish = new String[8];

			//Initialize GUI
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						initialize();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	/**
	 * Construcutor initializes GUI.
	 * This method is not used.
	 * 
	 */
	public Simulator() throws IOException {
		initialize();
	}

	/**
	 * Sets up GUI interface and all action listeners
	 * 
	 */
	private static void initialize() throws IOException{
		frame =  new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 709, 441);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		//*************POWER BUTTON***************//
		JButton btnPower = new JButton("Power");
		btnPower.setBounds(34, 19, 117, 29);
		frame.getContentPane().add(btnPower);

		JLabel lblChronotimer = new JLabel("CHRONOTIMER 1009");
		lblChronotimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblChronotimer.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblChronotimer.setBounds(256, 5, 170, 16);
		frame.getContentPane().add(lblChronotimer);

		JLabel lblStart = new JLabel("Start");
		lblStart.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStart.setBounds(226, 62, 61, 16);
		frame.getContentPane().add(lblStart);

		JLabel lblEnabledisable = new JLabel("Enable/Disable");
		lblEnabledisable.setBounds(192, 90, 95, 16);
		frame.getContentPane().add(lblEnabledisable);

		JLabel label_4 = new JLabel("Enable/Disable");
		label_4.setBounds(192, 185, 95, 16);
		frame.getContentPane().add(label_4);

		JLabel lblFinish = new JLabel("Finish");
		lblFinish.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFinish.setBounds(226, 152, 61, 16);
		frame.getContentPane().add(lblFinish);

		JLabel label = new JLabel("1");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(299, 34, 28, 16);
		frame.getContentPane().add(label);

		JLabel label_1 = new JLabel("3");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(339, 34, 28, 16);
		frame.getContentPane().add(label_1);

		JLabel label_2 = new JLabel("5");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(379, 34, 28, 16);
		frame.getContentPane().add(label_2);

		JLabel label_3 = new JLabel("7");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(419, 34, 28, 16);
		frame.getContentPane().add(label_3);

		JLabel label_5 = new JLabel("2");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(299, 124, 28, 16);
		frame.getContentPane().add(label_5);

		JLabel label_6 = new JLabel("4");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(339, 124, 28, 16);
		frame.getContentPane().add(label_6);

		JLabel label_7 = new JLabel("6");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(379, 124, 28, 16);
		frame.getContentPane().add(label_7);

		JLabel label_8 = new JLabel("8");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setBounds(419, 124, 28, 16);
		frame.getContentPane().add(label_8);

		JButton button = new JButton("");
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(299, 62, 28, 16);
		frame.getContentPane().add(button);

		//*************TRIGGER BUTTONS***************//
		JButton button_1 = new JButton("");
		button_1.setBounds(339, 62, 28, 16);
		frame.getContentPane().add(button_1);

		JButton button_2 = new JButton("");
		button_2.setBounds(379, 62, 28, 16);
		frame.getContentPane().add(button_2);

		JButton button_3 = new JButton("");
		button_3.setBounds(419, 62, 28, 16);
		frame.getContentPane().add(button_3);

		JButton button_4 = new JButton("");
		button_4.setBounds(299, 152, 28, 16);
		frame.getContentPane().add(button_4);

		JButton button_5 = new JButton("");
		button_5.setBounds(339, 152, 28, 16);
		frame.getContentPane().add(button_5);

		JButton button_6 = new JButton("");
		button_6.setBounds(379, 152, 28, 16);
		frame.getContentPane().add(button_6);

		JButton button_7 = new JButton("");
		button_7.setBounds(419, 152, 28, 16);
		frame.getContentPane().add(button_7);

		//*************TOGGLE BUTTONS***************//
		JRadioButton radioButton = new JRadioButton("");
		radioButton.setBounds(299, 86, 28, 23);
		radioButton.setEnabled(false);
		frame.getContentPane().add(radioButton);

		JRadioButton radioButton_1 = new JRadioButton("");
		radioButton_1.setBounds(339, 86, 28, 23);
		radioButton_1.setEnabled(false);
		frame.getContentPane().add(radioButton_1);

		JRadioButton radioButton_2 = new JRadioButton("");
		radioButton_2.setBounds(379, 86, 28, 23);
		radioButton_2.setEnabled(false);
		frame.getContentPane().add(radioButton_2);

		JRadioButton radioButton_3 = new JRadioButton("");
		radioButton_3.setBounds(419, 86, 28, 23);
		radioButton_3.setEnabled(false);
		frame.getContentPane().add(radioButton_3);

		JRadioButton radioButton_4 = new JRadioButton("");
		radioButton_4.setBounds(299, 181, 28, 23);
		radioButton_4.setEnabled(false);
		frame.getContentPane().add(radioButton_4);

		JRadioButton radioButton_5 = new JRadioButton("");
		radioButton_5.setBounds(339, 181, 28, 23);
		radioButton_5.setEnabled(false);
		frame.getContentPane().add(radioButton_5);

		JRadioButton radioButton_6 = new JRadioButton("");
		radioButton_6.setBounds(379, 181, 28, 23);
		radioButton_6.setEnabled(false);
		frame.getContentPane().add(radioButton_6);

		JRadioButton radioButton_7 = new JRadioButton("");
		radioButton_7.setBounds(419, 181, 28, 23);
		radioButton_7.setEnabled(false);
		frame.getContentPane().add(radioButton_7);

		//*************FUNCTION BUTTON***************//
		JButton btnFunction = new JButton("FUNCTION");
		btnFunction.setBounds(34, 219, 117, 29);
		frame.getContentPane().add(btnFunction);

		//*************SCROLL BUTTONs***************//
		JLabel label_triDown = new JLabel();
		label_triDown.setSize(30, 30);
		label_triDown.setLocation(135, 251);
		ImageIcon triDown = new ImageIcon("TrianglePointingDown.png");
		Image scaledTriDown = triDown.getImage().getScaledInstance(28, 26,Image.SCALE_DEFAULT);
		label_triDown.setIcon(new ImageIcon(scaledTriDown));
		frame.getContentPane().add(label_triDown);

		JLabel label_triLeft = new JLabel();
		label_triLeft.setSize(30, 30);
		label_triLeft.setLocation(44, 251);
		ImageIcon triLeft = new ImageIcon("TrianglePointingLeft.png");
		Image scaledTriLeft = triLeft.getImage().getScaledInstance(28, 26,Image.SCALE_DEFAULT);
		label_triLeft.setIcon(new ImageIcon(scaledTriLeft));
		frame.getContentPane().add(label_triLeft);

		JLabel label_triRight = new JLabel();
		label_triRight.setSize(30, 30);
		label_triRight.setLocation(86, 251);
		ImageIcon triRight = new ImageIcon("TrianglePointingRight.png");
		Image scaledTriRight = triRight.getImage().getScaledInstance(28, 26,Image.SCALE_DEFAULT);
		label_triRight.setIcon(new ImageIcon(scaledTriRight));
		frame.getContentPane().add(label_triRight);

		JLabel label_triUp = new JLabel();
		label_triUp.setSize(30, 30);
		label_triUp.setLocation(164, 251);
		ImageIcon triUp = new ImageIcon("TrianglePointingUp.png");
		Image scaledTriUp = triUp.getImage().getScaledInstance(28, 26,Image.SCALE_DEFAULT);
		label_triUp.setIcon(new ImageIcon(scaledTriUp));
		frame.getContentPane().add(label_triUp);

		//*************GUI DISPLAY***************//
		JTextPane textPane = new JTextPane();
		Font myFont = new Font(Font.MONOSPACED, 0, 11);
		textPane.setFont(myFont);
		textPane.setEditable(false);
		textPane.setEnabled(false);
		textPane.setBounds(242, 214, 215, 173);
		frame.getContentPane().add(textPane);

		JLabel lblQueueRunning = new JLabel("Queue / Running / Final Time");
		lblQueueRunning.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblQueueRunning.setBounds(270, 397, 164, 16);
		frame.getContentPane().add(lblQueueRunning);

		//*************PRINT BUTTON***************//
		JButton btnPrinterPower = new JButton("Print");
		btnPrinterPower.setBounds(531, 19, 117, 29);
		frame.getContentPane().add(btnPrinterPower);

		JPanel panel = new JPanel();
		panel.setBounds(519, 214, 147, 173);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(4, 3));

		//*************SENSOR POP-UP MENU***************//
		JPopupMenu sensorMenu = new JPopupMenu();
		JPopupMenu GRPsensorMenu = new JPopupMenu();
		JMenuItem pushButton = new JMenuItem("Push Button");
		JMenuItem elecEye = new JMenuItem("Electric Eye");
		JMenuItem gate = new JMenuItem("Gate Sensor");
		JMenuItem pad = new JMenuItem("Pad Sensor");
		JMenuItem pad1 = new JMenuItem("Pad Sensor");

		sensorMenu.add(pushButton);
		sensorMenu.add(elecEye);
		sensorMenu.add(gate);
		sensorMenu.add(pad);

		GRPsensorMenu.add(pad1);

		//*************KEYPAD BUTTONS***************//
		JButton button_12 = new JButton("1");
		panel.add(button_12);

		JButton button_11 = new JButton("2");
		panel.add(button_11);

		JButton button_10 = new JButton("3");
		panel.add(button_10);

		JButton button_9 = new JButton("4");
		panel.add(button_9);

		JButton btnNewButton_6 = new JButton("5");
		panel.add(btnNewButton_6);

		JButton btnNewButton_5 = new JButton("6");
		panel.add(btnNewButton_5);

		JButton btnNewButton_4 = new JButton("7");
		panel.add(btnNewButton_4);

		JButton btnNewButton_3 = new JButton("8");
		panel.add(btnNewButton_3);

		JButton btnNewButton_2 = new JButton("9");
		panel.add(btnNewButton_2);

		JButton btnJ = new JButton("*");
		panel.add(btnJ);

		JButton btnNewButton_1 = new JButton("0");
		panel.add(btnNewButton_1);

		JButton btnNewButton = new JButton("#");
		panel.add(btnNewButton);

		//*************SWAP BUTTON***************//
		JButton btnSwap = new JButton("SWAP");
		btnSwap.setBounds(34, 344, 117, 29);
		frame.getContentPane().add(btnSwap);

		//*************PAPER TAPE PANES***************//
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(500, 46, 185, 155);
		frame.getContentPane().add(layeredPane);

		textPane_2 = new JTextPane();
		layeredPane.setLayer(textPane_2, 1);
		textPane_2.setBounds(10, 6, 160, 127);
		textPane_2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textPane_2.setFont(myFont);
		textPane_2.setAutoscrolls(true);
		layeredPane.add(textPane_2);

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textPane_1.setBounds(0, 61, 180, 90);
		layeredPane.add(textPane_1);


		//****************************SCROLL BUTTON LISTENERS************************************//
		//***************************************************************************************//
		label_triUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (power){
					if (functionNumber == 1){
						if(menuVersion - 1 >= 0) {
							menuVersion--;
							textPane.setText(getRaceMenuLines(menuVersion));
						}
					}
				}
			}
		});

		label_triDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {        		
				if(power){
					if (functionNumber == 1){
						if (menuVersion + 1< 4) {
							menuVersion++;
							textPane.setText(getRaceMenuLines(menuVersion));
						}
					}
				}
			}
		});

		//****************************FUNCTION BUTTON LISTENER***********************************//
		//***************************************************************************************//
		btnFunction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					//menuVersion = 0 --> IND
					//menuVersion = 1 --> PAR
					//menuVersion = 2 --> GRP
					//menuVersion = 3 --> PARGRP
					if (functionNumber == 1){					
						if (menuVersion == 0) {commands("EVENT IND",timmy); funcCompleted = true; eventType = "IND";}
						if (menuVersion == 1) {commands("EVENT PARIND",timmy); funcCompleted = true; eventType = "PARIND";}
						if (menuVersion == 2) {commands("EVENT GRP",timmy); funcCompleted = true; eventType = "GRP";}
						if (menuVersion == 3) {commands("EVENT PARGRP",timmy); funcCompleted = true; eventType = "PARGRP";for(int i = 0; i < 8; i++)didTheyFinish[i] = "---";}//Matt change 5/2

						if (funcCompleted) {
							commands("NEWRUN",timmy);
							radioButton.setEnabled(true); radioButton_1.setEnabled(true); 
							radioButton_2.setEnabled(true); radioButton_3.setEnabled(true);
							radioButton_4.setEnabled(true); radioButton_5.setEnabled(true);
							radioButton_6.setEnabled(true); radioButton_7.setEnabled(true);
							textPane.setText("Enter racer's number on the keypad. Press FUNCTION to enter. Enter \"#\" by itself to end.\n");
							functionNumber++;
						}
					}
					else if (functionNumber == 2){
						String[] items = textPane.getText().split("\n");

						char[] nums = items[1].toCharArray();
						boolean badinput = false;
						for(int i = 0; i < nums.length; i++){
							if(nums[i] < 48 || nums[i] > 57) badinput = true;
						}

						if(!items[1].equals("#") && nums.length > 1 && badinput){
							textPane.setText("Invalid racer number! Enter racer's number on the keypad. Press FUNCTION to enter. Enter \"#\" by itself to end.\n");
						}
						else if (!items[1].equals("#")) {
							commands("NUM " + items[1],timmy);
							textPane.setText("Enter racer's number on the keypad. Press FUNCTION to enter. Enter \"#\" by itself to end.\n");
						}
						else {
							numbersEntered = true;
							textPane.setText("Waiting for race to start... Toggle channels before starting race. Then press a Start button to begin race.");
						}
					}
					else if (functionNumber == 3){
						commands("ENDRUN",timmy);
						textPane.setText("Race is completed. See output file for results, or press FUNCTION for a new race.");
						functionNumber++;
					}
					else if (functionNumber == 4){
						menuVersion = 0;
						textPane.setText(getRaceMenuLines(menuVersion));

						//Reset toggle buttons
						if (radioButton.isSelected()) {radioButton.setSelected(false);}
						if (radioButton_4.isSelected()) {radioButton_4.setSelected(false);}
						if (radioButton_1.isSelected()) {radioButton_1.setSelected(false);}
						if (radioButton_5.isSelected()) {radioButton_5.setSelected(false);}
						if (radioButton_2.isSelected()) {radioButton_2.setSelected(false);}
						if (radioButton_6.isSelected()) {radioButton_6.setSelected(false);}
						if (radioButton_3.isSelected()) {radioButton_3.setSelected(false);}
						if (radioButton_7.isSelected()) {radioButton_7.setSelected(false);}

						functionNumber = 1;
					}
				}
			}
		});

		//****************************POWER BUTTON LISTENER***********************************//
		//************************************************************************************//
		btnPower.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				commands("POWER",timmy);

				if (power) {
					textPane.setEnabled(true);
					textPane.setText(getRaceMenuLines(menuVersion));
				}
				else{
					textPane.setText("");
					textPane.setEnabled(false);

					radioButton.setSelected(false); radioButton_1.setSelected(false); 
					radioButton_2.setSelected(false); radioButton_3.setSelected(false);
					radioButton_4.setSelected(false); radioButton_5.setSelected(false);
					radioButton_6.setSelected(false); radioButton_7.setSelected(false);

					menuVersion = 0;
					canToggle = true;
					numbersEntered = false;
					functionNumber = 1;
					timmy = new Chronotimer();
				}
			}
		});

		//****************************KEYPAD BUTTON LISTENERS***********************************//
		//**************************************************************************************//
		button_12.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					String currentText = textPane.getText();
					textPane.setText(currentText + "1");
				}
			}
		});

		button_11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					String currentText = textPane.getText();
					textPane.setText(currentText + "2");
				}
			}
		});

		button_10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					String currentText = textPane.getText();
					textPane.setText(currentText + "3");
				}
			}
		});

		button_9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					String currentText = textPane.getText();
					textPane.setText(currentText + "4");
				}
			}
		});

		btnNewButton_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					String currentText = textPane.getText();
					textPane.setText(currentText + "5");
				}
			}
		});

		btnNewButton_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					String currentText = textPane.getText();
					textPane.setText(currentText + "6");
				}
			}
		});

		btnNewButton_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					String currentText = textPane.getText();
					textPane.setText(currentText + "7");
				}
			}
		});

		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					String currentText = textPane.getText();
					textPane.setText(currentText + "8");
				}
			}
		});

		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					String currentText = textPane.getText();
					textPane.setText(currentText + "9");
				}
			}
		});

		btnJ.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					String currentText = textPane.getText();
					textPane.setText(currentText + "*");
				}
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					String currentText = textPane.getText();
					textPane.setText(currentText + "0");
				}
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					String currentText = textPane.getText();
					textPane.setText(currentText + "#");
				}
			}
		});


		//****************************TOGGLE BUTTON LISTENERS***********************************//
		//***************************************************************************************//
		radioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					commands("TOG 1",timmy);

					//If a toggle would cause an error, de-select it
					if (!canToggle) {radioButton.setSelected(false);}
					//Otherwise display sensor menu and toggle
					else {
						switch(timmy.eventType){
						case PARGRP: GRPsensorMenu.show(frame, 299, 86);
							break;
						default: sensorMenu.show(frame, 299, 86);
							break;
						}
					}
				}
			}
		});

		radioButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					commands("TOG 3",timmy);
					//If a toggle would cause an error, de-select it
					if (!canToggle)  {radioButton_1.setSelected(false);}
					//Otherwise display sensor menu and toggle
					else {
						switch(timmy.eventType){
						case PARGRP: GRPsensorMenu.show(frame, 339, 86);
							break;
						default: sensorMenu.show(frame, 339, 86);
							break;
						}
					}
				}
			}
		});

		radioButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(power){
					commands("TOG 5",timmy);
					//If a toggle would cause an error, de-select it
					if (!canToggle)  {radioButton_2.setSelected(false);}
					//Otherwise display sensor menu and toggle
					else {GRPsensorMenu.show(frame, 379, 86);}
				}
			}
		});

		radioButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(power){
					commands("TOG 7",timmy);
					//If a toggle would cause an error, de-select it
					if (!canToggle)  {radioButton_3.setSelected(false);}
					//Otherwise display sensor menu and toggle
					else {GRPsensorMenu.show(frame, 419, 86);}
				}
			}
		});

		radioButton_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(power){
					commands("TOG 2",timmy);
					//If a toggle would cause an error, de-select it
					if (!canToggle)  {radioButton_4.setSelected(false);}
					//Otherwise display sensor menu and toggle
					else {
						switch(timmy.eventType){
						case PARGRP: GRPsensorMenu.show(frame, 299, 181);
						break;
						default: sensorMenu.show(frame, 299, 181);
						break;}
					}
				}
			}
		});

		radioButton_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					commands("TOG 4",timmy);
					//If a toggle would cause an error, de-select it
					if (!canToggle)  {radioButton_5.setSelected(false);}
					//Otherwise display sensor menu and toggle
					else {
						switch(timmy.eventType){
						case PARGRP: GRPsensorMenu.show(frame, 339, 181);
						break;
						default: sensorMenu.show(frame, 339, 181);
						break;
						}
					}
				}
			}
		});

		radioButton_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					commands("TOG 6",timmy);
					//If a toggle would cause an error, de-select it
					if (!canToggle)  {radioButton_6.setSelected(false);}
					//Otherwise display sensor menu and toggle
					else {GRPsensorMenu.show(frame,379, 181);}
				}
			}
		});

		radioButton_7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					commands("TOG 8",timmy);
					//If a toggle would cause an error, de-select it
					if (!canToggle) {radioButton_7.setSelected(false);}
					//Otherwise display sensor menu and toggle
					else {GRPsensorMenu.show(frame, 419, 181);}
				}
			}
		});


		//****************************TRIGGER BUTTON LISTENERS***********************************//
		//***************************************************************************************//
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					if ((commands("TRIG 1",timmy) != 1) && numbersEntered) {
						if (eventType.equals("IND")){
							textPane.setText(getINDRaceText());
						}
						else if (eventType.equals("PARIND")){
							textPane.setText(getPARRaceText());
						}
						else if (eventType.equals("GRP")){
							textPane.setText(getGRPRaceText());
						}
						else if(eventType.equals("PARGRP")){
							textPane.setText(getPARGRPRaceText(1));
						}
						if (functionNumber == 2) functionNumber++;
					}
				}
			}
		});

		button_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(power){
					if(commands("TRIG 2",timmy) != -1) {
						if (eventType.equals("IND")){
							textPane.setText(getINDRaceText());
						}
						else if (eventType.equals("PARIND")){
							textPane.setText(getPARRaceText());
						}
						else if (eventType.equals("GRP")){
							textPane.setText(getGRPRaceText());
						}						
						else if(eventType.equals("PARGRP")){
							textPane.setText(getPARGRPRaceText(2));
						}
					}

				}
			}
		});

		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					if ((commands("TRIG 3",timmy) != -1) && numbersEntered) {
						if (eventType.equals("PARIND")){
							textPane.setText(getPARRaceText());
						}
						else if(eventType.equals("PARGRP")){
							textPane.setText(getPARGRPRaceText(3));
						}
						if (functionNumber == 2) functionNumber++;
					}
				}
			}
		});

		button_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					if(commands("TRIG 4",timmy) != -1) {
						if (eventType.equals("PARIND")){
							textPane.setText(getPARRaceText());
						}
						else if(eventType.equals("PARGRP")){
							textPane.setText(getPARGRPRaceText(4));
						}
					}
				}
			}
		});

		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){;

				if ((commands("TRIG 5",timmy) != -1) && numbersEntered) {
					if(eventType.equals("PARGRP")){
						textPane.setText(getPARGRPRaceText(5));
					}
					if (functionNumber == 2) functionNumber++;
				}
				}	
			}
		});

		button_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					if(commands("TRIG 6",timmy) != -1) {
						if(eventType.equals("PARGRP")){
							textPane.setText(getPARGRPRaceText(6));
						}
					}
				}
			}
		});

		button_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(power){
					if ((commands("TRIG 7",timmy) != -1) && numbersEntered) {
						if(eventType.equals("PARGRP")){
							textPane.setText(getPARGRPRaceText(7));
						}

						if (functionNumber == 2) functionNumber++;
					}
				}
			}
		});

		button_7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					if(commands("TRIG 8",timmy) != -1) {
						if(eventType.equals("PARGRP")){
							textPane.setText(getPARGRPRaceText(8));
						}
					}
				}
			}
		});

		btnPrinterPower.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					if (commands("PRINT",timmy) == -1){
						textPane.setText("Cannot print because no racers have started.");
					}
				}
			}
		});

		btnSwap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				if(power){
					commands("SWAP",timmy);
					textPane.setText(getINDRaceText());
				}
			}
		});
	}

	/**
	 * Helper method to get the initial menu
	 * dispaly.
	 * 
	 * @param menuSelection -- index value
	 * @return The menu text at position menuSelection
	 */
	private static String getRaceMenuLines(int menuSelection){
		String [] lines = {"Welcome to Chronotimer 1009 \n"
				+ "\nPlease select race type: \n> Individual\n  Parallel\n  Group\n  Parallel Group\n\n"
				+ "*NOTE: Channel operations only work during events.",
				"Welcome to Chronotimer 1009 \n"
						+ "\nPlease select race type: \n  Individual\n> Parallel\n  Group\n  Parallel Group\n\n"
						+ "*NOTE: Channel operations only work during events.",
						"Welcome to Chronotimer 1009 \n"
								+ "\nPlease select race type: \n  Individual\n  Parallel\n> Group\n  Parallel Group\n\n"
								+ "*NOTE: Channel operations only work during events.",
								"Welcome to Chronotimer 1009 \n"
										+ "\nPlease select race type: \n  Individual\n  Parallel\n  Group\n> Parallel Group\n\n"
										+ "*NOTE: Channel operations only work during events."
		};


		return lines[menuSelection];
	}

	/**
	 * Helper method that generates the display text
	 * during an IND race
	 * 
	 * @return The text that should be displayed
	 */
	private static String getINDRaceText() {
		String queued =  "Queued: ";
		int qPos =  timmy.times.get(0).startTimes.size() + timmy.times.get(0).finishTimes.size();//this should calculate the next racer queued
		if(qPos + 2 <= timmy.racerNums.size() - 1)
			queued += " " + timmy.racerNums.get(qPos) + "\n         " + timmy.racerNums.get(qPos+1) + "\n         "
					+ timmy.racerNums.get(qPos+2) + "\n\n";

		else if(qPos + 1 == timmy.racerNums.size() - 1)
			queued += " " + timmy.racerNums.get(qPos) + "\n         " + timmy.racerNums.get(qPos+1) + "\n         ---\n\n";

		else if(qPos == timmy.racerNums.size() - 1)
			queued += " " + timmy.racerNums.get(qPos) + "\n         ---" + "\n         ---\n\n";

		else if(qPos < timmy.racerNums.size() - 1)
			queued += " ---" + "\n         ---" + "\n         ---\n\n";
		else queued += " ---" + "\n         ---" + "\n         ---\n\n";


		String current = "Current: ";

		int marker = 0;
		for(int i = timmy.times.get(0).finishTimes.size(), j=0; j < timmy.times.get(0).startTimes.size(); i++, j++){
			if (marker!= 0) {current += ", " + timmy.racerNums.get(i);}
			else {current += timmy.racerNums.get(i);}
			marker++;
		}
		if (timmy.times.get(0).startTimes.size() ==0) {
			if (marker != 0) {current += ", ---";}
			else {current += "---";}
			marker++;
		}

		String finished = "\n\nFinished: " + ((timmy.times.get(0).finishTimes.size() > 0) ? (timmy.racerNums.get(timmy.times.get(0).finishTimes.size()-1) + "   "
				+ "  " + timmy.parseTime(timmy.times.get(0).finishTimes.peekLast())):("---"));
		String endRace = "\n\nPress FUNCTION to end race.";

		return queued + current + finished + endRace;
	}

	/**
	 * Helper method that generates the display text
	 * during a PAR race
	 * 
	 * @return The text that should be displayed
	 */
	private static String getPARRaceText() {
		String upNext = "Queued: ";
		int sr = timmy.times.get(0).startTimes.size() + timmy.times.get(1).startTimes.size();
		int fr = timmy.times.get(0).finishTimes.size() + timmy.times.get(1).finishTimes.size();
		int qPos = sr + fr;

		if(qPos + 1 <= timmy.racerNums.size() - 1)
			upNext += timmy.racerNums.get(qPos) + "\n        " + timmy.racerNums.get(qPos+1);
		else if(qPos == timmy.racerNums.size() - 1)
			upNext += timmy.racerNums.get(qPos) + "\n        ---";
		else 
			upNext += "---" + "\n        ---";

		String current = "\n\nCurrent on track 1: ";
		current += (timmy.times.get(0).startTimes.size() > 0) ? timmy.times.get(0).racerNums.get((timmy.times.get(0).startTimes.size() + timmy.times.get(0).finishTimes.size()) - 1) + "" : "---";
		current += "\nCurrent on track 2: ";
		current += (timmy.times.get(1).startTimes.size() > 0) ? timmy.times.get(1).racerNums.get((timmy.times.get(1).startTimes.size() + timmy.times.get(1).finishTimes.size()) - 1) : "---";

		String finished = "\n\nFinished on track 1: ";
		finished += (timmy.times.get(0).finishTimes.size() > 0) ? timmy.times.get(0).racerNums.get(timmy.times.get(0).finishTimes.size() -1) + "" : "---";
		finished += "\nFinished on track 2: ";
		finished += (timmy.times.get(1).finishTimes.size() > 0) ? timmy.times.get(1).racerNums.get(timmy.times.get(1).finishTimes.size() -1) : "---";

		String endRace = "\n\nPress FUNCTION to end race.";

		return upNext + current + finished + endRace;
	}

	/**
	 * Helper method that generates the display text
	 * during a GRP race
	 * 
	 * @return The text that should be displayed
	 */
	public static String getGRPRaceText(){
		String start = "Start Time:   " + timmy.times.get(0).getStartTime() + "\n\n\n";
		LinkedList<Long> times = timmy.times.get(0).getTimes();

		String nextFinish = "Next Finish Time:  \n";

		if(!times.isEmpty()){
			nextFinish += "Racer #" + times.size() + ": " + timmy.parseTime(times.getLast()) + "\n\n";
		}
		else{
			nextFinish += "Racer #----: ----\n\n";
		}

		String endRace = "Press PRINT at any time to print race results.\n\nPress FUNCTION to end race.";

		return start + nextFinish + endRace;
	}

	/**
	 * Helper method that generates the display text
	 * during a PARGRP race
	 * 
	 * @return The text that should be displayed
	 */
	public static String getPARGRPRaceText(int whichTrig){
		String start = "Start Time:\t" + timmy.times.get(0).getStartTime() + "\n\n";
		if(timmy.times.get(0).finishTimes.get(whichTrig-1) != -1 && didTheyFinish[whichTrig - 1].equals("---")){
			didTheyFinish[whichTrig - 1] = timmy.parseTime(timmy.times.get(0).finishTimes.get(whichTrig-1)); //didTheyFinished all initialized to ---
		}
		String posNums = "Finish Times:\n";

		for(int i = 0; i < 4; i++){
			if (didTheyFinish[i].equals("---")) {
				posNums += "R" + (i+1) + ": " + didTheyFinish[i] + "        R" + (i+5) + ": " + didTheyFinish[i+4] + "\n";
			} else {
				posNums += "R" + (i+1) + ": " + didTheyFinish[i] + " R" + (i+5) + ": " + didTheyFinish[i+4] + "\n";
			}

		}

		String endRace = "\nPress PRINT at any time to print race results.\n\nPress FUNCTION to end race.";

		return start + posNums + endRace;
	}

	/**
	 * Helper method that takes in a command (from a file
	 * or from GUI) and a chronotimer, and performs an
	 * action on the Chronotimer.
	 * 
	 * @param input -- command
	 * @param timer -- Chronotimer object
	 * @return In the case of an error during toggle or trigger, 
	 * 		a -1 is returned, otherwise a 0 is returned.
	 */
	private static long commands(String input, Chronotimer timer) {	
		if (!cmdFile) {
			date = new Date();
			timeStamp = date.toString().substring(11,19);
		}
		else {
			String[] fileLine = input.split("\t");
			input = fileLine[1];
			timeStamp = fileLine[0];
		}

		long temp = 0;
		int channel;
		int trigger;

		String secondaryParam = "";

		if (input.contains(" ")){
			String[] inputComponents = input.split(" ");
			input = inputComponents[0];
			secondaryParam = inputComponents[1];
		}

		switch(input){
		case "POWER":
			power = timer.powerToggle();
			break;
		case "EXIT":
			timer.powerToggle();
			break;
		case "RESET":
			timer.reset();
			break;
		case "TIME": 
			//DO NOTHING
			break;
		case "DNF":
			timer.DNF();
			break;
		case "CANCEL":
			timer.cancel();
			break;
		case "TOG":
			channel = Integer.parseInt(secondaryParam);
			canToggle = timer.toggle(channel);
			break;
		case "TRIG":
			trigger = Integer.parseInt(secondaryParam);
			//Special function for file input
			if(cmdFile){timer.trigger(trigger, timeStamp);}
			else{temp = timer.trigger(trigger);}
			break;
		case "NEWRUN":
			timer.newRun();
			break;
		case "START":
			timer.start();
			break;
		case "ENDRUN":
			timer.endRun();
			break;
		case "FINISH":
			timer.finish();
			break;
		case "NUM":
			int number = Integer.parseInt(secondaryParam);
			timer.setNum(number);
			break;
		case "EVENT":
			String event = secondaryParam;
			timer.setEvent(event);
			break;
		case "PRINT":
			temp = timer.print();
			break;
		case "SWAP": 
			timer.swap();
			break;
		default:
			System.out.println("You can't do that");
			break;
		}
		//Print to console 
		System.out.println(timeStamp + "\t" + input + " " + secondaryParam);

		//Print to paper tape if GUI is used
		if (!cmdFile){
			String currentText = textPane_2.getText();

			int newLineCount = 0;
			for (int i = 0; i < currentText.length(); i++){
				if (currentText.charAt(i) == '\n'){newLineCount++;}
				if(newLineCount == 9){
					String[] lines = currentText.split("\n");
					currentText = lines[1] + "\n" + lines[2] + "\n" + lines[3] + "\n" + lines[4] + "\n" + lines[5] + "\n" 
							+ lines[6] + "\n" + lines[7] + "\n" + lines[8] + "\n";
				}
			}
			textPane_2.setText(currentText + timeStamp + "\t" + input + " " + secondaryParam + "\n");
		}

		return temp;
	}
}