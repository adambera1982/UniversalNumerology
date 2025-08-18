package com.nightbird.universalnumerology.Data.Type;

public enum ChosenNumber {
	STABLE, ASCENDING, DESCENDING, MOUNTAIN, VALLEY, RUNNER_UP, KEEPER, RUNNER_DOWN, HOLDER;

	public static ChosenNumber[] newInstance(ChosenNumber[] chosenNumbers) {
		ChosenNumber[] copy = new ChosenNumber[10];
		for (int i = 0; i < chosenNumbers.length; i++) {
			copy[i] = ChosenNumber.values()[chosenNumbers[i].ordinal()];
		}

		return copy;
	}
}
