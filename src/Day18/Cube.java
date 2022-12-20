package Day18;

import java.util.ArrayList;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude="exposed")
public class Cube {
	
	private int x;
	private int y;
	private int z;
	
	private ArrayList<Cube> exposed=new ArrayList<Cube>(); //exposed sides

	public Cube(int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}	

	public void addExposed(Cube cube) {
		exposed.add(cube);
	}
	
}
