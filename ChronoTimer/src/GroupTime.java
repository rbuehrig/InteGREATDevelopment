import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.NoSuchElementException;

//////////////////////////////////////////
//InteGREAT Development
//Class: CS 361
//Authors: Nicholas Kopplin
//
//
//Description: GroupTime class, Inherits from time
//
//////////////////////////////////////////
public class GroupTime extends Time{
	private long groupStartTime;
	
	private String timeStamp;

	public GroupTime(){
		groupStartTime = 0;
		finishTimes = new LinkedList<Long>();
		racerNums = new LinkedList<Integer>();
		timeStamp = "";
	}
	
	/**
	 * Default method for a start event
	 * sets start time to current time 
	 * 
	 * @author Nicholas Kopplin
	 * @return clock.millis()
	 */
	public long start(){
		if(groupStartTime == 0){
			groupStartTime = System.currentTimeMillis();
			
			//stores a time stamp for displaying start time
			Date date = new Date();
			timeStamp = date.toString().substring(11,19);
			
		}
		else{
			//do nothing
		}
		return groupStartTime;
	}
	
	/**
	 * Overload for start()
	 * Allows for user input as start time
	 * 
	 * @author Nicholas Kopplin
	 * @param start
	 * @return start time that was added
	 */
	public long start(long start){ //change - should this check if there already is a start time and then remove it if there is and just reset the start time
		if(groupStartTime == 0){
			groupStartTime = start;
		}
		else{
			//do nothing
		}
		return groupStartTime;
	}
	
	/**
	 * Default method for finish event
	 * Calculates time for next racer in the the queue and adds it to a list of race times
	 * 
	 * @author Nicholas Kopplin
	 * @return finish time of next racer
	 */
	public long finish(){
		if(groupStartTime == 0){
			System.out.println("Race Has Not Started.");
			return -1;
		}
		
		long time = System.currentTimeMillis() - groupStartTime;
		finishTimes.add(time);
		return time;
	}
	
	/**
	 * Overload for finish()
	 * Allows for user suppplied finish time
	 * 
	 * @author Nicholas Kopplin
	 * @param finish
	 * @return finish time of next racer
	 */
	public long finish(long finish){

		if (groupStartTime > 0){
			long time = finish - groupStartTime;
			finishTimes.add(time);
			return time;
		}
		return 0;
	}
	
	/**
	 * Stub for GroupTime to implement
	 * 
	 * 
	 * @return groupStartTime
	 */
	public String getStartTime(){
		return timeStamp;
	}
	
	/**
	 * Sets next racer time to -1, signifying a DNF or "did not finish"
	 * 
	 * @author Nicholas Kopplin
	 */
	public void dnf(){
		finishTimes.add((long)-1);
	}
	
	/**
	 * Does nothing (OVERIDE PARENT)
	 * @author Philip Kocol
	 */
	public void cancel(){
	}
	
	/**
	 * Does Nothing (OVERIDE PARENT)
	 * @return -1, because we won't know how many racers there are until all have finished
	 */
	public int getNumTimes(){
		return -1;
	}
	
}