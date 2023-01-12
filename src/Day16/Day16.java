package Day16;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day16 {

	static ArrayList<Valve> valves = new ArrayList<Valve>();
	static List<Valve> nonZeroValves = new ArrayList<Valve>();
	static ArrayList<Integer> pressures = new ArrayList<Integer>();
	static Map<List<Valve>, Integer> combinations = new HashMap<List<Valve>, Integer>();

	public static void main(String[] args) throws IOException {

		ArrayList<String> rawData = new ArrayList<String>();

		int part1 = 0;
		int part2 = 0;

		rawData = parseFile("src/Day16/input.txt");
		rawData.forEach(s -> parseValve(s));
		nonZeroValves = valves.stream().filter(v -> v.getFlow() != 0).toList();
		findMaxPressure(new ArrayList<Valve>(), 0, "AA", 0, null);
		findMaxPressure2(new ArrayList<Valve>(), 0, "AA", 0, null);
		part1=findMax(pressures);
		part2 = findMax2(combinations);
		System.out.println(part1);
		System.out.println("-----");
		System.out.println(part2);

	}

	public static int findMax2(Map<List<Valve>, Integer> combinations) {

		ArrayList<Integer> pressures = new ArrayList<Integer>();
		Map<List<Valve>, Integer> temp = new HashMap<List<Valve>, Integer>(combinations);
		int max = 0;
		for (List<Valve> v1 : combinations.keySet()) {
			int pressure1 = combinations.get(v1);
	
			temp.remove(v1);
			
			for (List<Valve> v2 : temp.keySet()) {
				int pressure2 = combinations.get(v2);
				if (pressure1 + pressure2 > max) {
					if (allElementsDifferent(v1, v2)) {
						pressures.add(pressure1 + pressure2);
						max = findMax(pressures);
					}
				}

			}
		}

		return findMax(pressures);
	}

	public static boolean allElementsDifferent(List<Valve> list1, List<Valve> list2) {

		return list1.stream().filter(v -> list2.contains(v)).findAny().isEmpty();

	}

	public static int findMax(List<Integer> pressures) {
		int max = 0;
		for (int i : pressures) {
			if (i > max)
				max = i;
		}
		return max;
	}

	
	public static void findMaxPressure2(List<Valve> opened, int pressure, String current_name, int minutes,
			String previous_name) {

		Valve current = findByName(current_name);
		if (previous_name != null) {
			Valve previous = findByName(previous_name);
			// calculate minutes to travel and open valve
			minutes = minutes + 1 + findShortestPath(previous, current);
		}
		if (minutes < 26) {
			// get pressure of current valve
			pressure += current.getFlow() * (26 - minutes);
			opened = new ArrayList<Valve>(opened);
			// set valve as opened
			if (!current.getName().equals("AA")) {
				opened.add(current);

			}
		}
		

		// every valve is opened or time is up
		if (opened.size() >= nonZeroValves.size() / 2 || minutes >= 26) {
			opened.sort(new Valve().new NameComparator());
			if (combinations.containsKey(opened)) {
				if (pressure > combinations.get(opened)) {
					combinations.put(opened, pressure);
				}
			} else {
				combinations.put(opened, pressure);
			}

		} else {
			// iterate through every valve
			for (Valve v : nonZeroValves) {

				// if it's not opened yet
				if (!opened.contains(v)) {

					findMaxPressure2(opened, pressure, v.getName(), minutes, current.getName());

				}
			}
		}

	}

	public static void findMaxPressure(List<Valve> opened, int pressure, String current_name, int minutes,
			String previous_name) {

		Valve current = findByName(current_name);
		if (previous_name != null) {
			Valve previous = findByName(previous_name);
			// calculate minutes to travel and open valve
			minutes = minutes + 1 + findShortestPath(previous, current);
		}

		if (minutes < 30) {
			// get pressure of current valve
			pressure += current.getFlow() * (30 - minutes);
			opened = new ArrayList<Valve>(opened);
			// set valve as opened
			if (!current.getName().equals("AA")) {
				opened.add(current);

			}
		}
		// every valve is opened or time is up
		if (opened.size() == nonZeroValves.size() || minutes >= 30) {
			pressures.add(pressure);

		} else {
			// iterate through every valve
			for (Valve v : nonZeroValves) {

				// if it's not opened yet
				if (!opened.contains(v)) {

					findMaxPressure(opened, pressure, v.getName(), minutes, current.getName());

				}
			}
		}

	}

	// bfs function to find shortestpath, weight is 1 for all nodes

	public static int findShortestPath(Valve v1, Valve v2) {

		Deque<Valve> queue = new ArrayDeque<Valve>();
		// store visited nodes
		ArrayList<Valve> visited = new ArrayList<Valve>();
		// store distance to every node
		Map<Valve, Integer> distance = new HashMap<Valve, Integer>();

		// set distances to all nodes as infinite, starting Node as 0
		for (Valve v : valves) {
			if (!v.equals(v1)) {
				distance.put(v, Integer.MAX_VALUE);
			} else {
				distance.put(v, 0);
			}
		}
		queue.add(v1);
		while (!queue.isEmpty()) {
			// take first el from the queue
			Valve valve = queue.poll();
			int current_dis = distance.get(valve);
			// add it to visited
			visited.add(valve);
			// if it isn't our destination
			if (!valve.equals(v2)) {
				// add all adjacents to queue and set their distance to +1
				for (Valve v : valve.getAdjacents()) {
					if (!visited.contains(v)) {
						distance.put(v, current_dis + 1);
						queue.add(v);
					}
				}
			} else {
				return current_dis;
			}

		}

		return 0;
	}

	public static void parseValve(String line) {
		Deque<Valve> queue = new ArrayDeque<Valve>();

		Pattern pattern = Pattern.compile("([A-Z][A-Z])");
		Pattern pattern2 = Pattern.compile("(\\d+)");
		Matcher matcher = pattern.matcher(line);
		Matcher matcher2 = pattern2.matcher(line);
		while (matcher.find()) {
			String name = matcher.group();
			Valve valve = new Valve(name);
			if (valves.contains(valve)) {
				valve = findByName(name);

			} else {
				valves.add(valve);
			}

			queue.add(valve);

		}

		Valve valve = queue.poll();
		if (matcher2.find()) {
			valve.setFlow(Integer.valueOf(matcher2.group()));
		}
		while (!queue.isEmpty()) {
			valve.addAdjacent(queue.poll());
		}

	}

	public static Valve findByName(String name) {
		return valves.stream().filter(valve -> valve.getName().equals(name)).findFirst().get();
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
