package Day19;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day19 {

	static int max = 0;

	public static void main(String[] args) throws IOException {

		ArrayList<String> rawData = new ArrayList<String>();
		ArrayList<Blueprint> blueprints = new ArrayList<Blueprint>();
		int part1 = 0;
		int part2 = 0;
		int counter = 0;

		rawData = parseFile("src/Day19/input.txt");
		rawData.forEach(s -> blueprints.add(parseBluePrint(s)));
		// System.out.println(blueprints);
		Resources availableResources = new Resources(0, 0, 0);
		State state = new State(0, availableResources, 1, 0, 0, 0, 0);
		for (Blueprint b : blueprints) {
			//calculateOpenedGeodes(b, state, 24);
			counter++;
			part1 += counter * max;
			//System.out.println(counter);
			max = 0;

		}

		for (Blueprint b : blueprints) {
			calculateOpenedGeodes(b, state, 32);
			System.out.println(max);
			max = 0;

		}

		System.out.println(part1);
		System.out.println("-----");
		System.out.println(part2);
	}

	public static void calculateOpenedGeodes(Blueprint blueprint, State state, int limit) {

		// state
		int result = state.result;
		int oreRobots = state.getOreRobots();
		int clayRobots = state.getClayRobots();
		int obsidianRobots = state.getObsidianRobots();
		int geodeRobots = state.getGeodeRobots();
		int time = state.getTime();
		// we don't need more robots than maximal amount of resource needed to build
		// robot cause we can build robot at the time
		int maxOreRobotsNeeded = maxOre(blueprint);
		int maxClayRobotsNeeded = blueprint.getCostOfObsidianRobot().getClay();
		int maxObsidianRobotsNeeded = blueprint.getCostOfGeodeRobot().getObsidian();
		Resources availableResources = state.getResources().clone();

		// check all possibilities to make robots;
		boolean checkGeode = availableResources.checkIfEnoughResources(blueprint.getCostOfGeodeRobot());
		boolean checkObsidian = availableResources.checkIfEnoughResources(blueprint.getCostOfObsidianRobot())
				&& obsidianRobots < maxObsidianRobotsNeeded && availableResources
						.getObsidian() < (limit - time) * maxObsidianRobotsNeeded - obsidianRobots * (limit - time - 1);
		boolean checkClay = availableResources.checkIfEnoughResources(blueprint.getCostOfClayRobot())
				&& clayRobots < maxClayRobotsNeeded && availableResources
						.getClay() < (limit - time) * maxClayRobotsNeeded - clayRobots * (limit - time - 1);
		boolean checkOre = availableResources.checkIfEnoughResources(blueprint.getCostOfOreRobot())
				&& oreRobots < maxOreRobotsNeeded
				&& availableResources.getOre() < (limit - time) * maxOreRobotsNeeded - oreRobots * (limit - time - 1);

		// increase available resources
		availableResources.addOre(oreRobots);
		availableResources.addClay(clayRobots);
		availableResources.addObsidian(obsidianRobots);
		result += geodeRobots;
		time++;

		// iterate through every state
		if (time < limit && calculatePossibleGeodes(limit - time + 1, geodeRobots, result) > max) {
			// if enough resources to build geodeRobot

			if (checkGeode) {

				Resources res = availableResources.clone();
				res.buildRobot(blueprint.getCostOfGeodeRobot());
				int g = geodeRobots + 1;
				calculateOpenedGeodes(blueprint, new State(time, res, oreRobots, clayRobots, obsidianRobots, g, result),
						limit);
			} else if (time <= limit - 1) {
				if (checkObsidian) {
					Resources res = availableResources.clone();
					res.buildRobot(blueprint.getCostOfObsidianRobot());
					int o = obsidianRobots + 1;
					calculateOpenedGeodes(blueprint,
							new State(time, res, oreRobots, clayRobots, o, geodeRobots, result), limit);
				}
				if (checkClay) {
					Resources res = availableResources.clone();
					res.buildRobot(blueprint.getCostOfClayRobot());
					int c = clayRobots + 1;
					calculateOpenedGeodes(blueprint,
							new State(time, res, oreRobots, c, obsidianRobots, geodeRobots, result), limit);

				}
				if (checkOre) {
					Resources res = availableResources.clone();
					res.buildRobot(blueprint.getCostOfOreRobot());
					int o = oreRobots + 1;
					calculateOpenedGeodes(blueprint,
							new State(time, res, o, clayRobots, obsidianRobots, geodeRobots, result), limit);
				}

				calculateOpenedGeodes(blueprint,
						new State(time, availableResources, oreRobots, clayRobots, obsidianRobots, geodeRobots, result),
						limit);

			}

		} else {
			if (result > max) {
				// System.out.println(max);
				max = result;
			}
		}

	}

	private static int calculatePossibleGeodes(int timeLeft, int geodeRobots, int geode) {
		int result = 0;
		for (int i = timeLeft; i > 0; i--) {
			result += timeLeft * geodeRobots;
			geodeRobots++;
		}

		result += geode;
		return result;
	}

	private static int maxOre(Blueprint blueprint) {
		int max = blueprint.getCostOfOreRobot().getOre();

		int ore2 = blueprint.getCostOfClayRobot().getOre();
		int ore3 = blueprint.getCostOfObsidianRobot().getOre();
		int ore4 = blueprint.getCostOfGeodeRobot().getOre();

		if (ore2 > max)
			max = ore2;
		if (ore3 > max)
			max = ore3;
		if (ore4 > max)
			max = ore4;
		return max;
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
			ore = Integer.valueOf(matcher.group(2));
			resources = new Resources(ore, 0, 0);
			blueprint.setCostOfClayRobot(resources);
			// obsidian
			ore = Integer.valueOf(matcher.group(3));
			int clay = Integer.valueOf(matcher.group(4));
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
