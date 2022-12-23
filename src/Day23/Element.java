package Day23;

import java.util.Comparator;
import java.util.Queue;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "proposedMove", "moves" })
@ToString(exclude={"proposedMove","moves"})
public class Element {

	private int x;
	private int y;
	private Element proposedMove;
	private Queue<Character> moves;

	class SortX implements Comparator<Element> {

		@Override
		public int compare(Element el1, Element el2) {

			return Integer.valueOf(el1.getX()).compareTo(Integer.valueOf(el2.getX()));
		}

	}

	class SortY implements Comparator<Element> {

		@Override
		public int compare(Element el1, Element el2) {

			return Integer.valueOf(el1.getY()).compareTo(Integer.valueOf(el2.getY()));
		}

	}
	

	public char getMove() {
		char move = moves.remove();
		moves.add(move);
		return move;
	}

	public Element(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
