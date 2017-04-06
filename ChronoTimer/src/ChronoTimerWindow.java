import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;


import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JLayeredPane;

public class ChronoTimerWindow {
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChronoTimerWindow window = new ChronoTimerWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChronoTimerWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(256, 214, 201, 173);
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
 
	}
}
