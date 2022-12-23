package Day23;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import lombok.Data;

@Data
public class Board {

	private ArrayList<Element> elves = new ArrayList<Element>();

	public void addElf(Element elf) {
		elves.add(elf);
	}

	public Optional<Element> findElf(int x, int y) {
		return elves.stream().filter(el -> el.getX() == x && el.getY() == y).findFirst();
	}

	public boolean checkIfHasAdjacents(Element elf) {
		int x = elf.getX();
		int y = elf.getY();
		if (findElf(x + 1, y).isPresent() || findElf(x - 1, y).isPresent() || findElf(x, y + 1).isPresent()
				|| findElf(x, y - 1).isPresent() || findElf(x - 1, y - 1).isPresent()
				|| findElf(x - 1, y + 1).isPresent() || findElf(x + 1, y + 1).isPresent()
				|| findElf(x + 1, y - 1).isPresent())
			return true;

		return false;
	}

	public boolean checkIfValidAndMove(Element elf, char move) {

		int x = elf.getX();
		int y = elf.getY();
		Element proposedMove = new Element(x, y);
		switch (move) {
		case 'N':
			if (findElf(x, y - 1).isPresent() || findElf(x + 1, y - 1).isPresent() || findElf(x - 1, y - 1).isPresent())
				return false;
			proposedMove.setY(y - 1);
			elf.setProposedMove(proposedMove);
			break;

		case 'S':
			if (findElf(x, y + 1).isPresent() || findElf(x + 1, y + 1).isPresent() || findElf(x - 1, y + 1).isPresent())
				return false;
			proposedMove.setY(y + 1);
			elf.setProposedMove(proposedMove);
			break;

		case 'W':
			if (findElf(x - 1, y).isPresent() || findElf(x - 1, y + 1).isPresent() || findElf(x - 1, y - 1).isPresent())
				return false;
			proposedMove.setX(x - 1);
			elf.setProposedMove(proposedMove);
			break;

		case 'E':
			if (findElf(x + 1, y).isPresent() || findElf(x + 1, y + 1).isPresent() || findElf(x + 1, y - 1).isPresent())
				return false;
			proposedMove.setX(x + 1);
			elf.setProposedMove(proposedMove);
			break;
		}

		return true;
	}

	public void draw() {
		int maxX = Collections.max(elves, new Element().new SortX()).getX();
		int maxY = Collections.max(elves, new Element().new SortY()).getY();
		int minY = Collections.min(elves, new Element().new SortY()).getY();
		int minX = Collections.min(elves, new Element().new SortX()).getX();
		System.out.println(minX-1);
		for (int i = minY - 1; i <= maxY + 1; i++) {
			System.out.print(i);
			for (int j = minX - 1; j <= maxX + 1; j++) {

				Optional<Element> el = findElf(j, i);
				if (el.isPresent()) {
					System.out.print("#");
				} else {
					System.out.print(".");
				}

			}
			System.out.println();
		}
	}

	public int calculateEmptyPoints() {
		int result = 0;
		int maxX = Collections.max(elves, new Element().new SortX()).getX();
		int maxY = Collections.max(elves, new Element().new SortY()).getY();
		int minY = Collections.min(elves, new Element().new SortY()).getY();
		int minX = Collections.min(elves, new Element().new SortX()).getX();

		for (int i = minY; i <= maxY; i++) {
			
			for (int j = minX; j <= maxX; j++) {

				Optional<Element> el = findElf(j, i);
				if (el.isEmpty())
					result++;

			}

		}
		return result;
	}
}