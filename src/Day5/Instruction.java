package Day5;

public class Instruction {

	private int amountToMove;
	private int source;
	private int dest;
	
	public Instruction() {
		super();
	}

	public Instruction(int amountToMove, int source, int dest) {
		super();
		this.amountToMove = amountToMove;
		this.source = source;
		this.dest = dest;
	}

	@Override
	public String toString() {
		return "Instruction [amountToMove=" + amountToMove + ", source=" + source + ", dest=" + dest + "]";
	}

	public int getAmountToMove() {
		return amountToMove;
	}


	public int getSource() {
		return source;
	}


	public int getDest() {
		return dest;
	}

	
	

	
	
}
