//PHIL CHANGE 5/3/17
public class Racer implements Comparable<Object>{
	public int number;
	//PHIL CHANGE 5/3/2017
	public String last;
	public String first;
	
	public String raceTimeString;
	public long  raceTimeLong;
	
	public Racer(int num, String timeStr, long timeLong){
		this.number = num; 
		this.raceTimeString = timeStr;
		this.raceTimeLong = timeLong;
		//PHIL CHANGE 5/3/17
		this.last = "";
		this.first = "";
	}
	
	//PHIL CHANGE 5/3/17
	public Racer(int num, String timeStr, long timeLong, String last, String first){
		this.number = num; 
		this.raceTimeString = timeStr;
		this.raceTimeLong = timeLong;
		this.last = last;
		this.first = first;
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
			return "\n\n---------Second Track-----------\n\n";
		}
		
		if(raceTimeLong == ((long) -4)){
			return "---------First Track-----------\n";
		}
		
		//normal race finish
		return "Racer: " + number + "\nTime: " + raceTimeString;
		
		//possible case for if a racer did not start?
	}
	
	//PHIL CHANGE 5/3/17
	@Override
	public int compareTo(Object o) {
		if (o instanceof Racer) {
			Racer other = (Racer) o;
			if (other.raceTimeLong < 0){
				return 1;
			}
			else if (this.raceTimeLong < 0){
				return -1;
			}
			else {
				long timeThis;
				long otherTime;
				try{
					timeThis = this.raceTimeLong;
					otherTime = other.raceTimeLong;
				}
				catch(NumberFormatException e){
					timeThis = 0;
					otherTime = 0;
				}

				if (timeThis > otherTime){
					return 1;
				}
				else if (timeThis < otherTime){
					return -1;
				}
				else {
					return 0;
				}
			}
		}
		return 0;
	}
	
	
}