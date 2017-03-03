import java.io.File;
import java.io.FileNotFoundException;
import java.text.*;
import java.util.Calendar;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public class Simulator {
	
	public static void main(String[] args) {
		//Timmy is his name and it's not changing
		Chronotimer timmy = new Chronotimer();
		boolean programRunning = true;
		Scanner scan;
		
		try{
			scan = new Scanner(new File("CTS1RUN2"));
		}
		catch(FileNotFoundException e){
			System.out.println("File not found. Please use the console.");
			scan = new Scanner(System.in);
		}
		
		String input;
		String timeStamp;
		Calendar date = new GregorianCalendar();
		
		while(programRunning){
			input = scan.next();
			timeStamp = null;
			if (input.contains(":")) {
				//TODO So yeah, I need some kind of set time, this is my way to handle the time stamps
				// at the start of the console files. This problem also comes up for the time command.
				// NOTE: I'm leaving it to chronotimer to print out any output, this includes the time stamps
				// WE are supposed to print out before each input to the console. I figure there can just be a 
				// "was time just set" check and if it's true we won't print our own timestamps, as they will be
				// entered by the read file.
				
				//Break the timestamp (if it exists) into list elements which should be HH, MM, SS
				timeStamp = input;
				input = scan.next();
			}
			else{
				timeStamp = Integer.toString(date.get(date.HOUR_OF_DAY)) + ":" + Integer.toString(date.get(date.MINUTE)) + ":" + Integer.toString(date.get(date.SECOND) );
			}
			
			int channel;
			int trigger;
			String param = "";
			switch(input){
				case "POWER":
					timmy.powerToggle();
					break;
				case "EXIT":
					timmy.powerToggle();
					programRunning = false;
					break;
				case "RESET":
					timmy.reset();
					break;
				case "TIME": 
					//I for real have no idea how this would even work lol
					param = scan.next();
					break;
				case "DNF":
					timmy.DNF();
					break;
				case "CANCEL":
					timmy.cancel();
					break;
				case "TOG":
					channel = scan.nextInt();
					param = Integer.toString(channel);
					timmy.toggle(channel);
					break;
				case "TOGGLE":
					channel = scan.nextInt();
					param = Integer.toString(channel);
					timmy.toggle(channel);
					break;
				case "TRIG":
					trigger = scan.nextInt();
					param = Integer.toString(trigger);
					/*hacky way of forcing triggers on only 1 or 2
					 * this way we can have all the channels as actual objects
					 * to avoid null pointer dereferencing
					 * 
					 * This will need to be modified when we add new event types
					 */
					if(trigger <= 2){
						if(timeStamp != null){
							timmy.trigger(trigger, timeStamp);
						}
						else{
							timmy.trigger(trigger);
						}
					}
					break;
				case "NEWRUN":
					timmy.newRun();
					break;
				case "START":
					timmy.start();
					break;
				case "ENDRUN":
					timmy.endRun();
					break;
				case "FINISH":
					timmy.finish();
					break;
				case "NUM":
					int number = scan.nextInt();
					timmy.setNum(number);
					break;
				case "EVENT":
					String event = scan.next();
					timmy.setEvent(event);
					break;
				case "PRINT":
					timmy.print();
					break;
					
				default:
					System.out.println("You can't do that");
					break;
			}
			echo(timeStamp, input, param);
		}
		

	}
	
	private static void echo(String timeStamp, String command, String param){
		System.out.println(timeStamp + "\t" + command + " " + param);
	}

}
