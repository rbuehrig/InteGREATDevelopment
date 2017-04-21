import java.util.ArrayList;
import java.util.LinkedList;

//Class: CS 361
//Authors: Matthew and Rylie
//
//
//Description: Chronotimer serves as an 
//interface to the simulator.
//
//////////////////////////////////////////

public class Chronotimer {	
	//Keep track of multiple times in the case of parallel races
	private LinkedList <Time> times;

	//For interfacing with the "directory"
	private DirectoryProxy dp = new DirectoryProxy();

	//A queue to keep track of each racer's number
	//in order. Implemented as an ArrayList for now.
	private ArrayList<Integer> racerNums;

	//To be used to store racer data for printing or saving
	private ArrayList<Racer> racers;

	//Keeps track of channels we need to use in the race.
	private ArrayList<Channel> channels;
	private final int NUM_CHANNELS = 8;

	//Boolean to keep track of if the system has been turned on.
	protected boolean on;

	private boolean newRunCalled;

	//Keep track of if EVENT is called
	private boolean eventSet;

	//keep track of the current trigger number
	private int whichRacer;

	//keep track of which time object a start was just added to
	//Default object is zero (i.e. for IND races)
	private int timeObjNum = 0;

	private int raceNum = 1;


	/**
	 * Constructor
	 * 
	 * @author Philip Kocol
	 */
	public Chronotimer(){
		racerNums = new ArrayList<Integer>();
		channels = new ArrayList<Channel>();

		on = false;
		newRunCalled = false;
		eventSet = false;
		whichRacer = 0;
	}


	/**
	 * Initializes array list of channels for use
	 * Sets odd channels to "start"(true) and even to "finish"(false)
	 * For use by constructor and reset() only
	 * 
	 * @author Philip Kocol
	 */
	private void initChannels(int numChans){
		for(int i = 0; i < numChans; i+=2){
			times.add(new Time());
			channels.add(new Channel(true, times.peekLast()));
			channels.add(new Channel(false, times.peekLast()));
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
	public boolean powerToggle(){			
		if(on){on = !on;}
		
		else{reset(); on = !on;}
		//Allows for a newRun to be called.
		newRunCalled = false;
		eventSet = false;
		
		return on;
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
		times = new LinkedList<Time>();
		racerNums = new ArrayList<Integer>();

		//Add two new channels to array list
		channels = new ArrayList<Channel>(NUM_CHANNELS);
		whichRacer = 0;
	}


	/** 
	 * Adds the correct number of channels depending on 
	 * the race type.
	 * 
	 * @version 1 - 03/10/17
	 * @version 2 - 03/3/17
	 * @author Matthew Buchanan and Rylie Buehrig
	 */
	public void setEvent(String eventType){
		if (on){
			switch (eventType){
			case "IND":
				initChannels(2);
				eventSet = true;
				break;

			case "PARIND":
				initChannels(4);
				eventSet = true;
				break;
			}
		}
	}


	/** 
	 * Calls the Time class to set the time if commands are 
	 * input from a file.
	 * 
	 * @precondition setEvent must be called before setting time,
	 *	 so that there is at least one time created.
	 * @version 1 - 02/28/17
	 * @version 2 - 03/3/17
	 * @author Rylie Buehrig
	 */
	public void setTime(String timeStamp){
		long timeLong = times.get(0).parseMilli(timeStamp);

		//This could be wrong
		//Assumes both Time objects will get the same time for reference
		if (on && eventSet && (times.size() > 1)){
			times.get(0).start(timeLong);
			times.get(1).start(timeLong);
		}
		else if (on && eventSet && (times.size() == 1)){
			times.get(0).start(timeLong);
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
		if (on) trigger(1);
	}


	/** 
	 * This method calls the trigger method and 
	 * passes in 2 to represent channel 2.
	 * 
	 * @version 1 - 02/26/17
	 * @author Matthew Buchanan and Rylie Buehrig
	 */
	public void finish(){ 
		if (on) trigger(2);
	}


	/** 
	 * This method will create a new run by clearing all 
	 * data structures.
	 * 
	 * @version 1 - 02/27/17
	 * @author Rylie Buehrig
	 */
	public void newRun(){
		if (on) newRunCalled = true;
	}


	/** 
	 * This method will print the delta time of the 
	 * racers that finished. 
	 * 
	 * @precondition racerNums ArrayList must not be empty.
	 * @version 1 - 02/27/17
	 * @author Rylie Buehrig
	 */
	public void print(){
		if (on && eventSet && newRunCalled)	{
			createRacerQueue();
			dp.add(racers);
			//print will print results to console
			dp.print("console",raceNum);
		}
	}


	/** 
	 * This method will enable boolean so that NEWRUN command
	 * can be called. 
	 * 
	 * @version 1 - 02/27/17
	 * @author Rylie Buehrig
	 */
	public void endRun(){
		if (newRunCalled) {	
			createRacerQueue();
			dp.add(racers);	
			//end run saves run data to a file
			dp.print("file",raceNum);
			dp.clear();
			raceNum++;
			reset();
			newRunCalled = false;
		}
	}

	/**
	 * Helper method that initializes the queue of Racer objects 
	 * and adds Racer objects with numbers and times.
	 * 
	 */
	private void createRacerQueue(){
		if (on && eventSet && newRunCalled)	{
			racers = new ArrayList<Racer>(racerNums.size());
			long raceTime;						

			for (int i = 0; i < racerNums.size(); i++){				
				for (int j = i; j < racerNums.size(); j++){
					//look through the first time object for the racer number's time
					if ((times.size() >= 1) && (j < (times.get(0).racerNums.size()))){				
						if (times.get(0).racerNums.get(j) == racerNums.get(i)){
							raceTime = times.get(0).racerTimes.get(j);
							racers.add(new Racer(racerNums.get(i),parseTime(raceTime),raceTime));
							break;
						}
					}
					//look through the second time object for the racer number's time
					if ((times.size() >= 2) && (i < (times.get(1).racerNums.size()))){				
						if (times.get(1).racerNums.get(j) == racerNums.get(i)){
							raceTime = times.get(0).racerTimes.get(j);
							racers.add(new Racer(racerNums.get(i),parseTime(raceTime),raceTime));
							break;
						}
					}
					
					else {
						racers.add(new Racer(racerNums.get(i),"Did not start.",-2));
					}
					//TODO Expand in the future to accommodate more time objects
				}
			}
		}
	}



	/**
	 * Helper method takes the racer's total time, gets the hours, 
	 * minutes, seconds, and milliseconds from it and returns that 
	 * in a formatted string.
	 * 
	 * @param timeInMS A delta time (the racer's total time in milliseconds).
	 * @return
	 */
	private String parseTime(long timeInMS){
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
	 * This method will call the time DNF method to set 
	 * a flag of -1 for the next racer's end time.
	 * 
	 * @version 1 - 02/26/17
	 * @author Matthew Buchanan and Rylie Buehrig
	 */
	public void DNF(){
		//I just have a variable to keep track of the which time object was last
		//added to.
		if (on && eventSet && newRunCalled) times.get(timeObjNum).dnf();
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
			times.get(timeObjNum).cancel();
			times.get(0).racerNums.remove(times.get(0).racerNums.size() - 1);
			whichRacer--;
		}
	}


	/** 
	 * This method will call the channel's toggle method. 
	 * Arms or disarms the specified channel.
	 * 
	 * @version 1 - 02/26/17
	 * @author Matthew Buchanan and Rylie Buehrig
	 */
	public boolean toggle(int channelNum){
		if (on && eventSet && newRunCalled && (channelNum <= channels.size())) {
			channels.get(channelNum - 1).toggle();
			return true;
		}
		else return false;
	}

	//TODO check if the channel exists and is active

	//TODO make sure a finish time has a corresponding start time.
	//If you trigger an odd channel 


	/** 
	 * This method will call the channel's trigger method.
	 * Sets channel as either a "start" or "end" channel.
	 * 
	 * @param channelNum 
	 * @version 1 - 02/26/17
	 * @version 1 - 03/3/17
	 * @author Matthew Buchanan and Rylie Buehrig
	 */
	public void trigger(int channelNum){
		//Need to somehow denote which racer corresponds to which time object and in which order
		if (on && newRunCalled && eventSet){	
			//*somewhere we need to see if the triggered channel is odd or even 
			//*because start and stop methods right now trigger specifically channels
			//*1 and 2			
			if ((channels.size() >= 1) && channelNum == 1) {
				channels.get(channelNum - 1).trigger(times.get(0));
				if (whichRacer < racerNums.size()) {times.get(0).racerNums.add(racerNums.get(whichRacer));}
				whichRacer++;
				timeObjNum = 0;
			}
			if ((channels.size() >= 2) && channelNum == 2) {
				channels.get(channelNum - 1).trigger(times.get(0));
			}
			if ((channels.size() >= 3) && channelNum == 3) {
				channels.get(channelNum - 1).trigger(times.get(1));
				times.get(1).racerNums.add(racerNums.get(whichRacer));
				whichRacer++;
				timeObjNum = 1;
			}
			if ((channels.size() >= 4) && channelNum == 4) {
				channels.get(channelNum - 1).trigger(times.get(1));
			}	
		}
	}

	/**
	 * Repeat of trigger method for file I/O
	 * 
	 * @param channelNum
	 * @param customTime
	 */
	public void trigger(int channelNum, String customTime){
		//Avoids the issue of having more racer start times than racer numbers
		if (on && newRunCalled && eventSet) {
			long tempTime = times.get(0).parseMilli(customTime);

			if ((channels.size() >= 1) && channelNum == 1) {
				channels.get(channelNum - 1).trigger(times.get(0),tempTime);
				times.get(0).racerNums.add(racerNums.get(whichRacer));
				whichRacer++;
				timeObjNum = 0;
			}
			if ((channels.size() >= 2) && channelNum == 2) {
				channels.get(channelNum - 1).trigger(times.get(0),tempTime);
			}
			if ((channels.size() >= 3) && channelNum == 3) {
				channels.get(channelNum - 1).trigger(times.get(1),tempTime);
				times.get(1).racerNums.add(racerNums.get(whichRacer));
				whichRacer++;
				timeObjNum = 1;
			}
			if ((channels.size() >= 4) && channelNum == 4) {
				channels.get(channelNum - 1).trigger(times.get(1),tempTime);
			}
		}
	}
}