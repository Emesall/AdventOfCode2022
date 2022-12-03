package Day3;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day3 {

	public static void main(String[] args) throws IOException {

		int part1 = 0;
		int part2 = 0;
		ArrayList<Character> repeated = new ArrayList<Character>();
		ArrayList<String> rawData = new ArrayList<String>();
		rawData = parseFile("src/Day3/input.txt");

		for (String line : rawData) {
			String[] words = divideIntoTwo(line);
			 char symbol = findSymbol(words[0], words[1]);
			 part1 += calculateScore(symbol);

		}

		for (int i = 0; i < rawData.size(); i = i + 3) {
			 char symbol = findSymbol(rawData.get(i), rawData.get(i+1),rawData.get(i+2));
			 part2 += calculateScore(symbol);

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

	// divide array to two,and return two arrays of char
	public static String[] divideIntoTwo(String line) {
		// length of string
		int length = line.length() / 2;
		String[] array = new String[2];
		array[0] = line.substring(0, length);
		array[1] = line.substring(length);
		return array;
	}

	public static char findSymbol(String word1, String word2) {

		Set<Character> set1 = new HashSet<>();
		Set<Character> set2 = new HashSet<>();

		for (char c : word1.toCharArray()) {
			set1.add(c);

		}
		
		for (char c : word2.toCharArray()) {
			set2.add(c);

		}
		set1.retainAll(set2);
		
		return set1.toArray(new Character[0])[0];
	}

	public static char findSymbol(String word1, String word2, String word3) {

		Set<Character> set1 = new HashSet<>();
		Set<Character> set2 = new HashSet<>();
		Set<Character> set3 = new HashSet<>();

		for (char c : word1.toCharArray()) {
			set1.add(c);

		}
		
		for (char c : word2.toCharArray()) {
			set2.add(c);

		}
		
		for (char c : word3.toCharArray()) {
			set3.add(c);

		}
		set1.retainAll(set2);
		set1.retainAll(set3);		
		
		return set1.toArray(new Character[0])[0];
	}

	public static int calculateScore(char c) {

		// ascii code of char
		int value = (int) c;
		// lowercase
		if (value > 97) {
			return c - 96; // a==1
		}
		// uppercase
		else {
			return c - 38; // A=27

		}

	}

}
