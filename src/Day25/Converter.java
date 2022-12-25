package Day25;

public class Converter {

	public long decodeSymbol(char c) {

		switch (c) {
		case '=':
			return -2;

		case '-':
			return -1;
		case '0':
			return 0;

		case '1':
			return 1;
		case '2':
			return 2;

		}
		return -3;
	}

	public long convertToDecimal(String number) {
		long decimal = 0;

		char[] digits = number.toCharArray();

		for (int i = 0; i < digits.length; i++) {
			int pow = digits.length - 1 - i;
			decimal = decimal + (long) Math.pow(5, pow) * decodeSymbol(digits[i]);
		}

		return decimal;
	}

	public String convertToFiveimal(long number) {
		String num = "";

		long rest = 0;
		while (number != 0) {
			rest = number % 5;
			number = number / 5;

			if (rest == 4) {
				num = "-" + num;
				number++;
			}
			if (rest == 3) {
				num = "=" + num;
				number++;
			}

			if (rest != 3 && rest != 4) {
				num = rest + num;
			}

		}

		return num;
	}

}
