import java.util.Collection;
//////////////////////////////////////////
//InteGREAT Development
//Class: CS 361
//
//Implement DirectoryInterface
//
//Description: Directory Interface
//////////////////////////////////////////
public class DirectoryProxy implements DirectoryInterface{
	//Main directory to add files
	MainDirectory d;
	
	/**
	 * Constructor initializes main directory.
	 */
	public DirectoryProxy(){
		d = new MainDirectory();
	}
	
	/**
	 * Adds collection to directory.
	 * 
	 * @param dir -- collection of racers to add
	 */
	@Override
	public void add(Collection<Racer> dir){
		d.add(dir);
	}

	/**
	 * Calls MainDirectory's print method.
	 * 
	 * @param outputSource -- where to print (file or new window)
	 * @param raceNum -- current race number
	 */
	@Override
	public void print(String outputSource,int raceNum) {
		d.print(outputSource,raceNum);
	}
	
	/**
	 * Clears out the main directory.
	 */
	@Override
	public void clear() {
		d.clear();
	}
	
}
