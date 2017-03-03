import java.util.ArrayList;
import java.util.LinkedList;

//////////////////////////////////////////
//InteGREAT Development
//Class: CS 361
//Authors: Matthew and Rylie
//
//
//Description: Chronotimer serves as an 
//interface to the simulator.
//
//////////////////////////////////////////

public class Chronotimer {
	//This time variable will serve as the keeper of 
	//racer's start and finish times for reference.
	private Time time;
	
	//A queue to keep track of each racer's number
	//in order. Implemented as an ArrayList for now.
	ArrayList<Integer> racerNums;
	
	//Keeps track of channels we need to use in the race.
	ArrayList<Channel> channels;
	private final int NUM_CHANNELS = 8;
	
	//Boolean to keep track of if the system has been turned on.
	protected boolean on;
	
	private boolean newRunCalled;
	//Keep track of EVENT is called
	private boolean eventSet;
	
	//keep track of how many times toggle is called
	private int toggleCalled;
	private int triggerCalled;
	
	
	/**
	 * Constructor
	 * 
	 * @author Philip Kocol
	 */
	public Chronotimer(){
		racerNums = new ArrayList<Integer>();
		channels = new ArrayList<Channel>(NUM_CHANNELS);
		
		on = false;
		newRunCalled = false;
		eventSet = false;
		triggerCalled = 0;
		toggleCalled = 0;	
	}
	
	/**
	 * Initializes array list of channels for use
	 * Sets odd channels to "start"(true) and even to "finish"(false)
	 * For use by constructor and reset() only
	 * 
	 * @author Philip Kocol
	 */
	private void initChannels(){
		for(int i = 0; i <= NUM_CHANNELS; i++){
			if(i%2 == 0){
				channels.add(new Channel(true, time));
			}
			else{
				channels.add(new Channel(false, time));
			}
		}
	}
	
	
	/** 
	* This method calls reset and changes the on boolean
	* attribute to its opposite state to simulate turning
	* off or on.
	* 
	* @version 1 - 02/26/17
	* @author Matthew Buchanan and Rylie Buehrig
	*/
	public void powerToggle(){			
		if(on){
			on = !on;
		}
		else{
			reset();
			on = !on;
		}
		//Allows for a newRun to be called.
		newRunCalled = false;
		eventSet = false;
		triggerCalled = 0;
		toggleCalled = 0;
	}
	
	/** 
	* This method initializes the Time and ArrayList
	* attributes. In some instances, this will clear
	* out the existing data.
	* 
	* @version 1 - 02/26/17
	* @author Matthew Buchanan and Rylie Buehrig
	*/
	public void reset(){
		time = new Time();
		racerNums = new ArrayList<Integer>();
		
		//Add two new channels to array list
		channels = new ArrayList<Channel>(NUM_CHANNELS);
		initChannels();
	}
	
	
	/** 
	* Adds the total amount of channels to the class 
	* 
	* @version 1 - 02/28/17
	* @author Matthew Buchanan and Rylie Buehrig
	*/
	public void setEvent(String eventType){
		if (on){
			switch (eventType){
				case "IND":
					channels.get(0).setType(true);
					channels.get(1).setType(false);;
					eventSet = true;
					break;
			}
		}
	}
	
	
	/** 
	* Calls the Time class to set the time if commands are 
	* input from a file.
	* 
	* @version 1 - 02/28/17
	* @author Rylie Buehrig
	*/
	public void setTime(String timeStamp){
		if (on){
			long timeLong = time.parseMilli(timeStamp);
			time.start(timeLong);
		}
	}


	/** 
	* This method will set the number of a racer. 
	* Every racer should have their own number.
	* 
	* @param racerNum An identifying number for a racer.
	* @version 1 - 02/27/17
	* @author Rylie Buehrig
	*/
	public void setNum(int racerNum){
		if (on && newRunCalled && eventSet){
			racerNums.add(racerNum);
		}
	}
	
	
	/** 
	* This method calls the trigger method and 
	* passes in 1 to represent channel 1.
	* 
	* @version 1 - 02/26/17
	* @author Matthew Buchanan and Rylie Buehrig
	*/
	public void start(){
		if(on) trigger(1);
	}
	
	
	/** 
	* This method calls the trigger method and 
	* passes in 2 to represent channel 2.
	* 
	* @version 1 - 02/26/17
	* @author Matthew Buchanan and Rylie Buehrig
	*/
	public void finish(){ 
		if(on) trigger(2);
	}

	
	/** 
	* This method will print the delta time of the 
	* racers that finished. 
	* 
	* @precondition racerNums ArrayList must not be empty.
	* @note Method may be revised to be more efficient
	*       for higher number of racers.
	* @version 1 - 02/27/17
	* @author Rylie Buehrig
	*/
	public void print(){
		if (on && eventSet && newRunCalled)	{
			LinkedList<Long> racerTimes = time.getTimes();
			//int totalNumRacers = racerTimes.size();
		
			for (int i = 0; i < racerNums.size(); i++){
				//Make sure DNF flag was not set
				if (i < racerTimes.size()) {
					if (racerTimes.get(i) != ((long) -1)){
						System.out.println("Racer: " + racerNums.get(i));
						System.out.println("Total Time: " + parseTime(racerTimes.get(i)) + "\n");
					}
					else{
						System.out.println("Racer: " + racerNums.get(i));
						System.out.println("Did not finish the race.\n");
					}
				}
				else {
					System.out.println("Racer: " + racerNums.get(i));
					System.out.println("Did not start the race.\n");
				}
			}
		}
	}
	
	
	/**
	 * Method takes the racer's total time, gets the hours, minutes, seconds, 
	 * and milliseconds from it and returns that in a formatted string.
	 * 
	 * @param timeInMS A delta time (the racer's total time in milliseconds).
	 * @return
	 */
	public String parseTime(long timeInMS){
		long currentTime = timeInMS;
		
		int hour = (int) (currentTime / (60*60*1000));
		currentTime = currentTime - hour*(60*60*1000);
		int minute = (int) (currentTime / (60*1000));
		currentTime = currentTime - minute*(60*1000);
		int second = (int) (currentTime / 1000);
		currentTime = currentTime - second*(1000);
		
		return (hour + ":" + minute + ":" + second + "." + currentTime);
	}
		
	/** 
	* This method will create a new run by clearing all 
	* data structures.
	* 
	* @version 1 - 02/27/17
	* @author Rylie Buehrig
	*/
	public void newRun(){
		if (on) {
			reset();
			newRunCalled = true;
		}
	}
	
	
	//Do we need to store previous run data somewhere? 
	//Or will simulator class do this?
	/** 
	* This method will enable boolean so that NEWRUN command
	* can be called 
	* 
	* @version 1 - 02/27/17
	* @author Rylie Buehrig
	*/
	public void endRun(){
		if (newRunCalled) newRunCalled = false;
		//save current data --> Sprint 2
	}
	
	
	/** 
	* This method will call the time DNF method to set 
	* a flag of -1 for the next racer's end time.
	* 
	* @version 1 - 02/26/17
	* @author Matthew Buchanan and Rylie Buehrig
	*/
	public void DNF(){
		if (on && eventSet && newRunCalled) time.dnf();
	}

	
	/** 
	* This method will call the time cancel method
	* to remove the last start time in the queue.
	* 
	* @version 1 - 02/26/17
	* @author Matthew Buchanan and Rylie Buehrig
	*/
	public void cancel(){
		if (on) {
			time.cancel();
		}
	}

	
	/** 
	* This method will call the channel's toggle method. 
	* Arms or disarms the specified channel.
	* 
	* @version 1 - 02/26/17
	* @author Matthew Buchanan and Rylie Buehrig
	*/
	public void toggle(int channelNum){
		if (on && eventSet && newRunCalled) {
			channels.get(channelNum - 1).toggle();
			toggleCalled++;
		}
	}
	

	/** 
	* This method will call the channel's trigger method.
	* Sets channel as either a "start" or "end" channel.
	* 
	* @version 1 - 02/26/17
	* @author Matthew Buchanan and Rylie Buehrig
	*/
	public void trigger(int channelNum){
		if (on && newRunCalled && eventSet && (time.getTimes().size() <= racerNums.size())) {
			channels.get(channelNum - 1).trigger();
		}
	}
	
	public void trigger(int channelNum, String customTime){
		if (on && newRunCalled && eventSet && (time.getTimes().size() <= racerNums.size())) {
			channels.get(channelNum - 1).trigger(time.parseMilli(customTime));
		}
	}
}
