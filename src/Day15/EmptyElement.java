package Day15;

import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper = true)
@NoArgsConstructor
public class EmptyElement extends Element {

	public boolean checkIfCloserThanBeacon(Sensor sensor) {
		int distance = sensor.getDistance();
		if (Math.abs(this.getX() - sensor.getX()) + Math.abs(this.getY() - sensor.getY()) <= distance)
			return true;
		return false;
	}

	public EmptyElement(int x, int y) {
		super(x, y);
	}
}
