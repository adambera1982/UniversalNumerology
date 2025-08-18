package com.nightbird.universalnumerology.Calculation.Support;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public final class Conversion {

	public static int[] convertIntToArray(int number) {

		int tempNumber = number;

		final int length = Maths.getDigitCount(tempNumber);
		int count = length - 1;

		final int[] array = new int[length];

		while (tempNumber > 0) {
			array[count] = tempNumber % 10;
			tempNumber /= 10;
			count--;
		}

		return array;
	}

	public static int[] convertStringToIntArray(String numberStream) {

		final int length = numberStream.length();

		final int[] array = new int[length];
		int i = 0;

		CharacterIterator iterator = new StringCharacterIterator(numberStream);

		for (char ch = iterator.first(); ch != CharacterIterator.DONE; ch = iterator
				.next()) {
			array[i] = Character.digit(ch, 10);
			i++;
		}

		return array;
	}

	public static String convertIntArrayToString(int[] numberStream) {

		StringBuilder builder = new StringBuilder(numberStream.length);

		for (int i : numberStream) {
			builder.append(i);
		}

		return builder.toString();
	}
}
