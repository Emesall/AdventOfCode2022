package Day22;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.Data;

@Data
public class Board {

	private ArrayList<Element> elements = new ArrayList<Element>();
	private Element actualPos;
	private int direction = 0; // 0-right 1-down 2-left 3-up

	public void move(int number) {

		switch (direction) {
		case 0:
			actualPos = moveRight(actualPos, number);
			break;
		case 1:
			actualPos = moveDown(actualPos, number);
			break;
		case 2:
			actualPos = moveLeft(actualPos, number);
			break;

		case 3:
			actualPos = moveUp(actualPos, number);
			break;

		}
	}

	public Element moveRight(Element actualPos, int number) {

		int actualX = actualPos.getX();
		int actualY = actualPos.getY();
		for (int i = 1; i <= number; i++) {
			if (isMoveLegal(actualX + 1, actualY)) {
				if (isWall(actualX + 1, actualY)) {
					break;
				} else {
					actualX++;
				}

			} else {
				Element el = findMinInRow(actualY);
				if (!el.isWall()) {
					actualX = el.getX();
				} else {
					break;
				}
			}
		}
		actualPos.setX(actualX);
		return actualPos;
	}

	public Element moveLeft(Element actualPos, int number) {

		int actualX = actualPos.getX();
		int actualY = actualPos.getY();
		for (int i = 1; i <= number; i++) {
			if (isMoveLegal(actualX - 1, actualY)) {
				if (isWall(actualX - 1, actualY)) {
					break;
				} else {
					actualX--;
				}

			} else {
				Element el = findMaxInRow(actualY);
				if (!el.isWall()) {
					actualX = el.getX();
				} else {
					break;
				}
			}
		}
		actualPos.setX(actualX);
		return actualPos;
	}

	public Element moveDown(Element actualPos, int number) {

		int actualX = actualPos.getX();
		int actualY = actualPos.getY();
		for (int i = 1; i <= number; i++) {
			if (isMoveLegal(actualX, actualY + 1)) {
				if (isWall(actualX, actualY + 1)) {
					break;
				} else {
					actualY++;
				}

			} else {
				Element el = findMinInColumn(actualX);
				if (!el.isWall()) {
					actualY = el.getY();
				} else {
					break;
				}
			}
		}
		actualPos.setY(actualY);
		return actualPos;
	}

	public Element moveUp(Element actualPos, int number) {

		int actualX = actualPos.getX();
		int actualY = actualPos.getY();
		for (int i = 1; i <= number; i++) {
			if (isMoveLegal(actualX, actualY - 1)) {
				if (isWall(actualX, actualY - 1)) {
					break;
				} else {
					actualY--;
				}

			} else {
				Element el = findMaxInColumn(actualX);
				if (!el.isWall()) {
					actualY = el.getY();
				} else {
					break;
				}
			}
		}
		actualPos.setY(actualY);
		return actualPos;
	}

	public void addElement(Element el) {
		elements.add(el);
	}

	public boolean isWall(int x, int y) {
		Optional<Element> el = findElement(x, y);
		return el.get().isWall();
	}

	public boolean isMoveLegal(int x, int y) {

		Optional<Element> el = findElement(x, y);
		if (el.isEmpty())
			return false;
		return true;
	}

	public void rotate(char symbol) {
		if (symbol == 'R') {
			direction++;
		}
		if (symbol == 'L') {
			direction--;
		}
		if (direction == 4)
			direction = 0;
		if (direction == -1)
			direction = 3;
	}

	public Optional<Element> findElement(int x, int y) {
		return elements.stream().filter(el -> el.getX() == x && el.getY() == y).findFirst();
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

	public void draw() {
		int maxX = Collections.max(elements, new Element().new SortX()).getX();
		int maxY = Collections.max(elements, new Element().new SortY()).getY();

		for (int i = 1; i <= maxY; i++) {
			for (int j = 1; j <= maxX; j++) {
				Optional<Element> el = findElement(j, i);

				if (el.isPresent()) {
					if (el.get().isWall()) {
						System.out.print("#");
					} else {
						System.out.print(".");
					}
				} else {
					System.out.print(" ");

				}

			}
			System.out.println();
		}
	}

}
