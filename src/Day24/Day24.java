package Day24;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day24 {

	static Board board = new Board();
	static int cycle = 0;

	public static void main(String[] args) throws IOException {

		ArrayList<String> rawData = new ArrayList<String>();

		int part1 = 0;
		int cycle = 0;

		rawData = parseFile("src/Day24/test.txt");

		for (int i = 1; i < rawData.size() - 1; i++) {
			addElements(rawData.get(i), i);
		}
		Element finPoint = new Element(rawData.get(0).length() - 2, rawData.size() - 1);
		board.setFinishingPoint(finPoint);

		cycle = (rawData.get(0).length() - 2) * (rawData.size() - 2);
		for (int i = 0; i < cycle - 1; i++) {
			round();
			board.addState(i);
		}
		System.out.println(board.getStates().size());

	}

	public static void round() {
		board.blizzardMove();

	}

	public static void addElements(String line, int row) {

		for (int i = 1; i < line.length() - 1; i++) {
			char c = line.charAt(i);

			Element el = new Element(i, row);
			el.addSymbol(c);
			board.addVertex(el);

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
