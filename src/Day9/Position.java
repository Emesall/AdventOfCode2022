package Day9;

import java.util.Objects;

public class Position {

	//coordinates
	private int x;
	private int y;
	private boolean isVisited;
	
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Position(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public boolean isVisited() {
		return isVisited;
	}
	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	
	
	
	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + ", isVisited=" + isVisited + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		return  x == other.x && y == other.y;
	}
	
	
	
	
}
