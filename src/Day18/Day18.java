package Day18;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day18 {

	static ArrayList<Cube> cubes = new ArrayList<Cube>();
	static Extremum X = new Extremum(0, 0);
	static Extremum Y = new Extremum(0, 0);
	static Extremum Z = new Extremum(0, 0);

	public static void main(String[] args) throws IOException {

		ArrayList<String> rawData = new ArrayList<String>();

		int part1 = 0;
		int part2 = 0;

		rawData = parseFile("src/Day18/input.txt");

		rawData.forEach(line -> addCube(line));
		cubes.forEach(cube -> addExposed(cube));
		findMaxs();
		part1 = countExposedSides();
		deleteTrapped();
		part2 = countExposedSides();
		System.out.println(part1);
		System.out.println("-----");
		System.out.println(part2);
	}

	public static void addCube(String line) {
		String[] coords = line.split(",");
		int x = Integer.valueOf(coords[0]);
		int y = Integer.valueOf(coords[1]);
		int z = Integer.valueOf(coords[2]);
		Cube cube = new Cube(x, y, z);
		cubes.add(cube);
	}

	public static int countExposedSides() {
		int result = 0;

		for (Cube cube : cubes) {
			result += cube.getExposed().size();
		}

		return result;
	}

	public static void deleteTrapped() {

		for (Cube cube : cubes) {
			ArrayList<Cube> exposed = cube.getExposed();
			ArrayList<Cube> exposedCopy = new ArrayList<Cube>(exposed);
			for (Cube c : exposedCopy) {
				if (isTrapped(c, new ArrayList<Cube>())) {
					exposed.remove(c);
				}
			}
		}

	}

	public static boolean isTrapped(Cube cube, ArrayList<Cube> visited) {

		int x = cube.getX();
		int y = cube.getY();
		int z = cube.getZ();
		visited.add(cube);
		Cube right = new Cube(x + 1, y, z);
		Cube left = new Cube(x - 1, y, z);
		Cube front = new Cube(x, y, z - 1);
		Cube rear = new Cube(x, y, z + 1);
		Cube top = new Cube(x, y + 1, z);
		Cube bottom = new Cube(x, y - 1, z);

		if (isOutOfBorder(x, y, z))
			return false;
		if (!cubes.contains(right) && !visited.contains(right)) {
			if (!isTrapped(right, visited))
				return false;

		}
		if (!cubes.contains(left) && !visited.contains(left))
			if (!isTrapped(left, visited))
				return false;

		if (!cubes.contains(front) && !visited.contains(front))
			if (!isTrapped(front, visited))
				return false;

		if (!cubes.contains(bottom) && !visited.contains(bottom))
			if (!isTrapped(bottom, visited))
				return false;

		if (!cubes.contains(rear) && !visited.contains(rear))
			if (!isTrapped(rear, visited))
				return false;

		if (!cubes.contains(top) && !visited.contains(top))
			if (!isTrapped(top, visited))
				return false;

		return true;
	}

	public static boolean isOutOfBorder(int x, int y, int z) {
		if (x > X.getMax() || y > Y.getMax() || z > Z.getMax() || x < X.getMin() || y < Y.getMin() || z < Z.getMin())
			return true;
		return false;
	}

	public static void findMaxs() {

		for (Cube cube : cubes) {
			int x = cube.getX();
			int y = cube.getY();
			int z = cube.getZ();
			if (x > X.getMax())
				X.setMax(x);
			if (y > Y.getMax())
				Y.setMax(y);
			if (z > Z.getMax())
				Z.setMax(z);
			if (x < X.getMin())
				X.setMin(x);
			if (y < Y.getMin())
				Y.setMin(y);
			if (z < Z.getMin())
				Z.setMin(z);

		}
	}

	public static void addExposed(Cube cube) {
		int x = cube.getX();
		int y = cube.getY();
		int z = cube.getZ();
		Cube right = new Cube(x + 1, y, z);
		Cube left = new Cube(x - 1, y, z);
		Cube front = new Cube(x, y, z - 1);
		Cube rear = new Cube(x, y, z + 1);
		Cube top = new Cube(x, y + 1, z);
		Cube bottom = new Cube(x, y - 1, z);
		if (!cubes.contains(right))
			cube.addExposed(right);
		if (!cubes.contains(left))
			cube.addExposed(left);
		if (!cubes.contains(front))
			cube.addExposed(front);
		if (!cubes.contains(bottom))
			cube.addExposed(bottom);
		if (!cubes.contains(rear))
			cube.addExposed(rear);
		if (!cubes.contains(top))
			cube.addExposed(top);

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
