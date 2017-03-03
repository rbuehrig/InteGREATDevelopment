import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Simulator {
	
	public static void main(String[] args) {
		//Timmy is his name and it's not changing
		Chronotimer timmy = new Chronotimer();
		boolean programRunning = true;
		Scanner scan;
		
		try{
			scan = new Scanner(new File("WhateverYouWant.txt"));
		}
		catch(FileNotFoundException e){
			System.out.println("File not found. Please use the console.");
			scan = new Scanner(System.in);
		}
		
		while(programRunning){
			String input = scan.next();
			String timeStamp = null;
			if (input.contains(":")) {
				//TODO So yeah, I need some kind of set time, this is my way to handle the time stamps
				// at the start of the console files. This problem also comes up for the time command.
				// NOTE: I'm leaving it to chronotimer to print out any output, this includes the time stamps
				// WE are supposed to print out before each input to the console. I figure there can just be a 
				// "was time just set" check and if it's true we won't print our own timestamps, as they will be
				// entered by the read file.
				
				//Break the timestamp (if it exists) into list elements which should be HH, MM, SS
				
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
					//timmy.setTime(timeLister[0], timeLister[1], timeLister[2]);
					break;
				case "DNF":
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

}
