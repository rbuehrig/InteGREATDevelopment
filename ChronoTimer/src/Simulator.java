import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.*;

/** 
* This class represents a crude user interface,
*  allowing for simple commands to be typed,
*  or read by file
*  
* @version 1 - 03/2/17
* @author Nicholas Kopplin
*/
public class Simulator {

	//Timmy is his name and it's not changing
	Chronotimer timmy = new Chronotimer();
	boolean programRunning = true;
	Scanner scan; //TODO <<<<ALSO: I'm getting a weird error here idk what it's about or if it's just my broken eclipse

	//TODO I don't remember if we talked about checking if they want to use a file,
	// so I'm just checking for the kind of input, and  I just left it looking 
	// for a specific filename, and I wasn't sure what Name we'd want for the file...
	try{
		scan = new Scanner(new File("WhateverYouWant.txt"));
	}
	catch(FileNotFoundException e){
		System.out.println("File not found. Please use the console.");
		scan = new Scanner(System.in);
	}
	
	//Loop through input until we get an exit command. Even if the machine is turned on and off
	while(programRunning){
		String input = scan.next();
		if (input.contains(":")) {
			//Matt comment
			//We do need to check to see if it has : to see if it did come from a file, but we dont need to split it here
			//and it probably shouldnt be the job of the simulator to do this anyway
			
			
			//TODO So yeah, I need some kind of set time, this is my way to handle the time stamps
			// at the start of the console files. This problem also comes up for the time command.
			// NOTE: I'm leaving it to chronotimer to print out any output, this includes the time stamps
			// WE are supposed to print out before each input to the console. I figure there can just be a 
			// "was time just set" check and if it's true we won't print our own timestamps, as they will be
			// entered by the read file.
			
			//Break the timestamp (if it exists) into list elements which should be HH, MM, SS
			//List<String> timeList = Arrays.asList(input.split(":"));
			//timmy.setTime(timeList.get(0), timeList.get(1), timeList.get(2));
			timmy.setTime(input);
		}
		switch(scan.next()){
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
				 //TODO ^ See above ^ 
				List<String> timeLister = Arrays.asList(scan.next().split(":"));
				timmy.setTime(scan.next());//change - time stamp after time passed to setTime
				break;
			case "DNF":
				timmy.DNF();//change - does have DNF method
				//TODO !!!! timmy needs a did not finish function!!!!!!
				break;
			case "CANCEL":
				timmy.cancel();
				break;
			case "TOG":
				int channel = scan.nextInt();
				timmy.toggle(channel);
				break;
			case "TOGGLE":
				int ch = scan.nextInt();
				timmy.toggle(ch);
				break;
			case "TRIG":
				int x = scan.nextInt();
				timmy.trigger(x);
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
				
			default:
				System.out.println("You can't do that");
				break;
		}
	}
}