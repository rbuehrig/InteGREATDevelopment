import java.time.Clock;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;


public class Time {
	private LinkedList<Long> startTimes;
	private LinkedList<Long> racerTimes;
	
	private Clock clock;
	
	public Time(){
		startTimes = new LinkedList<Long>();
		racerTimes = new LinkedList<Long>();
	}
	
	/**
	 * Default method for a start event
	 * Adds current time to start list 
	 * 
	 * @author Philip Kocol
	 * @return clock.millis()
	 */
	public long start(){
		long start = clock.millis();
		startTimes.add(start);
		return start;
	}
	
	/**
	 * Overload for start()
	 * Allows for user input as start time
	 * 
	 * @author Philip Kocol
	 * @param start
	 * @return start time that was added
	 */
	public long start(long start){ //change - should this check if there already is a start time and then remove it if there is and just reset the start time
		startTimes.add(start);
		return start;
	}
	
	/**
	 * Default method for finish event
	 * Calculates time for next racer in the the queue and adds it to a list of race times
	 * 
	 * @author Philip Kocol
	 * @return finish time of next racer
	 * @throws NoSuchElementException
	 */
	public long finish(){
		if(startTimes.isEmpty()) throw new NoSuchElementException("No racers started");
		
		long finish = clock.millis();
		long start = startTimes.pollFirst();
		long time = finish - start;
		racerTimes.add(time);
		return time;
	}
	
	/**
	 * Overload for finish()
	 * Allows for user suppplied finish time
	 * 
	 * @author Philip Kocol
	 * @param finish
	 * @return finish time of next racer
	 * @throws NoSuchElementException
	 */
	public long finish(long finish){
		if(startTimes.isEmpty()) throw new NoSuchElementException("No racers started");
		
		long start = startTimes.pollFirst();
		long time = finish - start;
		racerTimes.add(time);
		return time;
		
	}
	
	/**
	 * Sets next racer time to -1, signifying a DNF or "did not finish"
	 * 
	 * @author Philip Kocol
	 */
	public void dnf(){
		startTimes.remove();
		racerTimes.add((long)-1);
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
	 * Returns a list of racer times in order of when the start gate was triggered
	 * 
	 * @return LinkedList of racer times
	 */
	public LinkedList<Long> getTimes(){
		return racerTimes;
	}
	
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
		String[] secondParts = second.split(".");
		long second1 = Long.parseLong(secondParts[0]);
		long second2 = Long.parseLong(secondParts[1]); 
		
		
		long parsedTime = (hour * 3600000) + (minute * 60000) + (second1 * 1000) + (second2 * 10);
		return parsedTime;
		
	}
	

}
