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
		return "Racer: " + number + "";
	}
}