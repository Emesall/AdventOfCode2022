package Day20;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@EqualsAndHashCode(exclude = "value")
@ToString(exclude="number")
public class Element {

	private int number;
	private long value;
}
