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
import java.text.*;
import java.util.Date;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
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
import javax.swing.MenuElement;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultCaret;

//////////////////////////////////////////
//InteGREAT Development
//Class: CS 361
//Authors: Nicholas 
//
//
//Description: Simulator serves as the 
//interface for console or file input.
//
//////////////////////////////////////////

public class Simulator {
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
	static int numRacers;
	static String eventType;
	static int finishedRacer;

	public static void main(String[] args) {		
		try{	
			scan = new Scanner(new File("CTS3R1.txt"));
			cmdFile = true;
		}
		catch(FileNotFoundException e){cmdFile = false;}	

		if (cmdFile){
			while(scan.hasNextLine()){
				commands(scan.nextLine(),timmy);
			}
		}
		else {
			cmdFile = false;
			menuVersion = 0;
			functionNumber = 1;
			funcCompleted = false;
			power = false;
			canToggle = true;
			numbersEntered = false;
			numRacers = 0;
			finishedRacer = 0;

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

	public Simulator() throws IOException {
		initialize();
	}

	private static void initialize() throws IOException{
		frame =  new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 709, 441);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

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

		JRadioButton radioButton = new JRadioButton("");
		radioButton.setBounds(299, 86, 28, 23);
		radioButton.setEnabled(false);
		frame.getContentPane().add(radioButton);

		JButton button = new JButton("");
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(299, 62, 28, 16);
		frame.getContentPane().add(button);

		JLabel label = new JLabel("1");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(299, 34, 28, 16);
		frame.getContentPane().add(label);

		JRadioButton radioButton_1 = new JRadioButton("");
		radioButton_1.setBounds(339, 86, 28, 23);
		radioButton_1.setEnabled(false);
		frame.getContentPane().add(radioButton_1);

		JButton button_1 = new JButton("");
		button_1.setBounds(339, 62, 28, 16);
		frame.getContentPane().add(button_1);

		JRadioButton radioButton_2 = new JRadioButton("");
		radioButton_2.setBounds(379, 86, 28, 23);
		radioButton_2.setEnabled(false);
		frame.getContentPane().add(radioButton_2);

		JRadioButton radioButton_3 = new JRadioButton("");
		radioButton_3.setBounds(419, 86, 28, 23);
		radioButton_3.setEnabled(false);
		frame.getContentPane().add(radioButton_3);

		JButton button_2 = new JButton("");
		button_2.setBounds(379, 62, 28, 16);
		frame.getContentPane().add(button_2);

		JButton button_3 = new JButton("");
		button_3.setBounds(419, 62, 28, 16);
		frame.getContentPane().add(button_3);

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

		JLabel lblFinish = new JLabel("Finish");
		lblFinish.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFinish.setBounds(226, 152, 61, 16);
		frame.getContentPane().add(lblFinish);

		JLabel label_4 = new JLabel("Enable/Disable");
		label_4.setBounds(192, 185, 95, 16);
		frame.getContentPane().add(label_4);

		JRadioButton radioButton_4 = new JRadioButton("");
		radioButton_4.setBounds(299, 181, 28, 23);
		radioButton_4.setEnabled(false);
		frame.getContentPane().add(radioButton_4);

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

		JButton btnFunction = new JButton("FUNCTION");
		btnFunction.setBounds(34, 219, 117, 29);
		frame.getContentPane().add(btnFunction);

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

		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		textPane.setEditable(false);
		textPane.setEnabled(false);
		textPane.setBounds(242, 214, 215, 173);
		frame.getContentPane().add(textPane);

		JLabel lblQueueRunning = new JLabel("Queue / Running / Final Time");
		lblQueueRunning.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblQueueRunning.setBounds(270, 397, 164, 16);
		frame.getContentPane().add(lblQueueRunning);

		JButton btnPrinterPower = new JButton("Print");
		btnPrinterPower.setBounds(531, 19, 117, 29);
		frame.getContentPane().add(btnPrinterPower);

		JPanel panel = new JPanel();
		panel.setBounds(519, 214, 147, 173);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(4, 3));

		//ADDING POPUP MENU HERE 4/18
		JPopupMenu sensorMenu = new JPopupMenu();
		JMenuItem pushButton = new JMenuItem("Push Button");
		JMenuItem elecEye = new JMenuItem("Electric Eye");
		JMenuItem gate = new JMenuItem("Gate Sensor");
		JMenuItem pad = new JMenuItem("Pad Sensor");

		sensorMenu.add(pushButton);
		sensorMenu.add(elecEye);
		sensorMenu.add(gate);
		sensorMenu.add(pad);
		//END OF POPUP MENU CODE

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

		JButton btnSwap = new JButton("SWAP");
		btnSwap.setBounds(34, 344, 117, 29);
		frame.getContentPane().add(btnSwap);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(505, 46, 175, 155);
		frame.getContentPane().add(layeredPane);

		textPane_2 = new JTextPane();
		layeredPane.setLayer(textPane_2, 1);
		textPane_2.setBounds(17, 6, 140, 130);
		textPane_2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textPane_2.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		textPane_2.setAutoscrolls(true);
		DefaultCaret caret = (DefaultCaret)textPane_2.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		layeredPane.add(textPane_2);

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textPane_1.setBounds(6, 61, 163, 88);
		layeredPane.add(textPane_1);


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
						if (menuVersion + 1< 3) {
							menuVersion++;
							textPane.setText(getRaceMenuLines(menuVersion));
						}
					}
				}
			}
		});

		btnFunction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					//menuVersion = 0 --> IND
					//menuVersion = 1 --> PAR
					//menuVersion = 2 -- > GRP
					if (functionNumber == 1){					
						if (menuVersion == 0) {commands("EVENT IND",timmy); funcCompleted = true; eventType = "IND";}
						if (menuVersion == 1) {commands("EVENT PARIND",timmy); funcCompleted = true; eventType = "PARIND";}
						if (menuVersion == 2) {commands("EVENT GRP",timmy); funcCompleted = true; eventType = "GRP";}

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
							numRacers++;
						}
						else {
							numbersEntered = true;
							textPane.setText("Waiting for race to start... Toggle channels before starting race. Then press a Start button to begin race.");
							//functionNumber++;
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
				}

			}
		});

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


		//ADDING RADIO BUTTON POPUP MENUS HERE
		radioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					commands("TOG 1",timmy);
					if (!canToggle) {radioButton.setSelected(false);}
					else {sensorMenu.show(frame, 299, 86);}
				}
			}
		});

		radioButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					commands("TOG 3",timmy);
					if (!canToggle)  {radioButton_1.setSelected(false);}
					else {sensorMenu.show(frame, 339, 86);}
				}
			}
		});

		radioButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(power){
					commands("TOG 5",timmy);
					if (!canToggle)  {radioButton_2.setSelected(false);}
					else {sensorMenu.show(frame, 379, 86);}
				}
			}
		});

		radioButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(power){
					commands("TOG 7",timmy);
					if (!canToggle)  {radioButton_3.setSelected(false);}
					else {sensorMenu.show(frame, 419, 86);}
				}
			}
		});

		radioButton_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(power){
					commands("TOG 2",timmy);
					if (!canToggle)  {radioButton_4.setSelected(false);}
					else {sensorMenu.show(frame, 299, 181);}
				}
			}
		});

		radioButton_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					commands("TOG 4",timmy);
					if (!canToggle)  {radioButton_5.setSelected(false);}
					else {
						sensorMenu.show(frame, 339, 181);
					}
				}
			}
		});

		radioButton_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					commands("TOG 6",timmy);
					if (!canToggle)  {radioButton_6.setSelected(false);}
					else {sensorMenu.show(frame,379, 181);}
				}
			}
		});

		radioButton_7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					commands("TOG 8",timmy);
					if (!canToggle) {radioButton_7.setSelected(false);}
					else {sensorMenu.show(frame, 419, 181);}
				}
			}
		});

		//END POPUP MENU CODE

		//******TRIGGER BUTTONS********
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){

					if ((commands("TRIG 1",timmy) != 1) && numbersEntered) {
						if (eventType.equals("IND")){
							textPane.setText(getINDRaceText());
						}
						else if (eventType.equals("PAR")){

						}
						else if (eventType.equals("GRP")){
							textPane.setText(getGRPRaceText());
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
						//						textPane.setText("Start time not triggered, please trigger channel 1 "
						//								+ "before triggering channel 2.");
						finishedRacer++;
						
						if (eventType.equals("IND")){
							textPane.setText(getINDRaceText());
						}
						else if (eventType.equals("PAR")){

						}
						else if (eventType.equals("GRP")){
							textPane.setText(getGRPRaceText());
						}
					}
					
				}
			}
		});

		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					commands("TRIG 3",timmy);
					if (numbersEntered) {
						textPane.setText("Race in progress... Press Print to output current results. Press FUNCTION "
								+ "to end race and output results to file.");
						if (functionNumber == 2) functionNumber++;
					}
					else {finishedRacer++;}
				}
			}
		});

		button_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					if(commands("TRIG 4",timmy) == -1) {
						textPane.setText("Start time not triggered, please trigger channel 3 "
								+ "before triggering channel 4.");
					}
					else {finishedRacer++;}
				}
			}
		});

		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					commands("TRIG 5",timmy);
					if (numbersEntered) {
						textPane.setText("Race in progress... Press Print button to output current results. Press FUNCTION button "
								+ "to end race and output results to file.");
						if (functionNumber == 2) functionNumber++;
					}
				}	
			}
		});

		button_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					if(commands("TRIG 6",timmy) == -1) {
						textPane.setText("Start time not triggered, please trigger channel 5 "
								+ "before triggering channel 6.");
					}
					else {finishedRacer++;}
				}
			}
		});

		button_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(power){
					commands("TRIG 7",timmy);
					if (numbersEntered) {
						textPane.setText("Race in progress... Press Print button to output current results. Press FUNCTION button "
								+ "to end race and output results to file.");
						if (functionNumber == 2) functionNumber++;
					}
				}
			}
		});

		button_7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power){
					if(commands("TRIG 8",timmy) == -1) {
						textPane.setText("Start time not triggered, please trigger channel 7 "
								+ "before triggering channel 8.");
					}
					else{finishedRacer++;}
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
				if(power){commands("SWAP",timmy);}
			}
		});
	}

	private static String getRaceMenuLines(int menuSelection){
		String [] lines = {"Welcome to Chronotimer 1009     \n                                                        "
				+ "\nPlease select race type: \nIndividual  < \nParallel    \nGroup    ",
				"Welcome to Chronotimer 1009     \n                                                        "
						+ "\nPlease select race type: \nIndividual    \nParallel  < \nGroup    ",
						"Welcome to Chronotimer 1009     \n                                                        "
								+ "\nPlease select race type: \nIndividual    \nParallel    \nGroup  < "};

		return lines[menuSelection];
	}

	//Helper for active race text
	private static String getINDRaceText() {
		//		String queued =  "Queued:  " + ((timmy.racerNums.size() >= finishedRacer + 2) ? (timmy.racerNums.get(finishedRacer + 1)):("---"))
		//				+ ((timmy.racerNums.size() >= finishedRacer + 3) ? ("\n              " + timmy.racerNums.get(finishedRacer + 2)):("\n              ---"))
		//				+ ((timmy.racerNums.size() >= finishedRacer + 4) ? ("\n              " + timmy.racerNums.get(finishedRacer + 3) + "\n\n"):("\n              ---\n\n"))
		//				
		//				
		//				+ "Current: " + (timmy.times.get(0).startTimes.size() > 0  && (finishedRacer > 0 ? 
		//						(timmy.times.get(0).racerNums.peekLast() != timmy.racerNums.get(finishedRacer - 1)):(false)) ? 
		//								(" " + timmy.times.get(0).racerNums.peekLast() + "\n\n"):(" ---\n\n"));

		String queued =  "Queued:  ";
		int qPos =  timmy.times.get(0).startTimes.size() + finishedRacer;//this should calculate the next racer queued

		if(qPos + 2 <= timmy.racerNums.size() - 1)
			queued += timmy.racerNums.get(qPos) + "\n              " + timmy.racerNums.get(qPos+1) + "\n              "
					+ timmy.racerNums.get(qPos+2) + "\n\n";
		
		else if(qPos + 1 == timmy.racerNums.size() - 1)
			queued += timmy.racerNums.get(qPos) + "\n              " + timmy.racerNums.get(qPos+1) + "\n              ---\n\n";
		
		else if(qPos == timmy.racerNums.size() - 1)
			queued += timmy.racerNums.get(qPos) + "\n              ---" + "\n              ---\n\n";	
		
		else if(qPos < timmy.racerNums.size() - 1)
			queued += "---" + "\n               ---" + "\n               ---\n\n";


		String current = "Current:  ";

		for(int i = timmy.times.get(0).racerTimes.size(), j=0; j < timmy.times.get(0).startTimes.size(); i++, j++){
			current += timmy.racerNums.get(i);
		}
		if (timmy.times.get(0).startTimes.size() ==0) {current += "---";}
		
		String finished = "\n\nFinished: " + ((timmy.times.get(0).racerTimes.size() > 0) ? (timmy.racerNums.get(finishedRacer - 1) + "   "
				+ "  " + timmy.parseTime(timmy.times.get(0).racerTimes.peekLast())):("---"));
		
		String endRace = "\n\nPress FUNCTION to end race.";


		return queued + current + finished + endRace;
	}
	
	public static String getGRPRaceText(){
		String groupText = "Start Time:\t" + timmy.times.get(0).getGroupStartTime();
		
	}

	private static long commands(String input, Chronotimer timer) {	
		date = new Date();
		timeStamp = date.toString().substring(11,19);		
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
			//?? programRunning = false;
			break;
		case "RESET":
			timer.reset();
			break;
		case "TIME": 
			//?? param = scan.next();
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
			/*hacky way of forcing triggers on only 1 or 2
			 * this way we can have all the channels as actual objects
			 * to avoid null pointer dereferencing
			 * 
			 * This will need to be modified when we add new event types
			 */
			if(cmdFile){
				timer.trigger(trigger, timeStamp);
			}
			else{
				temp = timer.trigger(trigger);
			}

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

		//Print to paper tape
		String currentText = textPane_2.getText();
		int newLineCount = 0;
		for (int i = 0; i < currentText.length(); i++){
			if (currentText.charAt(i) == '\n'){newLineCount++;}
			if(newLineCount == 10){
				String[] lines = currentText.split("\n");
				currentText = lines[1] + "\n" + lines[2] + "\n" + lines[3] + "\n" + lines[4] + "\n" + lines[5] + "\n" 
						+ lines[6] + "\n" + lines[7] + "\n" + lines[8] + "\n" + lines [9] + "\n";
			}
		}
		textPane_2.setText(currentText + timeStamp + "\t" + input + " " + secondaryParam + "\n");

		return temp;
	}
}