import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Printer {
	PrintStream output;
	
	private void paperTape(ArrayList<Racer> racers, int rNum){
		int height = racers.size() * 70;

		JFrame frame = new JFrame();
		frame.setResizable(true);
        frame.add( new JLabel("RACE " + rNum + " RESULTS", SwingConstants.CENTER), BorderLayout.NORTH );
        
		JTextPane results = new JTextPane();
		results.setEditable(false);
		results.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		frame.setBounds(500, 500, 280, height);
		
		
		frame.setVisible(true);
		results.setVisible(true);
		
		frame.add(results);
		
		String output = "";
		
		output += "----------------------------------\n";

		for (Racer r: racers){
			output += r.toString();
			output += "\n----------------------------------\n";
		}
		
		output += "";
	
		results.setText(output);
	}

	/**
	 * Prints the result of the race either to a file or the console.
	 *
	 * @param racers Is the collection of racer objects; each object
	 * knows its number and its time
	 * @param outputSource Determines if output is to different window (true)
	 * or to file (false)
	 * 
	 */
	public void print(ArrayList<Racer> racers, boolean outputSource,int raceNum) {
		String fileName = "Race" + raceNum +".txt";

		//Print to console
		if (outputSource){
				paperTape(racers, raceNum);
		}
		//Print to file
		else{
			try {
				output = new PrintStream(new File(fileName));
			} catch (FileNotFoundException e) {
				System.out.println("ERROR creating file. Proceeding with console output.");
				output = System.out;
			}

			output.println("\n" + "          RACE " + raceNum + " RESULTS          ");
			output.println("----------------------------------");

			for (Racer r: racers){
				output.println(r.toString());
				output.println("----------------------------------");
			}
			
			output.println("");
		}

	}
}