import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class DirectoryProxy implements DirectoryInterface{
	
	MainDirectory d;
	
	public DirectoryProxy(){
		d = new MainDirectory();
	}
	
	@Override
	public void add(Collection<Racer> dir){
		d.add(dir);
	}

	@Override
	public void print(String outputSource) {
		d.print(outputSource);
	}

	@Override
	public void clear() {
		d.clear();
	}
	
}