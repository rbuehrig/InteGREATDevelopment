public class Racer {
	protected int number;
	protected String raceTimeString;
	protected long  raceTimeLong;
	
	public Racer(int num, String timeStr, long timeLong){
		this.number = num; 
		this.raceTimeString = timeStr;
		this.raceTimeLong = timeLong;
	}
	
	public String toString(){
		//if racer did not finish race
		if (raceTimeLong == ((long) -1)) {
			return "Racer: " + number + "\nTime: Did not finish race.";
		}
		
		//normal race finish
		return "Racer: " + number + "\nTime: " + raceTimeString;
		
		//possible case for if a racer did not start?
	}
}