package Day13;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class Day13 {

	public static void main(String[] args) throws IOException {

		ArrayList<String> rawData = new ArrayList<String>();

		int part1 = 0;
		int part2 = 0;

		rawData = parseFile("src/Day13/input.txt");
		part1 = part1(rawData);
		part2 = part2(rawData);
		System.out.println(part1);
		System.out.println("----");
		System.out.println(part2);
	}

	public static int part1(ArrayList<String> rawData) {

		int counter = 0;
		int result = 0;
		for (int i = 0; i < rawData.size(); i = i + 3) {
			counter++;
			if (isLessThan(rawData.get(i), rawData.get(i + 1))) {
				result += counter;

			}

		}

		return result;
	}

	public static int part2(ArrayList<String> rawData) {

		int result = 0;
		ArrayList<String> sorted = new ArrayList<String>();
		String divider1 = "[[2]]";
		String divider2 = "[[6]]";
		sorted.add(divider1);
		sorted.add(divider2);

		rawData.stream().filter(s -> !s.isBlank()).forEach(s -> sorted.add(s));

		sorted.sort(new Day13().new LineComparator());
		result = (sorted.indexOf(divider1)+1) * (sorted.indexOf(divider2) +1);

		return result;
	}

	public class LineComparator implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			if (isLessThan(o1, o2)) {
				return -1;
			}
			if (isLessThan(o2, o1)) {
				return 1;
			}
			return 0;
		}

	}

	public static boolean isLessThan(String firstLine, String secondLine) {

		List<Object> firstValues = parseLine(firstLine);
		List<Object> secondValues = parseLine(secondLine);

		for (int i = 0; i < Math.min(secondValues.size(), firstValues.size()); i++) {
			Object leftValue = firstValues.get(i);
			Object rightValue = secondValues.get(i);

			if (leftValue instanceof Integer && rightValue instanceof Integer) {

				if ((Integer) leftValue > (Integer) rightValue) {
					return false;
				} else if ((Integer) rightValue > (Integer) leftValue) {
					return true;
				}
			} else if (leftValue instanceof List && rightValue instanceof List) {

				String leftList = leftValue.toString();
				String rightList = rightValue.toString();
				if (isLessThan(leftList, rightList)) {
					return true;
				} else if (isLessThan(rightList, leftList)) {
					return false;
				}
			} else {

				if (leftValue instanceof Integer) {
					leftValue = Collections.singletonList(leftValue);
				} else {
					rightValue = Collections.singletonList(rightValue);
				}
				String leftList = leftValue.toString();
				String rightList = rightValue.toString();
				if (isLessThan(leftList, rightList)) {
					return true;
				} else if (isLessThan(rightList, leftList)) {
					return false;
				}
			}

		}

		return secondValues.size() > firstValues.size();
	}

	public static List<Object> parseLine(String line) {
		List<Object> values = new ArrayList<>();

		Deque<List<Object>> stack = new ArrayDeque<List<Object>>();
		stack.push(values);

		String digit = "";
		for (int i = 1; i < line.length(); i++) {
			char c = line.charAt(i);
			if (c == '[') {

				List<Object> newList = new ArrayList<>();
				stack.peek().add(newList);
				stack.push(newList);
			} else if (c == ']') {
				if (!digit.isBlank()) {
					stack.peek().add(parseValue(digit));
					digit = "";
				}
				stack.pop();
			} else if (c == ',') {

				if (!digit.isBlank()) {

					stack.peek().add(parseValue(digit));
					digit = "";
				}
			} else {

				digit += String.valueOf(c);

			}
		}

		return values;
	}

	public static Object parseValue(String value) {
		value = value.replaceAll(" ", "");
		return Integer.parseInt(value);

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
