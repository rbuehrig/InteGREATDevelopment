import java.util.ArrayList;
import java.util.LinkedList;

public class Chronotimer {
	//This time variable will serve as the keeper of 
	//racer's start and finish times for reference.
	Time time;
	
	//A queue to keep track of each racer's number
	//in order. Implemented as an ArrayList for now.
	ArrayList<Integer> racerNums;
	
			//chan will probably have a array of times in the class 
			//itself and the position of the time can simply indicate the racers time.
			//possibly the channel class could pass the set user time to the channel 
			//class and it can somehow use it to initialize each race time to the chronotimers clock.
	
	//Keeps track of channels we need to use in the race.
	ArrayList<Channel> channels;
	
	//Boolean to keep track of if the system has been turned on.
	protected boolean on = false;

	//Boolean to keep track of if the current run has finished. 
	protected boolean finishRun;
	
	
	/** 
	* This method calls reset and changes the on boolean
	* attribute to its opposite state to simulate turning
	* off or on.
	* 
	* @version 1 - 02/26/17
	* @author Matthew Buchanan and Rylie Buehrig
	*/
	public void powerToggle(){			
		reset();
		on = !on;
		
		//Allows for a newRun to be called.
		finishRun = true;
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
		channels = new ArrayList<Channel>();
		channels.add(new Channel(true,time));
		channels.add(new Channel(true,time));
	}
	
	
	/** 
	* 
	* 
	* @version 1 - 02/26/17
	* @author Matthew Buchanan and Rylie Buehrig
	*/
	public void setEvent(String eventType){
		if (eventType.equals("IND")){
			//add channels
		}
		
	}
	
	
	/** 
	* 
	* 
	* @version 1 - 02/26/17
	* @author Matthew Buchanan and Rylie Buehrig
	*/
	public void setTime(){
		//Nothing happening for now.
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
		racerNums.add(racerNum);
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
		LinkedList<Long> racerTimes = time.getTimes();
		//int totalNumRacers = racerTimes.size();
		
		for (int i = 0; i < racerNums.size(); i++){
			//Make sure DNF flag was not set
			if (racerTimes.get(i) != null) {
				if (racerTimes.get(i) != ((long) -1)){
					System.out.println("Racer: " + racerNums.get(i));
					System.out.println("Total Time: " + racerTimes.get(i) + "\n");
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
	
		
	/** 
	* This method will create a new run by clearing all 
	* data structures.
	* 
	* @version 1 - 02/27/17
	* @author Rylie Buehrig
	*/
	public void newRun(){
		if (finishRun) {
			reset();
			finishRun = false;
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
		finishRun = true;
		//save current data?
	}
	
	
	/** 
	* This method will call the time DNF method to set 
	* a flag of -1 for the next racer's end time.
	* 
	* @version 1 - 02/26/17
	* @author Matthew Buchanan and Rylie Buehrig
	*/
	public void DNF(){
		if (on) time.dnf();
		//should we change the channel type?
	}

	
	/** 
	* This method will call the time cancel method
	* to remove the last start time in the queue.
	* 
	* @version 1 - 02/26/17
	* @author Matthew Buchanan and Rylie Buehrig
	*/
	public void cancel(){
		if (on) time.cancel();
	}

	
	/** 
	* This method will call the channel's toggle method. 
	* Arms or disarms the specified channel.
	* 
	* @version 1 - 02/26/17
	* @author Matthew Buchanan and Rylie Buehrig
	*/
	public void toggle(int channelNum){
		if (on) channels.get(channelNum - 1).toggle();
	}
	

	/** 
	* This method will call the channel's trigger method.
	* Sets channel as either a "start" or "end" channel.
	* 
	* @version 1 - 02/26/17
	* @author Matthew Buchanan and Rylie Buehrig
	*/
	public void trigger(int channelNum){
		if (on) channels.get(channelNum - 1).trigger();
	}
}
