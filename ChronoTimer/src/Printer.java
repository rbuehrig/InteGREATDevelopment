import java.io.PrintWriter;
import java.util.ArrayList;

public class Printer {
	int raceNumber = 0;

	
	
	
	
	public void printToConsole(ArrayList<Racer> racers){
		raceNumber++;
		
		System.out.println("             RACE " + raceNumber + " RESULTS             " + "\n");
		System.out.println("----------------------------------------");
		
		for (int i = 0; i < racers.size(); i++){
			if (racers.get(i).raceTimeLong != ((long) -1)){
				System.out.println("Racer: " + racers.get(i).number);
				System.out.println("Time: " + racers.get(i).raceTimeString);
				System.out.println("----------------------------------------");
			}
			else{
				System.out.println("Racer: " + racers.get(i).number);
				System.out.println("Did not finish the race.\n");
				System.out.println("----------------------------------------");
			}
			
			 	
			/*else{
				System.out.println("Racer: " + racerNums.get(i));
				System.out.println("Did not start the race.\n");
			}*/
		}
	}

	public void printToFile(ArrayList<Racer> dir){
		String fileName = "Race" + raceNumber +".txt";
		PrintWriter out =  new PrintWriter(fileName);
		
		try {
			out.println();
		}
		catch (){
			
		}
		
		for (int i = 0; i < dir.size(); i++){
			if (dir.get(i) != ((long) -1)){
				System.out.println("Racer: " + racerNums.get(i));
				System.out.println("Total Time: " + parseTime(racerTimes.get(i)) + "\n");
			}
			else{
				System.out.println("Racer: " + racerNums.get(i));
				System.out.println("Did not finish the race.\n");
			}
			
			else{
				System.out.println("Racer: " + racerNums.get(i));
				System.out.println("Did not start the race.\n");
			}
		}
		
		
		
		for (Racer r: dir) {
			System.out.println(r.toString());
		}
	}
}