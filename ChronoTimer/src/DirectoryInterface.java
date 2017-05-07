import java.util.Collection;
////////////////////////////////////////////
//InteGREAT Development
//Class: CS 361
//
//Provide interface with no implementation.
//
//Description: Directory Interface
////////////////////////////////////////////
public interface DirectoryInterface {
	public void add(Collection<Racer> dir);
	
	public void clear();

	void print(String outputSource, int raceNum);
}