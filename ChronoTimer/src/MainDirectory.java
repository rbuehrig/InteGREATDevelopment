import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class MainDirectory implements DirectoryInterface {
	
	String directory;
	ArrayList<Racer> dir;
	Printer printer;
	Gson g;
	
	public MainDirectory(){
		directory = "";
		printer = new Printer();
		g = new Gson();
	}

	@Override
	public void add(Collection<Racer> dir) {
		directory = g.toJson(dir);
	}

	@Override
	public void print(String outputSource) {
		dir = (g.fromJson(directory, new TypeToken<Collection<Racer>>(){}.getType()));
		
		if(dir.isEmpty()){
			System.out.println("No Racers in queue.");
		}
		if (outputSource.equals("file")) {printer.print(dir,false);}
		else {printer.print(dir,true);}
		
	}

	@Override
	public void clear() {
		directory = "";
	}
	
}