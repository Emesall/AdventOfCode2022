package Day17;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Day17 {

	static Set<Element> elements = new HashSet<Element>();
	static Queue<Move> movements = new LinkedList<Move>();
	static Queue<Shape> shapes = new LinkedList<Shape>();
	static int maxHeight = 0;
	static Map<Move, Integer> moveMap = new HashMap<Move, Integer>();
	static int cycle = 0; // number after which cycle repeats
	static boolean cycleFound = false;
	static int shapeCounter = 0;
	static int heightFirstCycles = 0; // height after first cycles

	public static void main(String[] args) throws IOException {

		String rawdata = parseFile("src/Day17/input.txt");
		int part1 = 0;
		long part2 = 0;
		int y = 0;
		int numberToFindCycle = 10000;
		char[] array = rawdata.toCharArray();
		for (int i = 0; i < array.length; i++) {
			Move move = new Move(i, array[i]);
			movements.add(move);
		}
		createShapes();
		createFloor();
		for (int i = 0; i <= numberToFindCycle; i++) {

			shapeCounter++;

			Shape shape = shapes.remove();
			shapes.add(new Shape(shape.getName()));
			maxHeight = findMax();

			if (shape.getName() != "+") {
				y = maxHeight + 4;
			} else {
				y = maxHeight + 5;
			}
			Element left = new Element(-1, y);
			shape.addElements(left);
			round(shape);
		}
		part1 = maxHeight;
		heightFirstCycles = part1;
		System.out.println("Cycle:" + cycle);
		for (int i = numberToFindCycle + 1; i <= numberToFindCycle + cycle; i++) {
			Shape shape = shapes.remove();
			shapes.add(new Shape(shape.getName()));
			maxHeight = findMax();
			if (shape.getName() != "+") {
				y = maxHeight + 4;
			} else {
				y = maxHeight + 5;
			}
			Element left = new Element(-1, y);
			shape.addElements(left);
			round(shape);
		}

		long numberOfCycles = (1000000000000l - numberToFindCycle) / cycle;
		long rest = (1000000000000l - numberToFindCycle) % cycle;
		int heightAfterCycles = maxHeight;
		part2 = heightFirstCycles + numberOfCycles * (maxHeight - part1);

		for (int i = numberToFindCycle + 1 + cycle; i < numberToFindCycle + 1 + cycle + rest; i++) {
			Shape shape = shapes.remove();
			shapes.add(new Shape(shape.getName()));
			maxHeight = findMax();
			if (shape.getName() != "+") {
				y = maxHeight + 4;
			} else {
				y = maxHeight + 5;
			}
			Element left = new Element(-1, y);
			shape.addElements(left);
			round(shape);
		}
		part2 += maxHeight - heightAfterCycles;

		System.out.println(part1);
		System.out.println(part2);

	}

	public static void createShapes() {
		Shape shape1 = new Shape("-");
		Shape shape2 = new Shape("+");
		Shape shape3 = new Shape("L");
		Shape shape4 = new Shape("|");
		Shape shape5 = new Shape("#");
		shapes.add(shape1);
		shapes.add(shape2);
		shapes.add(shape3);
		shapes.add(shape4);
		shapes.add(shape5);
	}

	public static void round(Shape shape) {
		boolean rest = false;

		while (!rest) {

			Move move = movements.remove();
			Move move2 = new Move(move.getNumber(), move.getSymbol());
			movements.add(move);
			move2.setShape(shape);
			move2.setLastRows(findLastRows());

			if (!cycleFound) {
				if (moveMap.containsKey(move2)) {

					cycle = shapeCounter - moveMap.get(move2);

					cycleFound = true;
				} else {
					moveMap.put(move2, shapeCounter);
				}
			}

			if (move2.getSymbol() == '>' && shape.checkifMoveRightPossible(elements)) {
				shape.moveRight();
			}
			if (move2.getSymbol() == '<' && shape.checkIfMoveLeftPossible(elements)) {
				shape.moveLeft();
			}
			if (shape.checkIfMoveDownPossible(elements)) {
				shape.moveDown();
			} else {
				rest = true;
				addShape(shape);
			}

		}
	}

	public static ArrayList<Element> findLastRows() {
		ArrayList<Element> el = new ArrayList<Element>();
		for (int y = maxHeight; y >= maxHeight - 10; y--) {
			for (int x = -3; x <= 3; x++) {
				Element element = new Element(x, y);
				if (elements.contains(element)) {
					element.setY(maxHeight - y);
					el.add(element);
				}
			}
		}

		return el;
	}

	public static int findMax() {
		int max = 0;
		for (Element el : elements) {
			if (el.getY() > max) {
				max = el.getY();
			}
		}
		return max;
	}

	public static void addShape(Shape shape) {
		shape.getElements().forEach(el -> elements.add(el));
	}

	public static void createFloor() {
		for (int x = -3; x < 4; x++) {
			elements.add(new Element(x, 0));
		}
	}

	public static String parseFile(String fileName) throws IOException {

		Scanner scanner = new Scanner(new FileReader(fileName));
		String data = "";
		while (scanner.hasNext()) {

			data = scanner.nextLine();

		}
		scanner.close();

		return data;

	}

}
