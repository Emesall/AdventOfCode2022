package Day22;

import java.util.Comparator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(exclude="isWall")
@AllArgsConstructor
@NoArgsConstructor
public class Element {

	private int x;
	private int y;
	private boolean isWall;
	
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
}
