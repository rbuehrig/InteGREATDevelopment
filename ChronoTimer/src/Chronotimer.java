import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;

import com.google.gson.Gson;
//////////////////////////////////////////
//InteGREAT Development
//Class: CS 361
//
//Description: Chronotimer serves as an 
//interface to the simulator.
//////////////////////////////////////////
public class Chronotimer {	
	//Keep track of multiple times in the case of parallel races
	protected LinkedList <Time> times;

	//For interfacing with the "directory"
	private DirectoryProxy dp;

	//A queue to keep track of each racer's number
	//in order. Implemented as an ArrayList.
	protected ArrayList<Integer> racerNums;

	//To be used to store racer data for printing or saving
	private ArrayList<Racer> racers;

	//Keeps track of channels we need to use in the race.
	private ArrayList<Channel> channels;

	//Max number of channels
	private final int NUM_CHANNELS = 8;

	//Boolean to keep track of if the system has been turned on.
	protected boolean on;

	//Keeps track of when NEWRUN is called
	private boolean newRunCalled;

	//Keep track of if EVENT is called
	private boolean eventSet;

	//Keeps track of the current event type
	protected EventType eventType;

	//EventType enum
	public enum EventType {
		UNSET, PARGRP, IND, PARIND, GRP
	}

	//keep track of the current trigger number
	private int whichRacer;

	//keep track of which time object a start was just added to
	//Default object is zero (i.e. for IND races)
	private int timeObjNum;

	//Keeps track of the current race number
	private int raceNum;

	//Used to prevent trigger from being called more times than what should be
	private int timesTriggered;

	//Keeps track of which channels have been triggered
	boolean[] beenTriggered = new boolean[8];

	/**
	 * Constructor initializes are variables
	 * 
	 * @author Philip Kocol
	 */
	public Chronotimer(){
		racerNums = new ArrayList<Integer>();
		channels = new ArrayList<Channel>();
		racers = new ArrayList<Racer>();
		times = new LinkedList<Time>();
		dp = new DirectoryProxy();
		timesTriggered = 0;
		beenTriggered = new boolean[8];//hopefully all defaulted to false;

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
	 * Reset data in arrays and linked lists, and return 
	 * variables to 0.
	 * 
	 * @author Matthew Buchanan and Rylie Buehrig
	 */
	public void reset(){
		times = new LinkedList<Time>();
		racerNums = new ArrayList<Integer>();
		channels = new ArrayList<Channel>(NUM_CHANNELS);
		whichRacer = 0;
		timeObjNum = 0;
		timesTriggered = 0;
		beenTriggered = new boolean[8];//hopefully all defaulted to false;
	}

	/**
	 * Initializes array list of channels--the number of which depends
	 * on the race type. Sets odd channels to "start"(true) and even to 
	 * "finish"(false).
	 * 
	 *  Internal use only.
	 * 
	 * @author Philip Kocol
	 */
	private void initChannels(){
		switch(this.eventType){
		case IND:
			times.add(new Time());
			channels.add(new Channel(true));
			channels.add(new Channel(false));
			break;
		case PARIND:
			times.add(new Time());
			times.add(new Time());
			channels.add(new Channel(true));
			channels.add(new Channel(false));
			channels.add(new Channel(true));
			channels.add(new Channel(false));
			break;
		case GRP:
			times.add(new GroupTime());
			channels.add(new Channel(true));
			channels.add(new Channel(false));
			break;
		case PARGRP:
			times.add(new GroupTime());
			for(int i = 0; i < 8; i++){
				times.get(0).finishTimes.add((long)-1);
			}
			channels.add(new Channel(true));
			channels.add(new Channel(false));
			channels.add(new Channel(false));
			channels.add(new Channel(false));
			channels.add(new Channel(false));
			channels.add(new Channel(false));
			channels.add(new Channel(false));
			channels.add(new Channel(false));
			break;
		default:
			break;
		}
	}

	/** 
	 * Sets the event type and calls helper method to 
	 * initialize the correct number of channels.
	 * 
	 * @author Matthew Buchanan and Rylie Buehrig
	 */
	public void setEvent(String eventType){
		if (on){
			switch (eventType){
			case "IND":
				this.eventType = EventType.IND;
				break;
			case "PARIND":
				this.eventType = EventType.PARIND;
				break;
			case "GRP":
				this.eventType = EventType.GRP;
				break;
			case "PARGRP":
				this.eventType = EventType.PARGRP;
				break;
			}
			initChannels();
			eventSet = true;
		}
	}

	/** 
	 * This method is for file input only. 
	 * 
	 * Calls the Time class to set the time of one or both
	 * time objects depending on the event type.
	 * 
	 * @precondition setEvent must be called before setting time,
	 *	 so that there is at least one time created.
	 */
	public void setTime(String timeStamp){
		long timeLong = times.get(0).parseMilli(timeStamp);

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
				break;
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
	 * @param racerNum -- An identifying number for a racer.
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
	 */
	public void start(){
		if (on) trigger(1);
	}

	/** 
	 * This Method swaps the next two racers to finish.
	 * 
	 */
	public void swap(){
		if (eventType == EventType.IND && times.get(0).startTimes.size() >= 2) {
			times.get(0).swap();

			int tempRNFirst = racerNums.get(0+times.get(0).finishTimes.size());
			int tempRNSecond = racerNums.get(1+times.get(0).finishTimes.size());
			racerNums.set(0+times.get(0).finishTimes.size(), tempRNSecond);
			racerNums.set(1+times.get(0).finishTimes.size(), tempRNFirst);

			//whichRacer = tempRNFirst;
			whichRacer = times.get(0).finishTimes.size();
		}
	}

	/** 
	 * This method calls the trigger method and 
	 * passes in 2 to represent channel 2.
	 * 
	 */
	public void finish(){ 
		if (on) trigger(2);
	}


	/** 
	 * This method will set newRun to true so that the
	 * race may occur. 
	 * 
	 */
	public void newRun(){
		if (on) newRunCalled = true;
		timesTriggered = 0;
	}


	/** 
	 * This method will output a window with the finish time of all 
	 * racers, or a special message if a racer did not start or finish.
	 * 
	 * @precondition racerNums ArrayList must not be empty.
	 */
	public long print(){
		if (on && eventSet && newRunCalled && (times.getFirst().startTimes.size() != 0 || times.getFirst().finishTimes.size() > 0)){
			//Setup the queue of racers
			createRacerQueue();
			//Add the queue of racers to our directory proxy
			dp.add(racers);
			//Sprint 4 -- console was changed to a new window
			dp.print("console",raceNum);
			return 0;
		}
		else return -1;
	}


	/** 
	 * This method will output a file with the finish time of all
	 * racers, or a special message if a racer did not start or finish.
	 * 
	 */
	public void endRun(){
		if (newRunCalled) {	
			//Setup the queue of racers
			createRacerQueue();
			//Attempt server connection
			try {connect(racers);} 
			catch (Exception e1) {e1.printStackTrace();}			
			//Add racer queue to directory proxy
			dp.add(racers);	
			//Saves run data to a file
			dp.print("file",raceNum);
			dp.clear();
			raceNum++;
			//Reset all data for a new race
			reset();
			//NewRun must be called to start a new race
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
			int finishT = times.get(0).finishTimes.size();
			int startT = times.get(0).startTimes.size();

			//The racers and times are read differently depending on the race type. 
			switch (this.eventType){
			case IND:
				for(int i = 0; i < racerNums.size(); i++){
					if(finishT > i)
						racers.add(new Racer(racerNums.get(i), parseTime(times.get(0).finishTimes.get(i)), times.get(0).finishTimes.get(i)));
					else if(finishT <= i && startT + finishT > i)
						racers.add(new Racer(racerNums.get(i), "Did not Finish!", -1));
					else 
						racers.add(new Racer(racerNums.get(i), "Did not Start!", -2));
				}
				break;
			case PARIND:
				int finishTwo = times.get(1).finishTimes.size();
				int startTwo = times.get(1).startTimes.size();

				racers.add(new Racer(-1, "first track", -4));
				for(int i = 0; i < times.get(0).racerNums.size(); i++){
					if(finishT > i)
						racers.add(new Racer(times.get(0).racerNums.get(i), parseTime(times.get(0).finishTimes.get(i)), times.get(0).finishTimes.get(i)));
					else if(finishT <= i && startT + finishT > i)
						racers.add(new Racer(times.get(0).racerNums.get(i), "Did not Finish!", -1));
				}

				racers.add(new Racer(-1, "Racer indicating the end of the first track, and the start of the second track", -3));
				for(int i = 0; i < times.get(1).racerNums.size(); i++){
					if(finishTwo > i)
						racers.add(new Racer(times.get(1).racerNums.get(i), parseTime(times.get(1).finishTimes.get(i)), times.get(1).finishTimes.get(i)));
					else if(finishTwo <= i && startTwo + finishTwo > i)
						racers.add(new Racer(times.get(1).racerNums.get(i), "Did not Finish!", -1));
				}

				if ((startT + startTwo + finishTwo + finishT) != racerNums.size()){
					racers.add(new Racer(-1, "didnt start", -5));
					for(int i = startT + startTwo + finishTwo + finishT; i < racerNums.size(); i++){
						racers.add(new Racer(racerNums.get(i), "Did not Start!", -2));
					}
				}
				break;
case GRP:
	for(int i = 0; i < racerNums.size(); i++){
		if(finishT > i)
			racers.add(new Racer(racerNums.get(i), parseTime(times.get(0).finishTimes.get(i)), times.get(0).finishTimes.get(i)));
		else 
			racers.add(new Racer(racerNums.get(i), "Did not Finish!", -1));

	}
	break;
case PARGRP:
	for(int i = 0; i < racerNums.size() && i < 8; i++){
		if(times.get(0).finishTimes.get(i) != (long)-1){
			racers.add(new Racer(racerNums.get(i), parseTime(times.get(0).finishTimes.get(i)), times.get(0).finishTimes.get(i) ));
		} else
			racers.add(new Racer(racerNums.get(i),  "Did not Finish!", -1));
	}
	break;
default:
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
	 * @return Formatted string with the hour, minute, second, and millisecond
	 * 		equivalent of the input.
	 */
	public String parseTime(long timeInMS){
		long currentTime = timeInMS;

		int hour = (int) (currentTime / (60*60*1000));
		currentTime = currentTime - hour*(60*60*1000);
		int minute = (int) (currentTime / (60*1000));
		currentTime = currentTime - minute*(60*1000);
		int second = (int) (currentTime / 1000);
		currentTime = currentTime - second*(1000);

		return (String.format("%01d",hour) + ":" + String.format("%01d",minute) + ":" + String.format("%02d",second) + "." + String.format("%03d",currentTime));
	}

	/** 
	 * This method will call the time DNF method to set 
	 * a flag of -1 for the next racer's end time.
	 * 
	 */
	public void DNF(){
		if (on && eventSet && newRunCalled) times.get(timeObjNum).dnf();
	}

	/** 
	 * This method will call the time cancel method
	 * to remove the last start time in the queue.
	 * 
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
	 */
	public boolean toggle(int channelNum){
		if (on && eventSet && newRunCalled && (channelNum <= channels.size())) {
			channels.get(channelNum - 1).toggle();
			return true;
		}
		else return false;
	}

	/** 
	 * For GUI input only.
	 * 
	 * This method will call the channel's trigger method.
	 * Sets channel as either a "start" or "end" channel.
	 * 
	 * @param channelNum -- channel number being triggered
	 */
	public long trigger(int channelNum){
		boolean canStillTrigger = true;
		long temp = 0;

		if (on && eventSet && newRunCalled && channelNum <= channels.size() && channels.get(channelNum - 1).isEnabled()){
			//do we need to check the channel sizes, if we know the event type has been set? (channels.size() >= 1) && 

			//Trigger is different depending on the race type and channel triggered
			switch(this.eventType){
			case IND:
				if (channelNum == 1 && (times.get(0).startTimes.size() + times.get(0).finishTimes.size()) != racerNums.size()) {
					channels.get(channelNum - 1).trigger(times.get(0), 1);
					times.get(0).racerNums.add(racerNums.get(whichRacer));
					whichRacer++; timesTriggered++; 
					timeObjNum = 0;
				}
				else if (channelNum == 2 && times.get(0).startTimes.size() != 0) {//there have to be people currently racing
					channels.get(channelNum - 1).trigger(times.get(0), 2);
					timesTriggered++; 
				}
				break;
			case PARIND://how can I only allow the user to correctly trigger start and finish only the correct amount of times
				if (channelNum == 1 && (times.get(0).startTimes.size() + times.get(0).finishTimes.size()) + (times.get(1).startTimes.size() + times.get(1).finishTimes.size()) != racerNums.size()) {
					channels.get(channelNum - 1).trigger(times.get(0), 1);
					times.get(0).racerNums.add(racerNums.get(whichRacer));
					whichRacer++;
					timeObjNum = 0;
				}
				else if (channelNum == 2 && times.get(0).startTimes.size() != 0) {
					channels.get(channelNum - 1).trigger(times.get(0), 2);
				}
				else if (channelNum == 3 && (times.get(0).startTimes.size() + times.get(0).finishTimes.size()) + (times.get(1).startTimes.size() + times.get(1).finishTimes.size()) != racerNums.size()) {
					channels.get(channelNum - 1).trigger(times.get(1), 3);
					times.get(1).racerNums.add(racerNums.get(whichRacer));
					whichRacer++;
					timeObjNum = 1;
				}
				else if (channelNum == 4 && times.get(1).startTimes.size() != 0) {
					channels.get(channelNum - 1).trigger(times.get(1), 4);
				}	

				break;
			case GRP:
				if(timesTriggered >= racerNums.size() + 1) canStillTrigger = false;//the initial start plus the number of racers

				if(canStillTrigger){
					if (channelNum == 1 && times.get(0).getNumTimes() == 0) {//Matt change 5/2 - can only trigger start once
						channels.get(channelNum - 1).trigger(times.get(0), -1);
						timesTriggered++;
						timeObjNum = 0;
					}
					else if (channelNum == 2) {
						channels.get(channelNum - 1).trigger(times.get(0), -1);
						timesTriggered++;
					}
				}
				break;
			case PARGRP:
				if (channelNum == 1 && times.get(0).getNumTimes() == 0) {//Matt change 5/2 - can only trigger start once
					channels.get(0).trigger(times.get(0), 1);
					channels.get(0).setType(false);
					timeObjNum = 0;
				}
				else if (channelNum <= racerNums.size() && times.get(0).getNumTimes() != 0 && !beenTriggered[channelNum-1]) {
					channels.get(channelNum - 1).trigger(times.get(0), channelNum);
					beenTriggered[channelNum - 1] = true;
				}

				break;
			default: 
				temp = -1;
				break;
			}
		}
		return temp;
	}

	/**
	 * For file input only. 
	 * 
	 * Repeat of trigger method for file I/O
	 * 
	 * @param channelNum -- channel number being triggered
	 * @param customTime -- time stamp from file
	 */
	public long trigger(int channelNum, String customTime){
		long temp = 0;
		boolean canStillTrigger = true;

		if (on && eventSet && newRunCalled && channelNum <= channels.size() && channels.get(channelNum - 1).isEnabled()){
			long tempTime = times.get(0).parseMilli(customTime);

			//Trigger is different depending on the race type and channel triggered
			switch(this.eventType){
			case IND:
				if (channelNum == 1 && (times.get(0).startTimes.size() + times.get(0).finishTimes.size()) != racerNums.size()) {
					channels.get(channelNum - 1).trigger(times.get(0), 1,tempTime);
					times.get(0).racerNums.add(racerNums.get(whichRacer));
					whichRacer++; timesTriggered++; 
					timeObjNum = 0;
				}
				else if (channelNum == 2 && times.get(0).startTimes.size() != 0) {//there have to be people currently racing
					channels.get(channelNum - 1).trigger(times.get(0), 2, tempTime);
					timesTriggered++; 
				}
				break;
			case PARIND:
				if (channelNum == 1 && (times.get(0).startTimes.size() + times.get(0).finishTimes.size()) + (times.get(1).startTimes.size() + times.get(1).finishTimes.size()) != racerNums.size()) {
					channels.get(channelNum - 1).trigger(times.get(0), 1,tempTime);
					times.get(0).racerNums.add(racerNums.get(whichRacer));
					whichRacer++;
					timeObjNum = 0;
				}
				else if (channelNum == 2 && times.get(0).startTimes.size() != 0) {
					channels.get(channelNum - 1).trigger(times.get(0), 2,tempTime);
				}
				else if (channelNum == 3 && (times.get(0).startTimes.size() + times.get(0).finishTimes.size()) + (times.get(1).startTimes.size() + times.get(1).finishTimes.size()) != racerNums.size()) {
					channels.get(channelNum - 1).trigger(times.get(1), 3,tempTime);
					times.get(1).racerNums.add(racerNums.get(whichRacer));
					whichRacer++;
					timeObjNum = 1;
				}
				else if (channelNum == 4 && times.get(1).startTimes.size() != 0) {
					channels.get(channelNum - 1).trigger(times.get(1), 4,tempTime);
				}	
				break;
			case GRP:
				if(timesTriggered >= racerNums.size() + 1) canStillTrigger = false;//the initial start plus the number of racers

				if(canStillTrigger){
					if (channelNum == 1 && times.get(0).getNumTimes() == 0) {//Matt change 5/2 - can only trigger start once
						channels.get(channelNum - 1).trigger(times.get(0), -1,tempTime);
						timesTriggered++;
						timeObjNum = 0;
					}
					else if (channelNum == 2) {
						channels.get(channelNum - 1).trigger(times.get(0), -1,tempTime);
						timesTriggered++;
					}
				}
				break;
			case PARGRP:
				if (channelNum == 1 && times.get(0).getNumTimes() == 0) {//Matt change 5/2 - can only trigger start once
					channels.get(0).trigger(times.get(0), 1,tempTime);
					channels.get(0).setType(false);
					timeObjNum = 0;
				}
				else if (channelNum <= racerNums.size() && times.get(0).getNumTimes() != 0 && !beenTriggered[channelNum-1]) {
					channels.get(channelNum - 1).trigger(times.get(0), channelNum,tempTime);
					beenTriggered[channelNum - 1] = true;
				}
				break;
			default: 
				temp = -1;
				break;
			}
		}
		return temp;
	}

	/**
	 * Connect to server and send it an ArrayList of Racers.
	 *
	 * @author Philip Kocol
	 * @param racers
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ProtocolException
	 */
	private static void connect(ArrayList<Racer> racers) throws MalformedURLException, IOException, ProtocolException, ConnectException {
		// Client will connect to this location
		URL site = new URL("http://localhost:8000/sendresults");
		HttpURLConnection conn = (HttpURLConnection) site.openConnection();

		// now create a POST request
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());

		// build a string that contains JSON from console
		String content = "";
		content = getJSON(racers);

		// write out string to output buffer for message
		out.writeBytes(content);
		out.flush();
		out.close();

		System.out.println("Done sent to server");

		InputStreamReader inputStr = new InputStreamReader(conn.getInputStream());

		// string to hold the result of reading in the response
		StringBuilder sb = new StringBuilder();

		// read the characters from the request byte by byte and build up
		// the Response
		int nextChar;
		while ((nextChar = inputStr.read()) > -1) {
			sb = sb.append((char) nextChar);
		}
		System.out.println("Return String: " + sb);
	}

	/**getJSON
	 *
	 * @author Philip Kocol
	 * @param racers
	 * @return returns ArrayList racers in Json standardized formatting
	 */
	private static String getJSON(ArrayList<Racer> racers) {
		Gson g = new Gson();
		String json = g.toJson(racers);
		return json;
	}
}