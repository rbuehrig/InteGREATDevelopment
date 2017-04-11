import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JLayeredPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChronoTimerWindow {
	private JFrame frame;
	int menuVersion = 0;
    
	static Chronotimer timmy = new Chronotimer();
	static Date date;
	static String timeStamp;
	static int functionNumber;
	static boolean cmdFile = false;
	static Scanner scan;
	/**
	 * Launch the application.
	 */
	//public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ChronoTimerWindow window = new ChronoTimerWindow();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public ChronoTimerWindow() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame =  new JFrame();
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
		frame.getContentPane().add(radioButton_1);
		
		JButton button_1 = new JButton("");
		button_1.setBounds(339, 62, 28, 16);
		frame.getContentPane().add(button_1);
		
		JRadioButton radioButton_2 = new JRadioButton("");
		radioButton_2.setBounds(379, 86, 28, 23);
		frame.getContentPane().add(radioButton_2);
		
		JRadioButton radioButton_3 = new JRadioButton("");
		radioButton_3.setBounds(419, 86, 28, 23);
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
		frame.getContentPane().add(radioButton_5);
		
		JRadioButton radioButton_6 = new JRadioButton("");
		radioButton_6.setBounds(379, 181, 28, 23);
		frame.getContentPane().add(radioButton_6);
		
		JRadioButton radioButton_7 = new JRadioButton("");
		radioButton_7.setBounds(419, 181, 28, 23);
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
		ImageIcon triDown = new ImageIcon("/Users/RylieBuehrig/git/InteGREATDevelopment/ChronoTimer/TrianglePointingDown.png");
		Image scaledTriDown = triDown.getImage().getScaledInstance(28, 26,Image.SCALE_DEFAULT);
		label_triDown.setIcon(new ImageIcon(scaledTriDown));
		frame.getContentPane().add(label_triDown);
		
		JLabel label_triLeft = new JLabel();
		
		label_triLeft.setSize(30, 30);
		label_triLeft.setLocation(44, 251);
		ImageIcon triLeft = new ImageIcon("/Users/RylieBuehrig/git/InteGREATDevelopment/ChronoTimer/TrianglePointingLeft.png");
		Image scaledTriLeft = triLeft.getImage().getScaledInstance(28, 26,Image.SCALE_DEFAULT);
		label_triLeft.setIcon(new ImageIcon(scaledTriLeft));
		frame.getContentPane().add(label_triLeft);
		
		JLabel label_triRight = new JLabel();
		label_triRight.setSize(30, 30);
		label_triRight.setLocation(86, 251);
		ImageIcon triRight = new ImageIcon("/Users/RylieBuehrig/git/InteGREATDevelopment/ChronoTimer/TrianglePointingRight.png");
		Image scaledTriRight = triRight.getImage().getScaledInstance(28, 26,Image.SCALE_DEFAULT);
		label_triRight.setIcon(new ImageIcon(scaledTriRight));
		frame.getContentPane().add(label_triRight);
		
		JLabel label_triUp = new JLabel();
		label_triUp.setSize(30, 30);
		label_triUp.setLocation(164, 251);
		ImageIcon triUp = new ImageIcon("/Users/RylieBuehrig/git/InteGREATDevelopment/ChronoTimer/TrianglePointingUp.png");
		Image scaledTriUp = triUp.getImage().getScaledInstance(28, 26,Image.SCALE_DEFAULT);
		label_triUp.setIcon(new ImageIcon(scaledTriUp));
		frame.getContentPane().add(label_triUp);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		textPane.setText(getRaceMenuLines(menuVersion));
		textPane.setEditable(false);
		textPane.setBounds(242, 214, 215, 173);
		frame.getContentPane().add(textPane);
		
		JLabel lblQueueRunning = new JLabel("Queue / Running / Final Time");
		lblQueueRunning.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblQueueRunning.setBounds(278, 397, 164, 16);
		frame.getContentPane().add(lblQueueRunning);
		
		JButton btnPrinterPower = new JButton("Printer Power");
		btnPrinterPower.setBounds(536, 1, 117, 29);
		frame.getContentPane().add(btnPrinterPower);
		
		JPanel panel = new JPanel();
		panel.setBounds(525, 214, 147, 173);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(4, 3));
		
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
        layeredPane.setBounds(525, 42, 141, 148);
        frame.getContentPane().add(layeredPane);
        
        JTextPane textPane_2 = new JTextPane();
        textPane_2.setEditable(false);
        layeredPane.setLayer(textPane_2, 1);
        textPane_2.setBounds(18, 16, 104, 107);
        textPane_2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        layeredPane.add(textPane_2);
        
        JTextPane textPane_1 = new JTextPane();
        textPane_1.setEditable(false);
        textPane_1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textPane_1.setBounds(6, 54, 129, 88);
        layeredPane.add(textPane_1);
        
        
        label_triUp.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (functionNumber == 1){
        			if(menuVersion - 1 >= 0) {
        				menuVersion--;
        				textPane.setText(getRaceMenuLines(menuVersion));
        			}
        		}
        	}
        });
        
        label_triDown.addMouseListener(new MouseAdapter() {
        	@Override
			public void mouseClicked(MouseEvent e) {        		
        		if (functionNumber == 1){
        			if (menuVersion + 1< 3) {
        				menuVersion++;
            			textPane.setText(getRaceMenuLines(menuVersion));
        			}
        		}
        		
        		else if (functionNumber == 2){
        			
        		}
        		
        		
			}
        });
        
		btnFunction.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//menuVersion = 0 --> IND
				//menuVersion = 1 --> PAR
				//menuVersion = 2 -- > GRP
				if (functionNumber == 1){
					if (menuVersion == 0) {commands("EVENT IND",timmy);}
					if (menuVersion == 1) {commands("EVENT PAR",timmy);}
					if (menuVersion == 2) {commands("EVENT GRP",timmy);}
					
					commands("NEWRUN",timmy);
					
					textPane.setText("Enter racer's number on the keypad. Press FUNCTION to enter and key # to end.\n");
				}
				else if (functionNumber == 2){
					String[] items = textPane.getText().split("\n");
					if (!items[1].equals("")) {commands("NUM " + items[1],timmy);}
					else {
						textPane.setText("Waiting for race to start.");
					}
				}
				else if (functionNumber == 3){}
				
				functionNumber++;
			}
		});
 
		btnPower.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				commands("POWER",timmy);
			}
		});
		
		button_12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String currentText = textPane.getText();
				textPane.setText(currentText + "1");
			}
		});
		
		button_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String currentText = textPane.getText();
				textPane.setText(currentText + "2");
			}
		});
		
		button_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String currentText = textPane.getText();
				textPane.setText(currentText + "3");
			}
		});
		
		button_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String currentText = textPane.getText();
				textPane.setText(currentText + "4");
			}
		});
		
		btnNewButton_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String currentText = textPane.getText();
				textPane.setText(currentText + "5");
			}
		});
		
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String currentText = textPane.getText();
				textPane.setText(currentText + "6");
			}
		});
		
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String currentText = textPane.getText();
				textPane.setText(currentText + "7");
			}
		});
		
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String currentText = textPane.getText();
				textPane.setText(currentText + "8");
			}
		});
		
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String currentText = textPane.getText();
				textPane.setText(currentText + "9");
			}
		});
		
		btnJ.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String currentText = textPane.getText();
				textPane.setText(currentText + "*");
			}
		});
		
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String currentText = textPane.getText();
				textPane.setText(currentText + "0");
			}
		});
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String currentText = textPane.getText();
				textPane.setText(currentText + "#");
			}
		});
		
		radioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				commands("TOG 1",timmy);
			}
		});
		
		radioButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				commands("TOG 2",timmy);
			}
		});
		
		radioButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				commands("TOG 3",timmy);
			}
		});
		
		radioButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				commands("TOG 4",timmy);
			}
		});
		
		radioButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				commands("TOG 5",timmy);
			}
		});
		
		radioButton_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				commands("TOG 6",timmy);
			}
		});
		
		radioButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				commands("TOG 7",timmy);
			}
		});
		
		radioButton_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				commands("TOG 8",timmy);
			}
		});
		
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				commands("TRIG 1",timmy);
			}
		});
		
		button_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				commands("TRIG 2",timmy);
			}
		});
		
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				commands("TRIG 3",timmy);
			}
		});
		
		button_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				commands("TRIG 4",timmy);
			}
		});
		
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				commands("TRIG 5",timmy);
			}
		});
		
		button_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				commands("TRIG 6",timmy);
			}
		});
		
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				commands("TRIG 7",timmy);
			}
		});

		button_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				commands("TRIG 8",timmy);
			}
		});
		
		btnPrinterPower.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				commands("PRINT",timmy);
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
	
	
	private static void commands(String input, Chronotimer timmy) {	
		date = new Date();
		timeStamp = date.toString().substring(11,19);
		
		
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
			timmy.powerToggle();
			break;
		case "EXIT":
			timmy.powerToggle();
			//?? programRunning = false;
			break;
		case "RESET":
			timmy.reset();
			break;
		case "TIME": 
			//?? param = scan.next();
			break;
		case "DNF":
			timmy.DNF();
			break;
		case "CANCEL":
			timmy.cancel();
			break;
		case "TOG":
			channel = Integer.parseInt(secondaryParam);
			timmy.toggle(channel);
			break;
		case "TOGGLE":
			channel = Integer.parseInt(secondaryParam);
			timmy.toggle(channel);
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
				timmy.trigger(trigger, timeStamp);
			}
			else{
				timmy.trigger(trigger);
			}
			
			break;
		case "NEWRUN":
			timmy.newRun();
			break;
		case "START":
			timmy.start();
			break;
		case "ENDRUN":
			timmy.endRun();
			break;
		case "FINISH":
			timmy.finish();
			break;
		case "NUM":
			int number = Integer.parseInt(secondaryParam);
			timmy.setNum(number);
			break;
		case "EVENT":
			String event = secondaryParam;
			timmy.setEvent(event);
			break;
		case "PRINT":
			timmy.print();
			break;

		default:
			System.out.println("You can't do that");
			break;
		}
		System.out.println(timeStamp + "\t" + input + " " + secondaryParam);
	}
}