import java.util.Collection;

public interface DirectoryInterface {
	public void add(Collection<Racer> dir);
	
	public void clear();

	void print(String outputSource, int raceNum);
}
