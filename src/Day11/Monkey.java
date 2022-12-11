package Day11;

import java.util.ArrayList;

public class Monkey {

	private ArrayList<Long> items = new ArrayList<>();
	private String operation;
	private Long divisible;
	private int true_monkey; // if test true
	private int false_monkey; // if test false
	private int inspected;

	public Move round(Long item) {

		Move move = new Move();
		//item = (long) Math.floor(performOperation(item)/3.0); //part1
		item = performOperation(item)%9699690; //part2,hardcoded multiplied all divisors
		inspected++;

		move.setItem(item);
		if (item % divisible == 0) {
			move.setDestination(true_monkey);
		} else {
			move.setDestination(false_monkey);
		}

		return move;
	}

	private Long performOperation(Long item) {
		Long result = 0l;
		Long value = 0l;
		String[] operations = operation.split(" ");
		if (operations[7].equals("old")) {
			//value = item;
			value = item;
		} else {
			value = Long.valueOf(operations[7]);
		}

		if (operations[6].equals("+")) {
			result = item + value;
		}
		if (operations[6].equals("*")) {
			result = item * value;
		}

		return result;

	}

	public void addItem(Long number) {
		items.add(number);
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public void setDivisible(Long divisible) {
		this.divisible = divisible;
	}

	public void setTrue_monkey(int true_monkey) {
		this.true_monkey = true_monkey;
	}

	public void setFalse_monkey(int false_monkey) {
		this.false_monkey = false_monkey;
	}

	

	

	@Override
	public String toString() {
		return "Monkey [items=" + items + ", inspected=" + inspected + "]";
	}

	public ArrayList<Long> getItems() {
		return items;
	}

	public void setItems(ArrayList<Long> items) {
		this.items = items;
	}

	public int getInspected() {
		return inspected;
	}

	
	
	

}
