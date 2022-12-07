package Day7;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day7 {

	public static void main(String[] args) throws IOException {

		long part1 = 0;
		long part2 = 0;
		ArrayList<String> rawData = new ArrayList<String>();

		rawData = parseFile("src/Day7/input.txt");
		ArrayList<Directory> directories = new ArrayList<Directory>(); //list of all directories
		directories.add(new Directory("/"));
		Directory actual = directories.get(0); // directory we're in

		// parse the data
		for (String line : rawData) {
			String[] splitted = line.split(" ");

			if (line.contains("$ cd") && !splitted[2].equals("/")) {

				if (splitted[2].equals("..")) {
					actual = findOuterDirectory(actual, directories);
				} else {
					actual = actual.findDirectory(splitted[2]);

				}

			}
			if (!line.contains("$")) {
				if (line.contains("dir")) {

					Directory dir = new Directory(splitted[1]);
					actual.addElement(dir);
					directories.add(dir);
				} else {
					File file = new File(splitted[1]);
					file.setSize(Integer.valueOf(splitted[0]));
					actual.addElement(file);
				}

			}
		}
		long spaceused = directories.get(0).calculateSize();
		long spaceToFreeUp = spaceused - 40000000;
		part2 = spaceused;

		for (Directory dir : directories) {
			long size = dir.calculateSize();
			if (size <= 100000) {
				part1 += size;
			}
			if (size >= spaceToFreeUp && size < part2) {
				part2 = size;
			}

		}

		System.out.println(part1);
		System.out.println("-----");
		System.out.println(part2);

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

	public static Directory findOuterDirectory(Directory actual, ArrayList<Directory> directories) {

		for (Directory dir : directories) {
			if (dir.checkIfDirectoryExists(actual))
				return dir;
		}
		return actual;

	}
}
