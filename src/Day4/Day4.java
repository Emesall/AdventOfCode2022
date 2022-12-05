package Day4;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day4 {

	public static void main(String[] args) throws IOException {

		int part1 = 0;
		int part2 = 0;
		ArrayList<String> rawData = new ArrayList<String>();
		ArrayList<Section> sections = new ArrayList<Section>();
		rawData = parseFile("src/Day4/input.txt");

		for (String line : rawData) {
			String[] tab = line.split(",");
			Section sec1 = createSection(tab[0]);
			Section sec2 = createSection(tab[1]);
			sections.add(sec1);
			sections.add(sec2);
		}

		for (int i = 0; i < sections.size(); i = i + 2) {
			if (sections.get(i).contains(sections.get(i + 1)) || sections.get(i + 1).contains(sections.get(i)))
				part1 += 1;
			if (sections.get(i).overlaps(sections.get(i + 1)) || sections.get(i + 1).overlaps(sections.get(i)))
				part2 += 1;
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

	public static Section createSection(String line) {
		String[] splitted = line.split("-");

		return new Section(Integer.valueOf(splitted[0]), Integer.valueOf(splitted[1]));

	}
}
