package Day9;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day9 {

	static final Set<Position> visited1 = new HashSet<Position>();
	static final Set<Position> visited2 = new HashSet<Position>();
	static Position head_pos = new Position(0, 0); // starting head position
	static final Position tail_pos = new Position(0, 0); // starting tail position
	static final Position[] positions = new Position[9]; // head excluded

	public static void main(String[] args) throws IOException {

		int part1 = 0;
		int part2 = 0;

		// visited positions
		ArrayList<String> rawData = new ArrayList<String>();

		rawData = parseFile("src/Day9/input.txt");

		for (int i = 0; i < positions.length; i++) {
			positions[i] = new Position(0, 0);
		}

		for (String line : rawData) {
			String[] instuction = line.split(" ");
			calculateNewPos(instuction);

		}
		head_pos = new Position(0, 0);
		for (String line : rawData) {
			String[] instuction = line.split(" ");
			calculateNewPos2(instuction);

		}

		for(Position pos:visited2) {
			System.out.println(pos);
		}
		part1 = visited1.size();
		part2 = visited2.size();
		System.out.println(part1);
		System.out.println("----");
		System.out.println(part2);

	}

	public static void calculateNewPos(String[] instruction) {

		int posX = head_pos.getX();
		int posY = head_pos.getY();
		int movement = Integer.valueOf(instruction[1]);
		for (int i = 1; i <= movement; i++) {
			if (instruction[0].equals("R")) {
				head_pos.setX(posX + 1);
				posX += 1;
			}
			if (instruction[0].equals("L")) {
				head_pos.setX(posX - 1);
				posX -= 1;
			}
			if (instruction[0].equals("U")) {
				head_pos.setY(posY + 1);
				posY += 1;
			}
			if (instruction[0].equals("D")) {
				head_pos.setY(posY - 1);
				posY -= 1;
			}
			calculateNextPos(head_pos, tail_pos);
			Position pos = new Position(tail_pos.getX(), tail_pos.getY());

			visited1.add(pos); // add position of tail to visited list
		}

	}

	public static void calculateNewPos2(String[] instruction) {

		int posX = head_pos.getX();
		int posY = head_pos.getY();

		int movement = Integer.valueOf(instruction[1]);
		for (int i = 1; i <= movement; i++) {
			if (instruction[0].equals("R")) {
				head_pos.setX(posX + 1);
				posX += 1;
			}
			if (instruction[0].equals("L")) {
				head_pos.setX(posX - 1);
				posX -= 1;
			}
			if (instruction[0].equals("U")) {
				head_pos.setY(posY + 1);
				posY += 1;
			}
			if (instruction[0].equals("D")) {
				head_pos.setY(posY - 1);
				posY -= 1;
			}
			calculateNextPos(head_pos, positions[0]);
			// calculate position of every point
			for (int j = 0; j < positions.length - 1; j++) {
				calculateNextPos(positions[j], positions[j + 1]);

			}

			Position pos1=new Position(positions[8].getX(),positions[8].getY());
			visited2.add(pos1); // add position of tail to visited list
		}

	}

	// if head is two tiles further move tail
	public static void calculateNextPos(Position actualPosition, Position nextPosition) {

		int differenceX = actualPosition.getX() - nextPosition.getX();
		int differenceY = actualPosition.getY() - nextPosition.getY();

		if (differenceX == 2) {
			nextPosition.setX(actualPosition.getX() - 1);
			if (Math.abs(differenceY) < 2) {
				nextPosition.setY(actualPosition.getY());
			}

		}
		if (differenceX == -2) {
			nextPosition.setX(actualPosition.getX() + 1);
			if (Math.abs(differenceY) < 2) {
				nextPosition.setY(actualPosition.getY());
			}

		}
		if (differenceY == 2) {
			nextPosition.setY(actualPosition.getY() - 1);
			if (Math.abs(differenceX) < 2) {
				nextPosition.setX(actualPosition.getX());
			}

		}
		if (differenceY == -2) {
			nextPosition.setY(actualPosition.getY() + 1);
			if (Math.abs(differenceX) < 2) {
				nextPosition.setX(actualPosition.getX());
			}

		}

		nextPosition.setVisited(true);

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
