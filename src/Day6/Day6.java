package Day6;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day6 {

	public static void main(String[] args) throws IOException {

		int part1 = 0;
		int part2 = 0;
		String rawData = parseFile("src/Day6/input.txt");

		for (int i = 0; i < rawData.length(); i++) {

			String substring = rawData.substring(i, i + 4);
			if (!isRepeated(substring)) {
				part1 = i + 4;
				break;
			}
			

		}
		
		for (int i = 0; i < rawData.length(); i++) {

			String substring = rawData.substring(i, i + 14);
			if (!isRepeated(substring)) {
				part2 = i + 14;
				break;
			}
			

		}
		System.out.println(part1);
		System.out.println(part2);

	}

	public static String parseFile(String fileName) throws IOException {

		Scanner scanner = new Scanner(new FileReader(fileName));
		String rawData = "";
		while (scanner.hasNext()) {

			rawData = scanner.nextLine();

		}
		scanner.close();

		return rawData;

	}

	public static boolean isRepeated(String line) {
		return line.length() != line.chars().mapToObj(c -> c).collect(Collectors.toSet()).size();

	}
}
