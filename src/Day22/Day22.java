package Day22;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day22 {

	static Board board = new Board();

	public static void main(String[] args) throws IOException {
		String instruction;

		ArrayList<String> rawData = new ArrayList<String>();

		int part1 = 0;
		int part2 = 0;

		rawData = parseFile("src/Day22/input.txt");
		// System.out.println(rawData);
		instruction = rawData.get(rawData.size() - 1);
		for (int i = 0; i < rawData.size() - 2; i++) {
			addElements(rawData.get(i), i + 1);
		}
		// System.out.println(instruction);
		// System.out.println(elements);
		// draw();
		board.setActualPos(board.getElements().get(0));
		part1(instruction);
		part1 = 1000 * board.getActualPos().getY() + 4 * board.getActualPos().getX() + board.getDirection();
		System.out.println(part1);

	}

	public static void part1(String instr) {

		char[] tab = instr.toCharArray();
		String num = "";
		for (int i = 0; i < tab.length; i++) {
			char c = tab[i];
			if (c <= 57) {
				num = num + c;
			} else {

				board.move(Integer.valueOf(num));
				board.rotate(c);
				num = "";
			}

		}

	}

	public static void addElements(String line, int row) {
		Element el;
		boolean isWall = false;
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			if (c == '#')
				isWall = true;
			if (c != ' ') {
				el = new Element(i + 1, row, isWall);
				board.addElement(el);
			}
			isWall = false;
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
