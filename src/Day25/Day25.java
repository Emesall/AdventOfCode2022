package Day25;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day25 {

	public static void main(String[] args) throws IOException {

		ArrayList<String> rawData = new ArrayList<String>();
		Converter converter = new Converter();
		long part1 = 0;

		rawData = parseFile("src/Day25/input.txt");
		for (String line : rawData) {
			part1 += converter.convertToDecimal(line);
		}
		System.out.println(part1);
		System.out.println(converter.convertToFiveimal(part1));
		
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
