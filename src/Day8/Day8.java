package Day8;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day8 {

	public static void main(String[] args) throws IOException {

		int part2 = 0;
		int[][] matrix = parseFile("src/Day8/input.txt");
		int part1 = matrix.length * 2 + 2 * (matrix.length - 2); // count edges
		for (int i = 1; i < matrix.length - 1; i++) {
			for (int j = 1; j < matrix[0].length - 1; j++) {

				int actual = matrix[i][j];
				// get column as array
				int[] column = new int[matrix.length];
				for (int k = 0; k < column.length; k++) {
					column[k] = matrix[k][j];
				}
				// check visibility:left,right,top,down
				int[] left = Arrays.copyOfRange(matrix[i], 0, j);
				int[] right = Arrays.copyOfRange(matrix[i], j + 1, matrix[0].length);
				int[] top = Arrays.copyOfRange(column, 0, i);
				int[] down = Arrays.copyOfRange(column, i + 1, matrix.length);
				if (isVisible(actual, left) || isVisible(actual, right) || isVisible(actual, top)
						|| isVisible(actual, down))
					part1 += 1;
				int score = countLeftAndTop(actual, left) * countRightAndDown(actual, right)
						* countLeftAndTop(actual, top) * countRightAndDown(actual, down);
				if (score > part2) {
					part2 = score;
				}

			}

		}
		System.out.println(part1);
		System.out.println(part2);
	}

	public static boolean isVisible(int number, int[] subarray) {


		for (int i : subarray) {

			if (i >= number)

				return false;
		}

		return true;
	}

	public static int countLeftAndTop(int number, int[] subarray) {

		int score = 0;
		
		for (int i = subarray.length - 1; i >= 0; i--) {
			if (subarray[i] >= number) {
				score += 1;
				break;
			}
			score += 1;
		}

		return score;
	}

	public static int countRightAndDown(int number, int[] subarray) {

		int score = 0;

		for (int i = 0; i < subarray.length; i++) {

			if (subarray[i] >= number) {
				score += 1;

				break;
			}

			score += 1;
		}

		return score;
	}

	public static int[][] parseFile(String fileName) throws IOException {

		Scanner scanner = new Scanner(new FileReader(fileName));
		ArrayList<String> data = new ArrayList<String>();

		while (scanner.hasNext()) {

			data.add(scanner.nextLine());

		}

		scanner.close();
		int[][] matrix = new int[data.size()][];
		for (int i = 0; i < data.size(); i++) {
			char[] tab = data.get(i).toCharArray();

			matrix[i] = charToIntArray(tab);

		}

		return matrix;

	}

	private static int[] charToIntArray(char[] array) {
		int[] tab = new int[array.length];

		for (int i = 0; i < array.length; i++) {
			tab[i] = Integer.valueOf(String.valueOf(array[i]));
		}
		return tab;
	}

}
