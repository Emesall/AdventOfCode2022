package Day21;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day21 {

	static ArrayList<Monkey> monkeys = new ArrayList<Monkey>();
	static String rootExpression;

	public static void main(String[] args) throws IOException {

		ArrayList<String> rawData = new ArrayList<String>();

		long part1 = 0;
		long part2 = 0;

		rawData = parseFile("src/Day21/input.txt");
		rawData.forEach(s -> addMonkey(s));
		monkeys.forEach(m -> m.calculateNumber(monkeys));
		part1 = monkeys.stream().filter(m -> m.getName().equals("root")).findFirst().get().getNumber();
		System.out.println(part1);
		System.out.println("-------");

		// part2
		long number = 0L;
		long number1 = 0L;
		long number2 = 0L;
		long diff = 0;
		monkeys = new ArrayList<Monkey>();
		rawData.forEach(s -> addMonkey(s));
		while (number1 != number2 || number1 == 0) {
			long prevNumber1=number1;
			monkeys.stream()
					.filter(m -> m.getName().equals("humn"))
					.findFirst()
					.get()
					.setExpression(String.valueOf(number));
			monkeys.forEach(m -> m.calculateNumber(monkeys));
			String[] data = rootExpression.split(" ");
			number1 = monkeys.stream().filter(m -> m.getName().equals(data[0])).findFirst().get().getNumber();
			number2 = monkeys.stream().filter(m -> m.getName().equals(data[2])).findFirst().get().getNumber();
			
			diff=prevNumber1-number1;
			if(diff!=0) {
				number=number+(number1-number2)/diff;
			}
			else {
				number+=1;
			}
			
			
			System.out.println(number1 + " || " + number2);
		}
		part2=number;
		System.out.println(part2);
		// System.out.println(monkeys);

	}

	public static void part2() {

	}

	public static void addMonkey(String line) {
		String[] data = line.split(":");
		data[1] = data[1].replaceFirst(" ", "");
		if (data[0].equals("root"))
			rootExpression = data[1];
		Monkey monkey = new Monkey(data[0], data[1]);
		monkeys.add(monkey);
	}

	public static ArrayList<String> parseFile(String fileName) throws IOException {

		Scanner scanner = new Scanner(new FileReader(fileName));
		ArrayList<String> data = new ArrayList<String>();
		while (scanner.hasNext()) {

			data.add(scanner.nextLine());

		}
		scanner.close();

		return data;

	}
}
