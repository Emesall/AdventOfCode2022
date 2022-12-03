package Day3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day3Test {

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void testDivideIntoTwo() {
		//String s="pQPsjwnNgQmnNNwQgNLNnmgZqvFqtwqtrMMzvzwFtrqTrr";
		String s="PmmdzqPrVvPwwTWBwg";
		String[] array=Day3.divideIntoTwo(s);
		String out=array[0]+array[1];
		System.out.println(array[0]);
		System.out.println(array[1]);
		assertEquals(array[0].length(),array[1].length());
		assertEquals(s, out);
	}



}
