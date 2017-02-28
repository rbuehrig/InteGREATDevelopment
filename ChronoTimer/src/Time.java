import java.time.Clock;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;


public class Time {
	private LinkedList<Long> startTimes;
	private LinkedList<Long> racerTimes;
	//private LinkedList<Integer> racers;
	
	private Clock clock;
	
	public Time(){
		startTimes = new LinkedList<Long>();
		racerTimes = new LinkedList<Long>();
		//racers = new LinkedList<Integer>();
	}
	
	public long start(){
		long start = clock.millis();
		startTimes.add(start);
		//racers.add(racer);
		return start;
	}
	
	public long finish(){
		if(startTimes.isEmpty()) throw new NoSuchElementException("No racers started");
		
		long finish = clock.millis();
		long start = startTimes.pollFirst();
		racerTimes.add(finish - start);
		return start;
	}
	
	/*Sets next racer to finish's time to -1 representing DNF*/
	public void dnf(){
		startTimes.remove();
		racerTimes.add((long)-1);
	}
	
	/*Completely removes start time of most recently added racer*/
	public void cancel(){
		startTimes.removeLast();
	}
	
	/*Returns list of racer times in order of when each finished*/
	public LinkedList<Long> getTimes(){
		return racerTimes;
	}
	
	

}
