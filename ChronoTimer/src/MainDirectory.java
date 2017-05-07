import java.util.ArrayList;
import java.util.Collection;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
//////////////////////////////////////////
//InteGREAT Development
//Class: CS 361
//
//Implement DirectoryInterface
//
//Description: Main directory
//////////////////////////////////////////
public class MainDirectory implements DirectoryInterface {
	//directory object represented as a string
	String directory;
	
	//Collection of racers
	ArrayList<Racer> dir;
	
	//Printer object
	Printer printer;
	
	//JSON object -- using Google's GSON
	Gson g;
	
	/**
	 * Constructor initializes main directory.
	 */
	public MainDirectory(){
		directory = "";
		printer = new Printer();
		g = new Gson();
	}

	/**
	 * Assign directory string the JSON representation
	 * of the collection of racers.
	 * 
	 * @param dir -- Collection of racers to convert
	 */
	@Override
	public void add(Collection<Racer> dir) {
		directory = g.toJson(dir);
	}

	/**
	 * Convert the JSON representation to a collection then
	 * send it to printer object for output.
	 * 
	 * @param outputSource -- where to print (file or new window)
	 * @param raceNum -- current race number
	 */
	@Override
	public void print(String outputSource,int raceNum) {
		dir = (g.fromJson(directory, new TypeToken<Collection<Racer>>(){}.getType()));
		
		if (dir.isEmpty()) {System.out.println("No Racers in queue.");}
		if (outputSource.equals("file")) {printer.print(dir,false,raceNum);}
		else {printer.print(dir,true,raceNum);}
		
	}

	/**
	 * Reset string representation of directory.
	 */
	@Override
	public void clear() {
		directory = "";
	}
	
}