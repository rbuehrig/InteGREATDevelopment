import java.util.ArrayList;
import java.util.LinkedList;

import javafx.event.EventType;

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
	protected LinkedList <Time> times;

	//For interfacing with the "directory"
	private DirectoryProxy dp;

	//A queue to keep track of each racer's number
	//in order. Implemented as an ArrayList for now.
	protected ArrayList<Integer> racerNums;

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
	
	private EventType eventType;

	//keep track of the current trigger number
	private int whichRacer;

	//keep track of which time object a start was just added to
	//Default object is zero (i.e. for IND races)
	private int timeObjNum;
	
	//Keep track of times a start trigger has been called
	//private int timesTriggered = 0;

	private int raceNum;

	private int timesTriggered;//this is tp prevent from clicking trigger more times then you should be able to

	
	/**
	 * Constructor
	 * 
	 * @author Philip Kocol
	 */
	public Chronotimer(){
		racerNums = new ArrayList<Integer>();
		channels = new ArrayList<Channel>();
		racers = new ArrayList<Racer>();
		times = new LinkedList<Time>();
		dp = new DirectoryProxy();

		on = false;
		newRunCalled = false;
		eventSet = false;
		eventType = EventType.UNSET;
		timeObjNum = 0;
		raceNum = 1;
		whichRacer = 0;
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
		on = !on;
		reset();
		
		//Allows for a newRun to be called.
		newRunCalled = false;
		eventSet = false;
		eventType = EventType.UNSET;
		
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
	 * Initializes array list of channels for use
	 * Sets odd channels to "start"(true) and even to "finish"(false)
	 * For use by constructor and reset() only
	 * 
	 * @author Philip Kocol
	 */
	private void initChannels(int numChans){
		for(int i = 0; i < numChans; i+=2){
			if(this.eventType == EventType.GRP){
				times.add(new GroupTime());
				channels.add(new Channel(true, times.peekLast()));
				channels.add(new Channel(false, times.peekLast()));
			}
			else{
				times.add(new Time());
				channels.add(new Channel(true, times.peekLast()));
				channels.add(new Channel(false, times.peekLast()));
			}
		}
	}


	/** 
	 * Adds the correct number of channels depending on 
	 * the race type.
	 * 
	 * @version 1 - 03/10/17
	 * @version 2 - 03/3/17
	 * @author Matthew Buchanan and Rylie Buehrig
	 */
	public void setEvent(String eventType){//////////////////////////////////matt change
		if (on){
			switch (eventType){
			case "IND":
				this.eventType = EventType.IND;
				initChannels(2);
				eventSet = true;
				break;

			case "PARIND":
				this.eventType = EventType.PARIND;
				initChannels(4);
				eventSet = true;
				break;
			
			case "GRP":
				this.eventType = EventType.GRP;
				initChannels(2);
				eventSet = true;
				break;
			case "PARGRP":
				this.eventType = EventType.PARGRP;
				initChannels(8);
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
	public void setTime(String timeStamp){///////////////////////////////////////matt change
		long timeLong = times.get(0).parseMilli(timeStamp);

		//This could be wrong
		//Assumes both Time objects will get the same time for reference
//		if (on && eventSet && (times.size() > 1)){
//			times.get(0).start(timeLong);
//			times.get(1).start(timeLong);
//		}
//		else if (on && eventSet && (times.size() == 1)){
//			times.get(0).start(timeLong);
//		}
		if (on){
			switch(eventType){
				case IND:
					times.get(0).start(timeLong);
					break;
				
				case PARIND:
					times.get(0).start(timeLong);
					times.get(1).start(timeLong);
					break;
					
				case GRP:
					times.get(0).start(timeLong);
					break;
					
				case PARGRP:
					times.get(0).start(timeLong);
					
				case UNSET:
					//DO Nothing
					break;
			}
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
	 * This Method swaps the next two racers to finish
	 * 
	 * @version 1 - 02/26/17
	 * @author Matthew Buchanan and Rylie Buehrig
	 */
	public void swap(){
		if (eventType == EventType.IND && times.get(0).startTimes.size() >= 2) {
			times.get(0).swap();

			int tempRNFirst = racerNums.get(0+times.get(0).finishTimes.size());
			int tempRNSecond = racerNums.get(1+times.get(0).finishTimes.size());
			racerNums.set(0+times.get(0).finishTimes.size(), tempRNSecond);
			racerNums.set(1+times.get(0).finishTimes.size(), tempRNFirst);

			whichRacer = tempRNFirst;
		}
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
	public long print(){
		if (on && eventSet && newRunCalled && times.getFirst().finishTimes.size() > 0) {	
			createRacerQueue();
			dp.add(racers);
			//print will print results to console
			dp.print("console",raceNum);
			return 0;
		}
		else return -1;
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
	private void createRacerQueue(){/////////////////////////////matt change
		if (on && eventSet && newRunCalled)	{
			racers = new ArrayList<Racer>(racerNums.size());
			int finishT = times.get(0).finishTimes.size();
			int startT = times.get(0).startTimes.size();
			
			switch (this.eventType){
			case IND:
				//MATT START CHANGE 5/1
				for(int i = 0; i < racerNums.size(); i++){
					if(finishT > i)
						racers.add(new Racer(racerNums.get(i), parseTime(times.get(0).finishTimes.get(i)), times.get(0).finishTimes.get(i)));
					else if(finishT <= i && startT + finishT > i)
						racers.add(new Racer(racerNums.get(i), "Did not Finish!", -1));
					else 
						racers.add(new Racer(racerNums.get(i), "Did not Start!", -2));
					//MATT END CHANGE 5/1
				}
				break;
			case PARIND:
				for(int i = 0; i < times.get(0).racerNums.size(); i++){
					if(finishT > i)
						racers.add(new Racer(times.get(0).racerNums.get(i), parseTime(times.get(0).finishTimes.get(i)), times.get(0).finishTimes.get(i)));
					else if(finishT < i && startT + finishT >= i)
						racers.add(new Racer(times.get(0).racerNums.get(i), "Did not Finish!", -1));
					else 
						racers.add(new Racer(times.get(0).racerNums.get(i), "Did not Start!", -2));
				}
				
				racers.add(new Racer(-1, "Racer indicating the end of the first track, and the start of the second track", -3));//maybe change the race class to handle this case, and we can format the print format better
				//I changed racer to do this
				for(int i = 0; i < times.get(1).racerNums.size(); i++){
					if(finishT > i)
						racers.add(new Racer(times.get(1).racerNums.get(i), parseTime(times.get(1).finishTimes.get(i)), times.get(1).finishTimes.get(i)));
					else if(finishT < i && startT + finishT >= i)
						racers.add(new Racer(times.get(1).racerNums.get(i), "Did not Finish!", -1));
					else 
						racers.add(new Racer(times.get(1).racerNums.get(i), "Did not Start!", -2));
				}
				break;
			default://GRP and PARGRP do the same thing? Just one time object?
				for(int i = 0; i < times.get(0).racerNums.size(); i++){
					if(finishT > i)
						racers.add(new Racer(i+1, parseTime(times.get(0).finishTimes.get(i)), times.get(0).finishTimes.get(i)));
					else if(finishT < i && startT + finishT >= i)
						racers.add(new Racer(i+1, "Did not Finish!", -1));
					else 
						racers.add(new Racer(i+1, "Did not Start!", -2));
				}
				break;

				
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


	/** 
	 * This method will call the channel's trigger method.
	 * Sets channel as either a "start" or "end" channel.
	 * 
	 * @param channelNum 
	 * @version 1 - 02/26/17
	 * @version 1 - 03/3/17
	 * @author Matthew Buchanan and Rylie Buehrig
	 */
	public long trigger(int channelNum){
		//Need to somehow denote which racer corresponds to which time object and in which order
		//MATT CHANGE 5/1
		boolean canStillTrigger = true;
		long temp = 0;
		
		if(timesTriggered >= racerNums.size()*2) canStillTrigger = false;//if the amount of start and finish buttons have been pressed, then trigger does nothing
		
		if (on && eventSet && newRunCalled && canStillTrigger){	

			
			if ((channels.size() >= 1) && channelNum == 1) {
				channels.get(channelNum - 1).trigger(times.get(0));
				//if (whichRacer < racerNums.size()) { line 435}
				times.get(0).racerNums.add(racerNums.get(whichRacer));
				whichRacer++; timesTriggered++;
				timeObjNum = 0;
			}
			if ((channels.size() >= 2) && channelNum == 2) {
				channels.get(channelNum - 1).trigger(times.get(0));
				timesTriggered++;
			}
			if ((channels.size() >= 3) && channelNum == 3) {
				channels.get(channelNum - 1).trigger(times.get(1));
				times.get(1).racerNums.add(racerNums.get(whichRacer));
				whichRacer++; timesTriggered++;
				timeObjNum = 1;
			}
			if ((channels.size() >= 4) && channelNum == 4) {
				channels.get(channelNum - 1).trigger(times.get(1));
				timesTriggered++;
			}	
			//MATT CHANGE 5/1
		}
		return temp;
	}

	/**
	 * Repeat of trigger method for file I/O
	 * 
	 * @param channelNum
	 * @param customTime
	 */
	public long trigger(int channelNum, String customTime){
		//Avoids the issue of having more racer start times than racer numbers
		long temp = 0;
		boolean checkTimesTriggered = true;
		
		if (on && eventSet && newRunCalled) {
			long tempTime = times.get(0).parseMilli(customTime);
			
//			if (timesTriggered >= racerNums.size()) {
//				checkTimesTriggered = false;
//				temp = -1;
//			}

			if ((channels.size() >= 1) && channelNum == 1 && checkTimesTriggered) {
				channels.get(channelNum - 1).trigger(times.get(0),tempTime);
				times.get(0).racerNums.add(racerNums.get(whichRacer));
				whichRacer++; //timesTriggered++;
				timeObjNum = 0;
			}
			if ((channels.size() >= 2) && channelNum == 2) {
				channels.get(channelNum - 1).trigger(times.get(0),tempTime);
			}
			if ((channels.size() >= 3) && channelNum == 3 && checkTimesTriggered) {
				channels.get(channelNum - 1).trigger(times.get(1),tempTime);
				times.get(1).racerNums.add(racerNums.get(whichRacer));
				whichRacer++; //timesTriggered++;
				timeObjNum = 1;
			}
			if ((channels.size() >= 4) && channelNum == 4) {
				channels.get(channelNum - 1).trigger(times.get(1),tempTime);
			}
		}
		return temp;
	}
	
	//Private Helpers
	private enum EventType {//////////////////////////////////matt change
		UNSET,
		PARGRP,
		IND,
		PARIND,
		GRP
	}
	
	private boolean OnWithEventSet() {
		return on && eventType != EventType.UNSET;
	}
}