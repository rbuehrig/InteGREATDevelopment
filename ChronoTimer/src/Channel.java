//////////////////////////////////////////
//InteGREAT Development
//Class: CS 361
//Authors: Phil
//
//
//Description: Channel object.
//
//////////////////////////////////////////
public class Channel {
	private boolean armed;
	private boolean type;
//	private Time time;
	
	
	/*Channel type relies on boolean 'type'
	 * true = 'start' channel
	 * false = 'finish' channel
	 */
	public Channel(boolean type, Time time){
		armed = false;
		this.type = type;
		//this.time = time;
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
	public long trigger(Time obj){
		if(armed = false){
			System.out.println("Channel is not active");
			return -1;
		}
		
		if(type == true){
			return obj.start();
		}
		else{
			return obj.finish();
		}
	}
	
	public long trigger(Time obj, long customTime){
		if(armed = false){
			System.out.println("Channel is not active");
			return -1;
		}
		
		if(type == true){
			return obj.start(customTime);
		}
		else{
			return obj.finish(customTime);
		}
	}
	
	public void setType(boolean type){
		this.type = type;
	}
	
	public boolean getType(){
		return this.type;
	}
	
//	public void setTime(Time time){
//		this.time = time;
//	}
//	
//	public Time getTime(){
//		return this.time;
//	}

}