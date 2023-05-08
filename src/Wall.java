import org.javatuples.Quartet;

public class Wall {
	
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	public Wall (int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public Quartet<Integer, Integer, Integer, Integer> getPosition () {
		return new Quartet<Integer, Integer,Integer,Integer>(x1,y1, x2, y2);
	}

}
