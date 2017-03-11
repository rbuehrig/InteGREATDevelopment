import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Printer {
	int raceNumber = 1;
	PrintStream output;

	/**
	 * Prints the result of the race either to a file or the console.
	 *
	 * @param racers Is the collection of racer objects; each object
	 * knows its number and its time
	 * @param outputSource Determines if output is to console (true)
	 * or to file (false)
	 * 
	 */
	public void print(ArrayList<Racer> racers, boolean outputSource) {
		String fileName = "Race" + raceNumber +".txt";
		
		//Print to console
		if (outputSource){
			output = System.out;

		}
		//Print to file
		else{
			try {
				output = new PrintStream(new File(fileName));
			} catch (FileNotFoundException e) {
				System.out.println("ERROR creating file. Proceeding with console output.");
				output = System.out;
			}
		}

		output.println("             RACE " + raceNumber + " RESULTS             " + "\n");
		output.println("----------------------------------------");

		for (int i = 0; i < racers.size(); i++){
			if (racers.get(i).raceTimeLong != ((long) -1)){
				output.println("Racer: " + racers.get(i).number);
				output.println("Time: " + racers.get(i).raceTimeString);
				output.println("----------------------------------------");
			}
			else{
				output.println("Racer: " + racers.get(i).number);
				output.println("Did not finish the race.\n");
				output.println("----------------------------------------");
			}


			/*else{
				System.out.println("Racer: " + racerNums.get(i));
				System.out.println("Did not start the race.\n");
			}*/
		}
		raceNumber++;
	}



//	for (Racer r: dir) {
//		System.out.println(r.toString());
//	}
	
}
