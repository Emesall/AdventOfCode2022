package Day12;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

public class Day12 {

	static Node startingNode;
	static ArrayList<Node> startingNodes = new ArrayList<Node>();
	static Node finishingNode;
	static Graph<Node, DefaultEdge> graph = new SimpleDirectedGraph<>(DefaultEdge.class);

	public static void main(String[] args) throws IOException {
		ArrayList<String> rawData = new ArrayList<String>();

		int part1 = 0;
		int part2 = 0;
		rawData = parseFile("src/Day12/input.txt");
		addNodes(rawData);
		createAdjacent(graph, rawData.get(0).length());

		DijkstraShortestPath<Node, DefaultEdge> dijkstraShortestPath = new DijkstraShortestPath<Node, DefaultEdge>(
				graph);

		List<Node> shortestPath = dijkstraShortestPath.getPath(startingNode, finishingNode).getVertexList();
		part1 = shortestPath.size() - 1;
		part2 = part1;
		for (Node node : startingNodes) {
			GraphPath<Node, DefaultEdge> path = dijkstraShortestPath.getPath(node, finishingNode);
			if (path != null) {
				List<Node> path_list = path.getVertexList();
				if (path_list.size() - 1 < part2) {
					part2 = path_list.size() - 1;
				}
			}

		}

		System.out.println(part1);
		System.out.println("-----");
		System.out.println(part2);

	}

	public static void addNodes(ArrayList<String> rawData) {

		for (int i = 0; i < rawData.size(); i = i + 1) {

			char[] symbols = rawData.get(i).toCharArray();
			for (int j = 0; j < symbols.length; j = j + 1) {
				int index = (i * (symbols.length)) + j;

				Node node = new Node(index, symbols[j]);
				graph.addVertex(node);
				if (symbols[j] == 'S') {
					startingNode = node;
					startingNodes.add(node);
				}
				if (symbols[j] == 'a') {
					startingNodes.add(node);
				}
				if (symbols[j] == 'E') {
					finishingNode = node;
				}
			}

		}

	}

	public static void createAdjacent(Graph<Node, DefaultEdge> graph, int row_length) {
		Set<Node> elements = graph.vertexSet();
		for (Node element : elements) {
			int index = element.getNumber();
			Optional<Node> right = elements.stream().filter(el -> el.getNumber() == index + 1).findAny();
			Optional<Node> left = elements.stream().filter(el -> el.getNumber() == index - 1).findAny();
			Optional<Node> up = elements.stream().filter(el -> el.getNumber() == index - row_length).findAny();
			Optional<Node> down = elements.stream().filter(el -> el.getNumber() == index + row_length).findAny();
			if (right.isPresent()) {
				Node node = right.get();
				if (symbolValid(element.getSymbol(), node.getSymbol())) {
					graph.addEdge(element, right.get());
				}
			}
			if (left.isPresent()) {
				Node node = left.get();
				if (symbolValid(element.getSymbol(), node.getSymbol())) {
					graph.addEdge(element, left.get());
				}
			}
			if (up.isPresent()) {
				Node node = up.get();
				if (symbolValid(element.getSymbol(), node.getSymbol())) {
					graph.addEdge(element, up.get());
				}
			}
			if (down.isPresent()) {
				Node node = down.get();
				if (symbolValid(element.getSymbol(), node.getSymbol())) {
					graph.addEdge(element, down.get());
				}
			}
		}
	}

	public static boolean symbolValid(char symbol1, char symbol2) {
		if (symbol1 == 'S')
			symbol1 = 'a';
		if (symbol2 == 'E')
			symbol2 = 'z';
		int result = symbol2 - symbol1; // a-S E-z
		if (result == 1 || result <= 0) {
			return true;
		}

		return false;
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
