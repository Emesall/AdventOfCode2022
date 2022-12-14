package Day14;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day14 {

	static Set<Element> elements = new HashSet<Element>();
	static int floor = 0;

	public static void main(String[] args) throws IOException {

		ArrayList<String> rawData = new ArrayList<String>();

		int part1 = 0;
		int part2 = 0;

		rawData = parseFile("src/Day14/input.txt");

		for (String line : rawData) {
			line = line.replaceAll("\\s", "");
			String[] coords = line.split("->");
			for (int i = 0; i < coords.length - 1; i++) {
				addElements(coords[i], coords[i + 1]);
			}

		}
		floor = findMaxY() + 2;
		//part1 = part1();
		part2 = part2();
		System.out.println(part1);
		System.out.println("----");
		System.out.println(part2);
		
	

	}

	public static int findMaxY() {
		int y = 0;
		for (Element el : elements) {
			if (el.getY() > y) {
				y = el.getY();
			}
		}
		return y;
	}

	public static int part1() {
		boolean finished = false;
		int counter = 0;
		while (!finished) {
			Element el = calculateSandPosition1();

			if (el == null) {
				finished = true;
			} else {
				elements.add(el);
				counter++;
			}
		}
		return counter;
	}

	public static int part2() {

		int counter = 0;
		Element first = new Element(500, 0);
		while (!elements.contains(first)) {

			Element el = calculateSandPosition2();

			elements.add(el);
			counter++;

		}
		return counter;
	}

	public static Element calculateSandPosition1() {
		int actualX = 500;
		int actualY = 0;
		int counter = 0;
		boolean rest = false;

		while (!rest) {
			Element el = findSandValidMove(actualX, actualY);
			if (el == null) {
				return new Element(actualX, actualY);
			}
			actualX = el.getX();
			actualY = el.getY();
			counter++;
			if (counter > 500) {
				rest = true;
			}

		}

		return null;
	}

	public static Element calculateSandPosition2() {
		int actualX = 500;
		int actualY = 0;

		while (true) {
			Element el = findSandValidMove(actualX, actualY);
			if (el == null) {
				return new Element(actualX, actualY);
			}
			actualX = el.getX();
			actualY = el.getY();

			if (actualY == floor-1) {
				return new Element(actualX, actualY);
			}
		}

	}

	public static Element findSandValidMove(int actualX, int actualY) {

		if (!checkIfElementExists(actualX, actualY + 1)) {
			return new Element(actualX, actualY + 1);
		}
		if (!checkIfElementExists(actualX - 1, actualY + 1)) {
			return new Element(actualX - 1, actualY + 1);
		}
		if (!checkIfElementExists(actualX + 1, actualY + 1)) {
			return new Element(actualX + 1, actualY + 1);
		}

		return null;

	}

	public static boolean checkIfElementExists(int x, int y) {
		Element el = new Element(x, y);
		return elements.contains(el);
	}

	public static void addElements(String point1, String point2) {

		String[] coords1 = point1.split(",");
		String[] coords2 = point2.split(",");
		int x1 = Integer.valueOf(coords1[0]);
		int x2 = Integer.valueOf(coords2[0]);
		int y1 = Integer.valueOf(coords1[1]);
		int y2 = Integer.valueOf(coords2[1]);

		// vertical
		if (y2 == y1) {

			for (int i = 0; i <= Math.abs(x2 - x1); i++) {
				int x;
				if (x2 > x1) {
					x = x1 + i;
				} else {
					x = x2 + i;
				}
				Element el = new Element(x, y1);
				elements.add(el);
			}
		}
		// horizontal
		if (x2 == x1) {
			for (int i = 0; i <= Math.abs(y2 - y1); i++) {
				int y;
				if (y2 > y1) {
					y = y1 + i;
				} else {
					y = y2 + i;
				}
				Element el = new Element(x1, y);
				elements.add(el);
			}
		}

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
