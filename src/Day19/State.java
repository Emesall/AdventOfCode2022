package Day19;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class State{

	int time;
	Resources resources;
	int oreRobots;
	int clayRobots;
	int obsidianRobots;
	int geodeRobots;
	int result;

	public State clone()  {
		State state=new State(time, resources.clone(), oreRobots, clayRobots, obsidianRobots, geodeRobots,result);
		return state;
	}

}
