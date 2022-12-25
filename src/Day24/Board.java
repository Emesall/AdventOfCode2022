package Day24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import lombok.Data;

@Data
public class Board {

	private ArrayList<Element> elements = new ArrayList<Element>();
	private Element actualPoint = new Element(1, 0);
	private Element finishingPoint;
	private Graph<Element, DefaultEdge> graph = new SimpleDirectedWeightedGraph<>(DefaultEdge.class);
	private Map<Element, ArrayList<Character>> moves = new HashMap<Element, ArrayList<Character>>();
	private Map<Integer, ArrayList<Element>> states = new HashMap<Integer, ArrayList<Element>>();
	private int counter = 0;

	public void addState(int counter) {

		states.put(counter, elements);

	}

	public void addVertex(Element el) {
		graph.addVertex(el);
		elements.add(el);
	}

	public void blizzardMove() {
		moves = new HashMap<Element, ArrayList<Character>>();
		// calculate moves
		for (Element el : elements) {
			move(el);
		}
		// move
		for (Element el : moves.keySet()) {
			el.setSymbols(moves.get(el));
		}

	}

	/*public void personMove() {
		int x = actualPoint.getX();
		int y = actualPoint.getY();
		Optional<Element> right = findElement(x + 1, y);
		Optional<Element> left = findElement(x - 1, y);
		Optional<Element> up = findElement(x, y - 1);
		Optional<Element> down = findElement(x, y + 1);

		for (int i : states.keySet().stream().filter(i -> i >= counter).collect(Collectors.toSet())) {

			if (right.isPresent() && right.get().getSymbols().isEmpty()) {
				DefaultEdge edge = graph.addEdge(actualPoint, right.get());
				graph.setEdgeWeight(edge, counter);
			}

			if (left.isPresent() && left.get().getSymbols().isEmpty()) {
				DefaultEdge edge = graph.addEdge(actualPoint, left.get());
				graph.setEdgeWeight(edge, counter);
			}

			if (up.isPresent() && up.get().getSymbols().isEmpty()) {
				DefaultEdge edge = graph.addEdge(actualPoint, up.get());
				graph.setEdgeWeight(edge, counter);
			}

			if (down.isPresent() && down.get().getSymbols().isEmpty()) {
				DefaultEdge edge = graph.addEdge(actualPoint, down.get());
				graph.setEdgeWeight(edge, counter);
			}

			counter++;
			if (counter == states.size()) {
				counter = counter % states.size();
			}
		}

	}
*/
	public void move(Element el) {

		for (char move : el.getSymbols()) {
			switch (move) {
			case '>':
				moveRight(el);
				break;
			case '<':
				moveLeft(el);
				break;
			case '^':
				moveUp(el);
				break;
			case 'v':
				moveDown(el);
				break;

			}

		}
		el.setSymbols(new ArrayList<Character>());
	}

	public void moveRight(Element el) {

		int x = el.getX();
		int y = el.getY();
		Optional<Element> element = findElement(x + 1, y);
		if (element.isPresent()) {
			addToMap(element.get(), '>');
		} else {
			Element el2 = findMinInRow(y);
			addToMap(el2, '>');
		}

	}

	public void moveLeft(Element el) {

		int x = el.getX();
		int y = el.getY();
		Optional<Element> element = findElement(x - 1, y);
		if (element.isPresent()) {
			addToMap(element.get(), '<');
		} else {
			Element el2 = findMaxInRow(y);
			addToMap(el2, '<');
		}

	}

	public void moveUp(Element el) {

		int x = el.getX();
		int y = el.getY();
		Optional<Element> element = findElement(x, y - 1);
		if (element.isPresent()) {
			addToMap(element.get(), '^');
		} else {
			Element el2 = findMaxInColumn(x);
			addToMap(el2, '^');
		}

	}

	public void moveDown(Element el) {

		int x = el.getX();
		int y = el.getY();
		Optional<Element> element = findElement(x, y + 1);
		if (element.isPresent()) {
			addToMap(element.get(), 'v');
		} else {
			Element el2 = findMinInColumn(x);
			addToMap(el2, 'v');
		}

	}

	public void addToMap(Element el, char symbol) {
		if (moves.containsKey(el)) {
			moves.get(el).add(symbol);
		} else {
			moves.put(el, new ArrayList<Character>(Arrays.asList(symbol)));
		}
	}

	public Element findMinInRow(int row) {
		List<Element> rowEl = elements.stream().filter(el -> el.getY() == row).collect(Collectors.toList());
		return Collections.min(rowEl, new Element().new SortX());
	}

	public Element findMaxInRow(int row) {
		List<Element> rowEl = elements.stream().filter(el -> el.getY() == row).collect(Collectors.toList());
		return Collections.max(rowEl, new Element().new SortX());
	}

	public Element findMinInColumn(int column) {
		List<Element> rowEl = elements.stream().filter(el -> el.getX() == column).collect(Collectors.toList());
		return Collections.min(rowEl, new Element().new SortY());
	}

	public Element findMaxInColumn(int column) {
		List<Element> rowEl = elements.stream().filter(el -> el.getX() == column).collect(Collectors.toList());
		return Collections.max(rowEl, new Element().new SortY());
	}

	public Optional<Element> findElement(int x, int y) {
		return elements.stream().filter(el -> el.getX() == x && el.getY() == y).findFirst();
	}

	public void draw() {
		int maxX = Collections.max(elements, new Element().new SortX()).getX();
		int maxY = Collections.max(elements, new Element().new SortY()).getY();
		int minY = Collections.min(elements, new Element().new SortY()).getY();
		int minX = Collections.min(elements, new Element().new SortX()).getX();

		for (int i = minY; i <= maxY; i++) {

			for (int j = minX; j <= maxX; j++) {

				List<Character> symbols = findElement(j, i).get().getSymbols();
				if (!symbols.isEmpty()) {
					if (symbols.size() > 1) {
						System.out.print(symbols.size());
					} else {
						System.out.print(symbols.get(0));
					}
				} else {
					System.out.print('.');
				}

			}
			System.out.println();
		}
	}

}
