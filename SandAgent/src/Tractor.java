
public class Tractor {
	
	private int x, y;
	private boolean north, west, east, south;
	
	public Tractor(int x, int y) {
		this.x = x;
		this.y = y;
	}	
	
	public void movements(int column){
		if(north)
			x--;
		if(west)
			y--;
		if(east)
			y++;
		if(south)
			x++;
	}	

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
