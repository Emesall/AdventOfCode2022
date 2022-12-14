package Day12;

import java.util.Objects;

public class Node {

	private int number;
	private char symbol;
	
	public Node(int number,char symbol) {
		super();
		this.number = number;
		this.symbol = symbol;
	}

	
	public char getSymbol() {
		return symbol;
	}



	public int getNumber() {
		return number;
	}



	public void setNumber(int number) {
		this.number = number;
	}



	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}



	@Override
	public String toString() {
		return "Node [number=" + number + ", symbol=" + symbol + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(number);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		return number == other.number;
	}
	
	




	

	

}
