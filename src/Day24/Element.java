package Day24;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude="symbols")
public class Element {

	private int x;
	private int y;
	private List<Character> symbols=new ArrayList<Character>();

	
	
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
	
	public void addSymbol(char symbol) {
		symbols.add(symbol);
	}

	public Element(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

}
