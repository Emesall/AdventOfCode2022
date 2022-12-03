package Day2;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2 {

	public static void main(String[] args) throws IOException{
	
		int part1=0;
		int part2=0;
		Scanner scanner=new Scanner(new FileReader("src/Day2/input.txt"));
		ArrayList<Round> rounds=new ArrayList<Round>();
		
		while(scanner.hasNext()) {
			String line=scanner.nextLine();
			String[] data=line.split(" ");
			Round round=new Round(data[0],data[1]);
			rounds.add(round);
			
		}
		
		
		
		for(Round round:rounds) {
			part1+=round.calculateScore1();
			part2+=round.calculateScore2();

		}
		System.out.println(part1);
		System.out.println("-----");
		System.out.println(part2);
		scanner.close();

	}

}
