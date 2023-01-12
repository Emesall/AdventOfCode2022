package Day16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Valve {

	@Include
	private String name;
	private int flow;
	@Exclude
	private List<Valve> adjacents=new ArrayList<Valve>();
	
	public Valve(String name) {
		super();
		this.name = name;
	}
	
	public void addAdjacent(Valve valve) {
		adjacents.add(valve);
	}
	
	public class NameComparator implements Comparator<Valve>{

		@Override
		public int compare(Valve o1, Valve o2) {
			return o1.getName().compareTo(o2.getName());
		}
		
	}
	
}
