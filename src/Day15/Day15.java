package Day15;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day15 {

	static ArrayList<Sensor> sensors = new ArrayList<Sensor>();
	static ArrayList<Element> elements = new ArrayList<Element>();
	static Set<EmptyElement> borderline = new HashSet<EmptyElement>();

	public static void main(String[] args) throws IOException {

		ArrayList<String> rawData = new ArrayList<String>();

		int part1 = 0;
		long part2 = 0l;
		int row = 2000000;
		int min = 0;
		int max = 4000000;

		rawData = parseFile("src/Day15/input.txt");

		rawData.forEach(s -> addSensor(s));

		part1 = countEmptyInRow(row);
		EmptyElement foundBeacon = null;
		findBorderlineElements();

		for (EmptyElement el : borderline) {
			foundBeacon = el;
			if (!checkIfEmpty(el) && inBorder(el, min, max))
				break;
		}
		System.out.println(foundBeacon);
		part2 = 4000000L * foundBeacon.getX() + foundBeacon.getY();

		System.out.println(part1);
		System.out.println("------");
		System.out.println(part2);

	}

	public static boolean inBorder(Element el, int min, int max) {
		if (el.getX() >= min && el.getX() <= max && el.getY() >= min && el.getY() <= max)
			return true;
		return false;
	}

	public static int countEmptyInRow(int row) {

		int counter = 0;
		Sensor minX = findMinX();
		Sensor maxX = findMaxX();
		int min = minX.getX() - minX.getDistance();
		int max = maxX.getX() + maxX.getDistance();

		for (int x = min; x < max; x++) {
			EmptyElement el = new EmptyElement(x, row);
			if (checkIfEmpty(el) && !elements.contains(el))
				counter++;
		}

		return counter;
	}

	public static void findBorderlineElements() {

		for (Sensor sensor : sensors) {
			int sensorX = sensor.getX();
			int sensorY = sensor.getY();
			int distance = sensor.getDistance();

			int x = sensorX + distance + 1;
			int y = sensorY;
			// from right to down
			while (x != sensorX) {
				EmptyElement el = new EmptyElement(x, y);
				x--;
				y++;
				borderline.add(el);
			}
			// from down to left
			while (x != sensorX - distance - 1) {
				EmptyElement el = new EmptyElement(x, y);
				x--;
				y--;
				borderline.add(el);

			}
			// from left to top
			while (x != sensorX) {
				EmptyElement el = new EmptyElement(x, y);
				x++;
				y--;
				borderline.add(el);
			}
			// from top to right
			while (x != sensorX + distance + 1) {
				EmptyElement el = new EmptyElement(x, y);
				x++;
				y++;
				borderline.add(el);

			}

		}
	}

	public static Sensor findMaxX() {

		return Collections.max(sensors, new EmptyElement().new SortX());
	}

	public static Sensor findMinX() {

		return Collections.min(sensors, new EmptyElement().new SortX());
	}

	public static boolean checkIfEmpty(EmptyElement element) {

		for (Sensor sensor : sensors) {
			if (element.checkIfCloserThanBeacon(sensor))
				return true;
		}

		return false;
	}

	public static void addSensor(String line) {
		Pattern pattern1 = Pattern
				.compile("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)");
		Matcher matcher = pattern1.matcher(line);
		Sensor sensor = new Sensor();
		Beacon beacon = new Beacon();
		int beaconX = 0;
		int beaconY = 0;
		if (matcher.matches()) {

			sensor.setX(Integer.valueOf(matcher.group(1)));
			sensor.setY(Integer.valueOf(matcher.group(2)));
			beaconX = Integer.valueOf(matcher.group(3));
			beaconY = Integer.valueOf(matcher.group(4));
		}
		beacon.setX(beaconX);
		beacon.setY(beaconY);

		sensor.calculateDistance(beaconX, beaconY);
		sensors.add(sensor);

		elements.add(beacon);
		elements.add(sensor);

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
