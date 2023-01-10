package Day19;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day19 {

	public static void main(String[] args) throws IOException {

		ArrayList<String> rawData = new ArrayList<String>();
		ArrayList<Blueprint> blueprints = new ArrayList<Blueprint>();
		int part1 = 0;
		int part2 = 0;

		rawData = parseFile("src/Day19/test.txt");
		rawData.forEach(s -> blueprints.add(parseBluePrint(s)));
		//System.out.println(blueprints);
		part1=calculateOpenedGeodes(24, blueprints.get(0));
		System.out.println(part1);
	}

	public static int calculateOpenedGeodes(int timeLimit, Blueprint blueprint) {
		int result = 0;
		Resources availableResources = new Resources(0, 0, 0);
		// number of all available robots
		int oreRobots = 1;
		int clayRobots = 0;
		int obsidianRobots = 0;
		int geodeRobots = 0;

		for (int i = 0; i < timeLimit; i++) {

			availableResources.addOre(oreRobots);
			availableResources.addClay(clayRobots);
			availableResources.addObsidian(obsidianRobots);
			result += geodeRobots;

			if (availableResources.checkIfEnoughResources(blueprint.getCostOfGeodeRobot())) {
				availableResources.buildRobot(blueprint.getCostOfGeodeRobot());
				geodeRobots++;
			}
			if (availableResources.checkIfEnoughResources(blueprint.getCostOfObsidianRobot())) {
				availableResources.buildRobot(blueprint.getCostOfObsidianRobot());
				obsidianRobots++;
			}
			if (availableResources.checkIfEnoughResources(blueprint.getCostOfClayRobot())) {
				availableResources.buildRobot(blueprint.getCostOfClayRobot());
				clayRobots++;
				
			}
			if (availableResources.checkIfEnoughResources(blueprint.getCostOfOreRobot())) {
				availableResources.buildRobot(blueprint.getCostOfOreRobot());
				oreRobots++;
			}

		}


		return result;
	}

	public static Blueprint parseBluePrint(String line) {
		Blueprint blueprint = new Blueprint();
		Pattern pattern1 = Pattern.compile(
				".*Each ore robot costs (\\d+) ore. Each clay robot costs (\\d+) ore. Each obsidian robot costs (\\d+) ore and (\\d+) clay. Each geode robot costs (\\d+) ore and (\\d+) obsidian.");
		Matcher matcher = pattern1.matcher(line);
		if (matcher.matches()) {
			// ore
			int ore = Integer.valueOf(matcher.group(1));
			Resources resources = new Resources(ore, 0, 0);
			blueprint.setCostOfOreRobot(resources);
			// clay
			int clay = Integer.valueOf(matcher.group(2));
			resources = new Resources(0, clay, 0);
			blueprint.setCostOfClayRobot(resources);
			// obsidian
			ore = Integer.valueOf(matcher.group(3));
			clay = Integer.valueOf(matcher.group(4));
			resources = new Resources(ore, clay, 0);
			blueprint.setCostOfObsidianRobot(resources);
			// geode
			ore = Integer.valueOf(matcher.group(5));
			int obsidian = Integer.valueOf(matcher.group(6));
			resources = new Resources(ore, 0, obsidian);
			blueprint.setCostOfGeodeRobot(resources);
		}

		return blueprint;
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
