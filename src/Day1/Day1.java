package Day1;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day1 {

	public static void main(String[] args) throws IOException {
		int result = 0;
		int max=0;
		int part2=0;
		ArrayList<Integer> elves = new ArrayList<Integer>();
		Scanner scanner = new Scanner(new FileReader("src/Day1/input.txt"));
		while (scanner.hasNext()) {
			String line = scanner.nextLine();

			if (!line.isEmpty()) {

				result += Integer.valueOf(line);

			} else {
				elves.add(result);
				result = 0;

			}
		}
		elves.add(result);
		
		max=max(elves);
		part2=top3(elves);
		System.out.println(max);
		System.out.println("----");
		System.out.println(part2);
		scanner.close();

	}

	private static int max(ArrayList<Integer> array) {
		int max = array.get(0);
		for (Integer element : array) {
			if (element > max)
				max = element;
		}

		return max;
	}
	
	private static int top3(ArrayList<Integer> array) {
		array.sort(Collections.reverseOrder());
		int top3=0;
		top3=array.get(0)+array.get(1)+array.get(2);
		return top3;
	}

}
