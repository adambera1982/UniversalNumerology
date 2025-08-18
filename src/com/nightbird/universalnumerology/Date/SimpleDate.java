package com.nightbird.universalnumerology.Date;

public final class SimpleDate implements Date {

	private final int year;
	private final int month;
	private final int day;

	public SimpleDate(int year, int month, int day) {

		this.year = year;
		this.month = month;
		this.day = day;

		validateDate();
	}

	private SimpleDate() {
		year = 0;
		month = 0;
		day = 0;
	}

	private static void checkRange(int value, int min, int max) {

		if (value < min || value > max) {
			throw new ItemOutOfRange("Date is out of range.");
		}
	}

	private static void checkNumDaysInMonth(int year, int month, int day) {

		if (day > getNumDaysInMonth(year, month)) {
			throw new ItemOutOfRange("Day is out of range.");
		}
	}

	private static int getNumDaysInMonth(int year, int month) {

		switch (month) {
			case 1:
				return 31;
			case 2:
				return isLeapYear(year) ? 29 : 28;
			case 3:
				return 31;
			case 4:
				return 30;
			case 5:
				return 31;
			case 6:
				return 30;
			case 7:
				return 31;
			case 8:
				return 31;
			case 9:
				return 30;
			case 10:
				return 31;
			case 11:
				return 30;
			case 12:
				return 31;
		}

		throw new IllegalArgumentException("Month is out of range.");
	}

	private static int getNumDaysInPrevMonth(int year, int month) {

		if (month > 1) {
			return getNumDaysInMonth(year, month - 1);
		} else {
			return getNumDaysInMonth(year - 1, 12);
		}
	}

	private static boolean isLeapYear(int year) {

		if (year % 100 == 0) {
			if (year % 400 == 0) {
				return true;
			}
		} else if (year % 4 == 0) {
			return true;
		}

		return false;
	}

	private static Date stepDayUp(ModifiedDate date) {

		date.day = date.day + 1;

		if (date.day > getNumDaysInMonth(date.year, date.month)) {
			date.day = 1;
			stepMonthUp(date);
		}

		return new SimpleDate(date.year, date.month, date.day);
	}

	public static Date stepDayUp(Date date) {
		ModifiedDate newDate = new ModifiedDate(date.getYear(), date.getMonth(), date.getDay());

		return stepDayUp(newDate);
	}

	public static Date stepMonthUp(Date date) {

		ModifiedDate newDate = new ModifiedDate(date.getYear(), date.getMonth(), date.getDay());
		stepMonthUp(newDate);

		return new SimpleDate(newDate.year, newDate.month, newDate.day);
	}

	public static Date stepMonthDown(Date date) {

		ModifiedDate newDate = new ModifiedDate(date.getYear(), date.getMonth(), date.getDay());
		stepMonthDown(newDate);

		return new SimpleDate(newDate.year, newDate.month, newDate.day);

	}

	public static Date stepYearUp(Date date) {

		ModifiedDate newDate = new ModifiedDate(date.getYear(), date.getMonth(), date.getDay());
		stepYearUp(newDate);

		return new SimpleDate(newDate.year, newDate.month, newDate.day);
	}

	public static Date stepYearDown(Date date) {

		ModifiedDate newDate = new ModifiedDate(date.getYear(), date.getMonth(), date.getDay());
		stepYearDown(newDate);

		return new SimpleDate(newDate.year, newDate.month, newDate.day);
	}

	private static Date stepDayDown(int year, int month, int day) {

		ModifiedDate newDate = new ModifiedDate(year, month, day);

		newDate.day = newDate.day - 1;

		if (newDate.day < 1) {
			newDate.day = getNumDaysInPrevMonth(year, month);
			stepMonthDown(newDate);
		}

		return new SimpleDate(newDate.year, newDate.month, newDate.day);
	}

	public static Date stepDayDown(Date date) {
		return stepDayDown(date.getYear(), date.getMonth(), date.getDay());
	}

	private static void stepMonthUp(ModifiedDate date) {

		date.month = date.month + 1;

		if (date.month > 12) {
			date.month = 1;
			stepYearUp(date);
		}
	}

	private static void stepMonthDown(ModifiedDate date) {

		date.month = date.month - 1;

		if (date.month < 1) {
			date.month = 12;
			stepYearDown(date);
		}
	}

	private static void stepYearUp(ModifiedDate date) {
		date.year = date.year + 1;
	}

	private static void stepYearDown(ModifiedDate date) {
		date.year = date.year - 1;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public String getDateString() {
		return Integer.toString(year) + "." + Integer.toString(month) + "." + Integer.toString(day);
	}

	private void validateDate() {

		checkRange(year, 1, 9999);
		checkRange(month, 1, 12);
		checkRange(day, 1, 31);

		checkNumDaysInMonth(year, month, day);
	}

	private static final class ModifiedDate {

		private int year;
		private int month;
		private int day;

		private ModifiedDate(int year, int month, int day) {
			this.year = year;
			this.month = month;
			this.day = day;
		}
	}

	private static final class ItemOutOfRange extends RuntimeException {

		private static final long serialVersionUID = -2900082313105865216L;

		ItemOutOfRange(String message) {
			super(message);
		}
	}
}
