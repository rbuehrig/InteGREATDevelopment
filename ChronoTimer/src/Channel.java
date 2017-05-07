//////////////////////////////////////////
//InteGREAT Development
//Class: CS 361
//
//Description: Channel object.
//////////////////////////////////////////
public class Channel {
	private boolean armed;
	private boolean type;


	/**
	 * Channel type relies on boolean 'type.'
	 *    
	 * @param type -- true = 'start' channel and
	 * 		false = 'finish' channel
	 */
	public Channel(boolean type){
		armed = false;
		this.type = type;
	}

	/**
	 * Arms or disarms a channel.
	 *    
	 */
	public void toggle(){
		if(armed == true){
			armed = false;
		}
		else{
			armed = true;
		}
	}

	/**
	 * Only used if input is from GUI.
	 * 
	 * Represents a trigger of the channel. 
	 * 		For a start channel, creates and returns a new Start time within specified Time object
	 * 		For a finish channel, calculates and returns the time for the next finishing racer
	 * 
	 * @param obj -- can be a regular Time or GroupTime
	 * @param chan -- channel number being triggered
	 * @return Start time, finish time or -1 to signify channel not active.
	 */
	public long trigger(Time obj, int chan){
		if(armed == false){
			System.out.println("Channel is not active");
			return -1;
		}

		if(type == true){
			return obj.start();
		}
		else{
			if(obj instanceof GroupTime && chan > 0)
				return obj.finish(chan);
			else return obj.finish();
		}
	}

	/**
	 * Only used if input is from file.
	 * 
	 * Represents a trigger of the channel. 
	 * 		For a start channel, creates and returns a new Start time within specified Time object
	 * 		For a finish channel, calculates and returns the time for the next finishing racer
	 * 
	 * @param obj -- can be a regular Time or GroupTime
	 * @param chan -- channel number being triggered
	 * @param customTime -- the time stamp sent in from file
	 * @return Start time, finish time or -1 to signify channel not active
	 */
	public long trigger(Time obj, int chan, long customTime){
		if(armed == false){
			System.out.println("Channel is not active");
			return -1;
		}

		if(type == true){
			return obj.start(customTime);
		}
		else{
			if(obj instanceof GroupTime && chan > 0)
				return obj.finish(chan,customTime);
			else return obj.finish(customTime);
		}
	}

	/**
	 * Set channel type (start or finish)
	 * 
	 * @param type -- start or finish
	 */
	public void setType(boolean type){
		this.type = type;
	}

	/**
	 * Get channel type
	 * 
	 * @return start = true, finish = false
	 */
	public boolean getType(){
		return this.type;
	}

	/**
	 * Get channel state
	 * 
	 * @return armed = true, disarmed = false
	 */
	public boolean isEnabled(){
		return this.armed;
	}
}