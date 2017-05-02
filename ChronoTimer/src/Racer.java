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
		
		if (raceTimeLong == ((long) -2)) {
			return "Racer: " + number + "\nTime: Did not start race.";
		}
		
		if(raceTimeLong == ((long) -3)){
			return "\n\n---------Different courses-----------\n\n";
		}
		
		//normal race finish
		return "Racer: " + number + "\nTime: " + raceTimeString;
		
		//possible case for if a racer did not start?
	}
	
	
}