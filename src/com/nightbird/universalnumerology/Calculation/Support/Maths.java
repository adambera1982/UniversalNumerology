package com.nightbird.universalnumerology.Calculation.Support;

public final class Maths {

	public static int getDigitCount(int number) {

		int num = number;

		num /= 10;

		if (num == 0) {
			return 1;
		}

		return (1 + getDigitCount(num));
	}

	public static int getTotalInMatrix(int[] matrix) {

		int[] numberMatrix = matrix.clone();

		int total = 0;
		for (int num : numberMatrix) {
			total = total + num;
		}

		return total;
	}

	public static int[] separateDigits(int number) {

		int num = number;

		final int[] digits;
		int digitCount = getDigitCount(num);

		digits = new int[digitCount];

		while (num != 0) {
			--digitCount;
			digits[digitCount] = num % 10;
			num /= 10;
		}

		return digits;
	}

	public static int concat(int number1, int number2) {

		int num = number1;
		if (number2 > 0) {
			num = concat(number1 * 10, number2 / 10);
		}

		return num - number2 / 10 + number2;
	}

	public static int concat(int[] number) {

		int length = number.length;

		int multiplier = 1;
		int result = 0;

		for (int i = length - 1; i >= 0; i--) {
			result = result + number[i] * multiplier;
			multiplier = multiplier * 10;
		}

		return result;
	}

	public static int sumOfInt(int number) {

		int num = number % 10;

		if (number / 10 != 0) {
			num += sumOfInt(number / 10);
		}

		return num;
	}

	public static int sumArrayToInt(int[]... array) {
		int number = 0;

		for (int[] anArray : array) {
			for (int subArray : anArray) {
				number += subArray;
			}
		}

		return number;
	}

	public static int[] increaseNumberStream(int[] numberStream) {

		int length = numberStream.length;
		int[] newStream = new int[length + 1];

		System.arraycopy(numberStream, 0, newStream, 1, length);

		increase(newStream, length);

		if (newStream[0] == 0) {
			// Compatibility problems, only available on API 11
			//return Arrays.copyOfRange(newStream, 1, newStream.length);
			int[] array = new int[length];
			System.arraycopy(newStream, 1, array, 0, newStream.length - 1);
			return array;
		} else {
			return newStream;
		}
	}

	public static int[] decreaseNumberStream(int[] numberStream) {

		int length = numberStream.length;
		int newStream[] = new int[length];

		System.arraycopy(numberStream, 0, newStream, 0, length);

		decrease(newStream, length - 1);

		if (newStream[0] == 0) {
			// Compatibility problems, only available on API 11
			//return Arrays.copyOfRange(newStream, 1, newStream.length);
			int[] array = new int[length - 1];
			System.arraycopy(newStream, 1, array, 0, newStream.length - 1);
			return array;
		} else {
			return newStream;
		}
	}

	private static void increase(int[] numberStream, int position) {

		numberStream[position]++;

		if (numberStream[position] == 10) {
			numberStream[position] = 0;
			increase(numberStream, position - 1);
		}
	}

	private static void decrease(int[] numberStream, int position) {

		numberStream[position]--;

		if (numberStream[position] == -1) {
			numberStream[position] = 9;
			decrease(numberStream, position - 1);
		}

	}

	public static int reverseNumber(int number, int reversed) {
		if (number == 0) {
			return reversed;
		} else {
			return reverseNumber(number / 10, reversed * 10 + number % 10);
		}
	}
}
