package Day20;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day20 {

	static ArrayList<Element> numbers = new ArrayList<Element>();
	static ArrayList<Element> numbers2 = new ArrayList<Element>();
	static final long decryptionKey = 811589153l;

	public static void main(String[] args) throws IOException {

		ArrayList<Integer> data = new ArrayList<Integer>();

		long part1 = 0;
		long part2 = 0;

		data = parseFile("src/Day20/input.txt");
		fillList(data);
		mix(data);
		part1 = countResult(numbers);
		for (int i = 0; i < 10; i++) {
			mix2(data);
		}
		part2 = countResult(numbers2);
		System.out.println(part1);
		System.out.println("----");
		System.out.println(part2);
	}

	public static void mix(ArrayList<Integer> data) {
		for (int i = 0; i < data.size(); i++) {
			Element el = findElement(i, numbers);
			moveElement(el, numbers);
		}

	}

	public static void mix2(ArrayList<Integer> data) {
		for (int i = 0; i < data.size(); i++) {
			Element el = findElement(i, numbers2);
			moveElement(el, numbers2);
		}

	}

	public static long countResult(ArrayList<Element> numbers) {
		long result = 0;
		Element el = numbers.stream().filter(e -> e.getValue() == 0).findFirst().get();
		int index = numbers.indexOf(el);
		long value1 = numbers.get(findDestination(index + 1000, numbers.size())).getValue();
		long value2 = numbers.get(findDestination(index + 2000, numbers.size())).getValue();
		long value3 = numbers.get(findDestination(index + 3000, numbers.size())).getValue();
		result = value1 + value2 + value3;
		return result;

	}

	public static void moveElement(Element el, ArrayList<Element> numbers) {
		int index = numbers.indexOf(el);
		long dest = index + el.getValue();
		numbers.remove(el);
		int des = findDestination(dest, numbers.size());
		
		numbers.add(des, el);
	}

	public static int findDestination(long dest, int listSize) {
		dest = dest % listSize;
		if (dest < 0) {
			dest = Math.abs(dest);
			dest = dest % listSize;
			dest = listSize - dest;

		}

		if (dest == 0) {
			dest = listSize;
		}
		
		return (int) dest;
	}

	public static Element findElement(int number, ArrayList<Element> numbers) {
		return numbers.stream().filter(el -> el.getNumber() == number).findFirst().orElseThrow();
	}

	public static void fillList(ArrayList<Integer> data) {
		for (int i = 0; i < data.size(); i++) {
			Element el = new Element(i, data.get(i));
			numbers.add(el);
			Element el2 = new Element(i, data.get(i) * decryptionKey);
			numbers2.add(el2);
		}
	}

	public static ArrayList<Integer> parseFile(String fileName) throws IOException {

		Scanner scanner = new Scanner(new FileReader(fileName));
		ArrayList<Integer> data = new ArrayList<Integer>();
		while (scanner.hasNext()) {

			data.add(scanner.nextInt());

		}
		scanner.close();

		return data;

	}
}
