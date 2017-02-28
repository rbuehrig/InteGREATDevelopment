
public class Channel {
	private boolean armed;
	private boolean type;
	private Time time;
	
	
	/*Channel type relies on boolean 'type'
	 * true = 'start' channel
	 * false = 'finish' channel
	 */
	public Channel(boolean type, Time time){
		armed = false;
		this.type = type;
		this.time = time;
	}
	
	public void toggle(){
		if(armed == true){
			armed = false;
		}
		else{
			armed = true;
		}
	}
	
	/*Represents a trigger of the channel
	 * If it's a start channel, creates and returns a new Start time within specified Time object
	 * If it's a finish channel, calculates and returns the time for the next finishing racer
	 */
	public long trigger(){
		if(type == true){
			return time.start();
		}
		else{
			return time.finish();
		}
	}
	
	public void setType(boolean type){
		this.type = type;
	}
	
	public boolean getType(){
		return this.type;
	}
	
	public void setTime(Time time){
		this.time = time;
	}
	
	public Time getTime(){
		return this.time;
	}

}
