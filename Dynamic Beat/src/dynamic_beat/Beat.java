package dynamic_beat;

public class Beat {
	private int time;
	private String noteName;
	
	//Getter, Setter
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getNoteName() {
		return noteName;
	}
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	
	//Constructor
	public Beat(int time, String noteName) {
		super();
		this.time = time;
		this.noteName = noteName;
	}
}
