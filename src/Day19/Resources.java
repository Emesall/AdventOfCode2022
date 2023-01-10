package Day19;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resources {

	private int ore;
	private int clay;
	private int obsidian;

	public void addOre(int number) {
		ore += number;
	}

	public void addClay(int number) {
		clay += number;
	}

	public void addObsidian(int number) {
		obsidian += number;
	}

	public boolean checkIfEnoughResources(Resources blueprint) {
		if (ore >= blueprint.getOre() && clay >= blueprint.getClay() && obsidian >= blueprint.getObsidian())
			return true;
		return false;
	}

	public void buildRobot(Resources blueprint) {
		ore -= blueprint.getOre();
		clay -= blueprint.getClay();
		obsidian -= blueprint.getObsidian();
	}

}
