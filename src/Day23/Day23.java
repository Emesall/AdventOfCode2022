package Day23;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Day23 {

	static Board board = new Board();
	static LinkedList<Character> moves = new LinkedList<Character>(Arrays.asList('N', 'S', 'W', 'E'));
	static boolean noMove = false;

	public static void main(String[] args) throws IOException {

		ArrayList<String> rawData = new ArrayList<String>();

		int part1 = 0;
		int part2 = 10;

		rawData = parseFile("src/Day23/input.txt");

		for (int i = 0; i < rawData.size(); i++) {
			addElements(rawData.get(i), i);
		}
		
		for (int i = 0; i < 10; i++) {

			round();
			move();

		}
		part1 = board.calculateEmptyPoints();
		while (!noMove) {
			round();
			move();
			part2++;
		}

	
		System.out.println(part1);
		System.out.println("-----");
		System.out.println(part2);

	}

	public static void move() {
		char move = moves.remove();
		moves.add(move);

	}

	public static void round() {
		int counter = 0;
		ArrayList<Element> proposedMoves = new ArrayList<Element>();
		// calculate proposed moves
		for (Element elf : board.getElves()) {
			elf.setMoves(new LinkedList<Character>(moves));
			if (board.checkIfHasAdjacents(elf)) {
				char move = elf.getMove();
				while (!board.checkIfValidAndMove(elf, move) && counter < moves.size() - 1) {

					counter++;
					move = elf.getMove();
				}
			}
			if (elf.getProposedMove() != null) {
				proposedMoves.add(elf.getProposedMove());
			}

			counter = 0;
		}
		// check proposed moves

		if (proposedMoves.isEmpty())
			noMove = true;

		for (Element elf : board.getElves()) {
			Element proposedMove = elf.getProposedMove();
			if (proposedMoves.stream().filter(el -> el.equals(proposedMove)).count() == 1) {
				elf.setX(proposedMove.getX());
				elf.setY(proposedMove.getY());
			}
			elf.setProposedMove(null);
		}

	}

	public static void addElements(String line, int row) {

		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			if (c == '#') {
				Element elf = new Element(i, row);
				board.addElf(elf);
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
