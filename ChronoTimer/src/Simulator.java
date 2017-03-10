import java.io.File;
import java.io.FileNotFoundException;
import java.text.*;
import java.util.Date;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

//////////////////////////////////////////
//InteGREAT Development
//Class: CS 361
//Authors: Nicholas 
//
//
//Description: Simulator serves as the 
//interface for console or file input.
//
//////////////////////////////////////////

public class Simulator {
	
	public static void main(String[] args) {
		//Timmy is his name and it's not changing
		Chronotimer timmy = new Chronotimer();
		boolean programRunning = true;
		Scanner scan;
		
		try{
			scan = new Scanner(new File("no"));
		}
		catch(FileNotFoundException e){
			System.out.println("File not found. Please use the console.");
			scan = new Scanner(System.in);
		}
		
		
		String input;
		String timeStamp;
		Date date = new Date();
		boolean cmdFile = false;
		
		while(programRunning){
			input = scan.next();
			timeStamp = null;
			date = new Date();
			if (input.contains(":")) {
				timeStamp = input;
				input = scan.next();
				cmdFile = true;
			}
			else{
				cmdFile = false;
				timeStamp = date.toString().substring(11, 19);
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
						if(cmdFile){
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
					param = Integer.toString(number);
					timmy.setNum(number);
					break;
				case "EVENT":
					String event = scan.next();
					param = event;
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