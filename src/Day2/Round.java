package Day2;

public class Round {

	private static final int ROCK = 1;
	private static final int PAPER = 2;
	private static final int SCISSORS = 3;

	private String villain;
	private String hero;

	public Round(String villain, String hero) {
		super();
		this.villain = villain;
		this.hero = hero;
	}

	// 0lose,3 point draw,6 winning
	private int result(int villain, int hero) {

		if (hero - villain == 1 || hero - villain == -2) {
			return 6;
		}

		if (villain - hero == 1 || villain - hero == -2) {
			return 0;
		}

		return 3;

	}

	private int result2(int villain, String hero) {
		int result = 0;
		if (hero.equals("X")) {
			if (villain != 1) {
				result = villain - 1;
			} else {
				result = 3;
			}
		}
		if (hero.equals("Y")) {
			result = villain + 3;
		}
		if (hero.equals("Z")) {
			result = 6;
			if (villain != 3) {
				result += villain + 1;
			} else {
				result += 1;
			}

		}

		return result;
	}

	private int pointFromString(String symbol) {
		if (symbol.equals("A") || symbol.equals("X"))
			return ROCK;
		if (symbol.equals("B") || symbol.equals("Y"))
			return PAPER;
		if (symbol.equals("C") || symbol.equals("Z"))
			return SCISSORS;
		return 0;
	}

	public int calculateScore1() {

		return pointFromString(hero) + result(pointFromString(villain), pointFromString(hero));
	}

	public int calculateScore2() {

		return result2(pointFromString(villain), hero);
	}

}
