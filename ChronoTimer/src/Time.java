import java.util.LinkedList;
import java.util.NoSuchElementException;

//////////////////////////////////////////
//InteGREAT Development
//Class: CS 361
//Authors: Phil
//
//
//Description: Time object.
//
//////////////////////////////////////////

public class Time {
	//Keep track of start times in order
	protected LinkedList<Long> startTimes;
	
	//Keep track of the finishTimes in order
	protected LinkedList<Long> finishTimes;
	
	//Keep track of which racer corresponds to time object
	protected LinkedList<Integer> racerNums;
	
	/**
	 * Initializes class fields
	 */
	public Time(){
		startTimes = new LinkedList<Long>();
		finishTimes = new LinkedList<Long>();
		racerNums = new LinkedList<Integer>();
	}
	
	/**
	 * Default method for a start event
	 * Adds current time to start list 
	 * 
	 * @author Philip Kocol
	 * @return clock.millis()
	 */
	public long start(){
		long start = System.currentTimeMillis();
		startTimes.add(start);
		return start;
	}
	
	/**
	 * Only use if input is from file.
	 * 
	 * Sets start time as given from file.
	 * 
	 * @author Philip Kocol
	 * @param start -- time from file input
	 * @return start time that was added
	 */
	public long start(long start){ 
		startTimes.add(start);
		return start;
	}
	
	/**
	 * Only use if input is from GUI.
	 * 
	 * Default method for finish event
	 * Calculates time for next racer in the the queue and adds it to a list of race times
	 * 
	 * @author Philip Kocol
	 * @return finish time of next racer
	 * @throws NoSuchElementException
	 */
	public long finish(){
		if(startTimes.isEmpty()){
			System.out.println("No racers started");
			return -1;
		}
		
		long finish = System.currentTimeMillis();
		long start = startTimes.pollFirst();
		long time = finish - start;
		finishTimes.add(time);
		return time;
	}
	
	/**
	 * Only use if input is from file.
	 * 
	 * Submits racer's time to finishTimes queue.
	 * 
	 * @param finish
	 * @return finish time of next racer
	 */
	public long finish(long finish){
		if (!startTimes.isEmpty()){
			long start = startTimes.pollFirst();
			long time = finish - start;
			finishTimes.add(time);
			return time;
		}
		return 0;
	}
	
	/**
	 * Stub for GroupTime to implement
	 * 
	 * @return Normal time object -> null; GroupTime -> start time
	 */
	public long finish(int channel){
		return -1;
	}
	
	/**
	 * Stub for GroupTime to implement
	 * 
	 * @return Normal time object -> null; GroupTime -> start time
	 */
	public long finish(int channel, long finishTime){
		return -1;
	}
	
	/**
	 * Sets next racer time to -1, signifying a DNF or "did not finish"
	 * 
	 * @author Philip Kocol
	 */
	public void dnf(){
		startTimes.remove();
		finishTimes.add((long)-1);
	}
	
	/**
	 * Removes start time of last racer put in the queue
	 * Signifies a "cancel"
	 * 
	 * @author Philip Kocol
	 */
	public void cancel(){
		startTimes.removeLast();
	}
	
	/**
	 * Swaps next two racers
	 * 
	 * @author Nicholas Kopplin
	 */
	public void swap(){
		Long startTimeFirst = startTimes.removeLast();
		Long startTimeSecond = startTimes.removeLast();
		startTimes.add(startTimeSecond);
		startTimes.add(startTimeFirst);
	}
	
	/**
	 * Returns the amount of racers that have started.
	 * 
	 * @return number of started races
	 */
	public int getNumTimes(){
		return startTimes.size();
	}
	
	/**
	 * Returns a list of racer times in order of when the start gate was triggered
	 * 
	 * @return LinkedList of racer times
	 */
	public LinkedList<Long> getTimes(){
		return finishTimes;
	}
	
	/**
	 * Stub for GroupTime to implement
	 * 
	 * @return Normal time object -> null; GroupTime -> start time
	 */
	public String getStartTime(){return null;}
	
	/**
	 * Parses a string of format "HH:MM:SS.SS" into milliseconds for time calculation
	 * 
	 * @author Philip Kocol
	 * @param time
	 * @return time in milliseconds since 00:00:00.00
	 */
	public long parseMilli(String time){
		String[] parts = time.split(":");
		long hour = Long.parseLong(parts[0]);
		long minute = Long.parseLong(parts[1]);
		String second = parts[2];
		String[] secondParts = second.split("\\.");
		long second1 = Long.parseLong(secondParts[0]);
		long second2 = Long.parseLong(secondParts[1]); 
		
		
		long parsedTime = (hour * 3600000) + (minute * 60000) + (second1 * 1000) + (second2 * 10);
		return parsedTime;
	}
}