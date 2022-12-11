package Day11;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day11 {

	public static void main(String[] args) throws IOException {

		long part1 = 0;
		ArrayList<String> rawData = new ArrayList<String>();
		Map<Integer, Monkey> monkeys = new HashMap<Integer, Monkey>();
		rawData = parseFile("src/Day11/input.txt");
		int monkey_number = 0;
		Monkey monkey = new Monkey();
		for (String line : rawData) {
			String[] words = line.split(" ");

			if (line.contains("Monkey")) {
				monkey = new Monkey();
			}
			if (line.contains("items")) {
				long[] numbers = getNumbers(words);
				for (long i : numbers) {
					monkey.addItem(i);
				}
			}
			if (line.contains("Operation")) {
				monkey.setOperation(line);
			}
			if (line.contains("Test")) {
				monkey.setDivisible(Long.valueOf(words[5]));
			}
			if (line.contains("true")) {
				monkey.setTrue_monkey(Integer.valueOf(words[9]));
			}
			if (line.contains("false")) {
				monkey.setFalse_monkey(Integer.valueOf(words[9]));
			}
			if (line.isBlank()) {
				monkeys.put(monkey_number, monkey);
				monkey_number++;
			}

		}

		monkeys.put(monkey_number, monkey);
		for (int i = 0; i < 10000; i++) {

			for (Monkey mon : monkeys.values()) {
				ArrayList<Long> items = mon.getItems();
				for (long item : items) {
					Move move = mon.round(item);
					monkeys.get(move.getDestination()).addItem(move.getItem());
				}
				mon.setItems(new ArrayList<>());

			}
		}
		long max1 = 0;
		long max2 = 0;
		for (Monkey mon : monkeys.values()) {
			long num = mon.getInspected();
			if (num > max1) {
				max2=max1;
				max1 = num;
			} else if (num > max2) {
				max2 = num;
			}
			
			System.out.println(mon);
		}

		part1=max1*max2;
		System.out.println(part1);

	}

	public static long[] getNumbers(String[] words) {
		long[] numbers = new long[words.length - 4];
		for (int i = 0; i < numbers.length; i++) {
			String s = words[i + 4].replaceAll(",", "");
			long number = Integer.valueOf(s);
			numbers[i] = number;
		}
		return numbers;
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
