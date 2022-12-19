package Day17;

import java.util.ArrayList;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = "elements")
public class Shape {

	private String name;
	private ArrayList<Element> elements = new ArrayList<Element>();

	public void addElements(Element left) {

		int x = left.getX();
		int y = left.getY();

		switch (name) {
		case "-":
			elements.add(left);
			for (int i = 1; i < 4; i++) {
				Element el = new Element(x + i, y);
				elements.add(el);
			}
			break;
		case "+":
			elements.add(left);
			elements.add(new Element(x + 1, y));
			elements.add(new Element(x + 2, y));
			elements.add(new Element(x + 1, y + 1));
			elements.add(new Element(x + 1, y - 1));
			break;

		case "L":
			elements.add(left);
			elements.add(new Element(x + 1, y));
			elements.add(new Element(x + 2, y));
			elements.add(new Element(x + 2, y + 1));
			elements.add(new Element(x + 2, y + 2));
			break;

		case "|":
			elements.add(left);
			for (int i = 1; i < 4; i++) {
				Element el = new Element(x, y + i);
				elements.add(el);
			}
			break;
		case "#":
			elements.add(left);
			elements.add(new Element(x + 1, y));
			elements.add(new Element(x, y + 1));
			elements.add(new Element(x + 1, y + 1));
			break;
		}
	}

	public void moveDown() {

		elements.forEach(el -> el.setY(el.getY() - 1));
	}

	public void moveLeft() {

		elements.forEach(el -> el.setX(el.getX() - 1));

	}

	public void moveRight() {

		elements.forEach(el -> el.setX(el.getX() + 1));

	}

	public boolean checkIfMoveDownPossible(Set<Element> allElements) {

		for (Element el : elements) {
			Element e = new Element(el.getX(), el.getY() - 1);
			if (allElements.contains(e))
				return false;
		}

		return true;
	}

	public boolean checkIfMoveLeftPossible(Set<Element> allElements) {

		int count = 0;

		for (Element el : elements) {
			Element e = new Element(el.getX() - 1, el.getY());
			if (allElements.contains(e))
				return false;
			if (e.getX() >= -3) {
				count++;
			}
		}

		if (count == elements.size())
			return true;

		return false;
	}

	public boolean checkifMoveRightPossible(Set<Element> allElements) {

		int count = 0;

		for (Element el : elements) {
			Element e = new Element(el.getX() + 1, el.getY());
			if (allElements.contains(e))
				return false;
			if (e.getX() <= 3) {
				count++;
			}
		}

		if (count == elements.size())
			return true;

		return false;

	}

	public Shape(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "Shape [name=" + name + "]";
	}
	
	
}
