package com.nightbird.universalnumerology.Data.Type;

public enum CalculationMethod {
	NORMAL_DATE, ARTIFICIAL_DATE, HISTORICAL_DATE, NORMAL_NUMBER, ARTIFICIAL_NUMBER;

	public static boolean isDateMethod(CalculationMethod method) {
		return !(method == ARTIFICIAL_NUMBER || method == NORMAL_NUMBER);
	}
}
