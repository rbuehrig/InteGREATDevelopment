//////////////////////////////////////////
//InteGREAT Development
//Class: CS 361
//
//Description: Racer object.
//////////////////////////////////////////
public class Racer implements Comparable<Racer>{
	//Racer's bib number
	protected int number;
	
	//Racer's last name
	public String last;
	
	//Racer's first name
	public String first;
	
	//Racer's time as a time stamp string
	protected String raceTimeString;
	
	//Racer's time as a long in milliseconds
	protected long  raceTimeLong;
	
	/**
	 * Constructor initializes class fields.
	 * 
	 * For racer with no name entered.
	 */
	public Racer(int num, String timeStr, long timeLong){
		this.number = num; 
		this.raceTimeString = timeStr;
		this.raceTimeLong = timeLong;
		this.last = "";
		this.first = "";
	}

	/**
	 * Overloaded constructor 
	 *    
	 * For racer with name entered.
	 */
	public Racer(int num, String timeStr, long timeLong, String last, String first){
		this.number = num;
		this.raceTimeString = timeStr;
		this.raceTimeLong = timeLong;
		this.last = last;
		this.first = first;
	}
	
	/**
	 * Override toString.
	 *    
	 */
	public String toString(){
		//if racer did not finish race
		if (raceTimeLong == ((long) -1)) {
			return "Racer: " + number + "\nTime: Did not finish race.";
		}
		//Racer did not start
		if (raceTimeLong == ((long) -2)) {
			return "Racer: " + number + "\nTime: Did not start race.";
		}
		//Next three are for parallel race
		if(raceTimeLong == ((long) -3)){
			return "\n\n------------Second Track------------\n";
		}
		if(raceTimeLong == ((long) -4)){
			return "------------First Track--------------\n";
		}
		if(raceTimeLong == ((long) -5)){
			return "\n\n---------------DNS----------------\n";
		}
		
		//normal race finish
		return "Racer: " + number + "\nTime: " + raceTimeString;
		
		//possible case for if a racer did not start?
	}

	/**
	 * Compares two racer objects (this and other) to get 
	 * their ordering based on finish times.
	 * 
	 * @param o -- other racer object
	 * @return Negative if this goes first, 1 if this goes second
	 */
	public int compareTo(Racer o) {
		long otherTime = ((Racer) o).raceTimeLong;

		if(otherTime < 0){
			//other is DNF
			return -1;
		}
		else if(this.raceTimeLong < 0){
			//this is DNF
			return 1;
		}
		else{
			//regular times
			return (int)(this.raceTimeLong - otherTime);
		}
	}
}