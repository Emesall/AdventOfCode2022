package Day10;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day10 {

	public static void main(String[] args) throws IOException {

		int part1 = 0;
		int registerX = 1;
		int cycle_number = 1;
		int crt_pos = 0;
		Map<Integer, Integer> values = new HashMap<>(); // key=number of cycle, value=value of register X

		ArrayList<String> rawData = new ArrayList<String>();
		rawData = parseFile("src/Day10/input.txt");

		for (String line : rawData) {
			String[] instruction = line.split(" ");
			cycle_number += 1;
			values.put(cycle_number, registerX);

			if (Math.abs(crt_pos - registerX) <= 1) {
				System.out.print("#");
			} else {
				System.out.print(".");
			}

			crt_pos += 1;
			if (crt_pos % 40 == 0) {
				crt_pos = 0;
				System.out.println();
			}
			if (instruction[0].equals("addx")) {

				if (Math.abs(crt_pos - registerX) <= 1) {
					System.out.print("#");
				} else {
					System.out.print(".");
				}
				crt_pos += 1;
				cycle_number += 1;

				registerX += Integer.valueOf(instruction[1]);
				values.put(cycle_number, registerX);

				if (crt_pos % 40 == 0) {
					crt_pos = 0;
					System.out.println();
				}

			}

		}
		part1 = calculatePart1(values);
		System.out.println();
		System.out.println(part1);

	}

	public static int calculatePart1(Map<Integer, Integer> values) {
		int result = 0;

		for (int i = 20; i <= 220; i = i + 40) {
			result += values.get(i) * i;
		}

		return result;
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
