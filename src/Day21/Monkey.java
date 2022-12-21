package Day21;

import java.util.ArrayList;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = "number")
public class Monkey {

	private String name;
	private long number;
	private String expression;

	public Monkey(String name, String expression) {
		super();
		this.name = name;
		this.expression = expression;
	}

	public long calculateNumber(ArrayList<Monkey> monkeys) {

		String[] tab = expression.split(" ");
		if (tab.length == 1) {
			return number = Long.valueOf(expression);
		}
		Monkey monkey1 = monkeys.stream().filter(m -> m.getName().equals(tab[0])).findFirst().get();
		Monkey monkey2 = monkeys.stream().filter(m -> m.getName().equals(tab[2])).findFirst().get();

		switch (tab[1]) {

		case "+":
			number = monkey1.calculateNumber(monkeys) + monkey2.calculateNumber(monkeys);
			break;
		case "-":
			number = monkey1.calculateNumber(monkeys) - monkey2.calculateNumber(monkeys);
			break;
		case "/":
			number = monkey1.calculateNumber(monkeys) / monkey2.calculateNumber(monkeys);
			break;
		case "*":
			number = monkey1.calculateNumber(monkeys) * monkey2.calculateNumber(monkeys);
			break;

		}
		return number;

	}

}
