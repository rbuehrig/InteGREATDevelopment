
public class Lane {
	private Time time; 
	private Channel start, finish;
	
	public Lane(Channel star, Channel fin){
		this.start = star;
		this.finish = fin;
		time = new Time();
	}
	
	public void newRun(){
		time = new Time();
	}
}
