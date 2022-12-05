package Day4;

public class Section {

	private int lower_limit;
	private int upper_limit;
	
	public Section(int lower_limit, int upper_limit) {
		super();
		this.lower_limit = lower_limit;
		this.upper_limit = upper_limit;
	}
	
	//does one section contain another
	public boolean contains(Section section) {
		
		if(upper_limit>=section.upper_limit && lower_limit<=section.lower_limit)
			return true;
		
		return false;
	}
	
	// do two section overlap each other
	public boolean overlaps(Section section) {
		
		if(upper_limit>=section.lower_limit && lower_limit<=section.upper_limit)
			return true;
		
		
		return false;
	}
	
	
	
}
