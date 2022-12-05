package Day5;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day5 {

	public static void main(String[] args) throws IOException {

		String part1 = "";
		String part2 = "";
		Map<Integer, Deque<Character>> stacks;
		ArrayList<char[]> stacksLines = new ArrayList<char[]>();

		ArrayList<Instruction> instructions = new ArrayList<Instruction>();
		Scanner scanner = new Scanner(new FileReader("src/Day5/input.txt"));

		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			if (!line.contains("move")) {
				line = line.replaceAll("[^A-Z]", " ");
				stacksLines.add(line.toCharArray());

			} else {
				instructions.add(generateInstr(line));
			}

		}
		stacks = generateStacks(stacksLines);
		// part1
		rearrangeStacks(stacks, instructions, true);
		 part1 = result(stacks);

		stacks = generateStacks(stacksLines);
		// part2
		rearrangeStacks(stacks, instructions, false);
		part2 = result(stacks);
		System.out.println(part1);
		System.out.println("-----");
		System.out.println(part2);

	}

	public static String result(Map<Integer, Deque<Character>> stacks) {
		String result = "";
		for (Deque<Character> value : stacks.values()) {
			result += value.peek();
		}
		return result;
	}

	// create map of all stacks (key=number of stack,value=stack) from array of
	// chars
	public static Map<Integer, Deque<Character>> generateStacks(ArrayList<char[]> stacksLines) {
		Map<Integer, Deque<Character>> stacks = new HashMap<Integer, Deque<Character>>();
		Deque<Character> stack;
		int number = 1;
		for (int j = 0; j < stacksLines.get(0).length; j++) {
			stack = new ArrayDeque<Character>();
			for (int i = stacksLines.size() - 1; i >= 0; i--) {

				char c = stacksLines.get(i)[j];
				if (Character.isLetter(c)) {

					stack.push(c);

				}
			}
			if (!stack.isEmpty()) {
				stacks.put(number, stack);
				number += 1;
			}

		}

		return stacks;

	}

	// create instruction from string
	public static Instruction generateInstr(String instr) {
		// replace all non digits symbols with space
		instr = instr.replaceAll("[^0-9]+", " ");
		String[] number = instr.split(" ");
		// number[0] is space
		int amountToMove = Integer.valueOf(number[1]);
		int source = Integer.valueOf(number[2]);
		int dest = Integer.valueOf(number[3]);
		Instruction instruction = new Instruction(amountToMove, source, dest);
		return instruction;
	}

	public static void rearrangeStacks(Map<Integer, Deque<Character>> stacks, ArrayList<Instruction> instructions,
			boolean part1) {

		for (Instruction instr : instructions) {
			int amountToMove = instr.getAmountToMove();
			int source = instr.getSource();
			int dest = instr.getDest();
			// move every letters
			if (part1) {
				for (int i = 1; i <= amountToMove; i++) {
					char deleted = stacks.get(source).pop();
					stacks.get(dest).push(deleted);
				}
			} else {
				char[] deleted = new char[amountToMove];
				for (int i = 1; i <= amountToMove; i++) {

					deleted[i - 1] = stacks.get(source).pop();

				}
				for (int i = deleted.length - 1; i >= 0; i--) {
					stacks.get(dest).push(deleted[i]);
				}

			}

		}

	}

}
