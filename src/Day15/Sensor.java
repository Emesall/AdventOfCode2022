package Day15;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class Sensor extends Element {

	private int distance;

	public void calculateDistance(int beaconX, int beaconY) {
		distance = Math.abs(this.getX()-beaconX) + Math.abs(this.getY() - beaconY);
	}
}
