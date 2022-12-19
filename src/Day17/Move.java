package Day17;

import java.util.ArrayList;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode()
public class Move {

	private int number;
	private char symbol;
	private Shape shape;
	private ArrayList<Element> lastRows; //10 last rows to detect cycle
	
	public Move(int number, char symbol) {
		super();
		this.number = number;
		this.symbol = symbol;
	}
	
	
}
