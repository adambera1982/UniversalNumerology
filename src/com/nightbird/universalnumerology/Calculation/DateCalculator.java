package com.nightbird.universalnumerology.Calculation;


import com.nightbird.universalnumerology.Calculation.Support.Maths;
import com.nightbird.universalnumerology.Data.DateNumerologyData;
import com.nightbird.universalnumerology.Data.Type.*;
import com.nightbird.universalnumerology.Date.Date;
import com.nightbird.universalnumerology.Date.SimpleDate;

public class DateCalculator extends BaseCalculator {

	private final Date date;
	private final CalculationMethod method;

	public DateCalculator(int year, int month, int day, CalculationMethod method) {

		date = new SimpleDate(year, month, day);
		this.method = method;
	}

	public DateCalculator(Date date, CalculationMethod method) {

		this.date = date;
		this.method = method;
	}

	public DateNumerologyData calculate() {

		Date prevDate = SimpleDate.stepDayDown(date);
		Date nextDate = SimpleDate.stepDayUp(date);

		BaseDateData currData = calculation(date.getYear(), date.getMonth(), date.getDay(), method);

		BaseDateData prevData = new DateCalculator(prevDate, method).calcChosenMatrices();
		BaseDateData nextData = new DateCalculator(nextDate, method).calcChosenMatrices();

		ChosenNumber[] chosen = getChosenNumber(prevData.getMatrix(), nextData.getMatrix());

		return new DateNumerologyData.Builder(currData.getMatrix(), currData.getEmphasized(), chosen, currData.getStability(), currData.getDominance(), method).
				energetics(currData.getEnergetics()).success(currData.getSuccess()).build();

	}

	private BaseDateData calcChosenMatrices() {
		return calculation(date.getYear(), date.getMonth(), date.getDay(), method);
	}

	protected BaseDateData calculation(int year, int month, int day, CalculationMethod method) {

		addToMatrix(year, month, day);

		EmphasizedNumber emphasized;
		BaseDateData data = null;

		switch (method) {
			case NORMAL_DATE: {
				NormalDateCalculator dateCalc = new NormalDateCalculator(year, month, day);

				emphasized = dateCalc.calculate();
				addEmphasizedNumberToMatrix(emphasized);

				BaseData baseData = super.baseCalculation();

				Energetics energetics = dateCalc.calcEnergetics();
				Success success = dateCalc.calcSuccess(baseData.getMatrix(), baseData.getDominance(), energetics);

				data = new BaseDateData(baseData.getMatrix(), emphasized, baseData.getStability(), baseData.getDominance(), success, energetics);

				break;
			}

			case HISTORICAL_DATE: {
				HistoricalDateCalculator dateCalc = new HistoricalDateCalculator(year, month, day);
				emphasized = dateCalc.calculate();
				addEmphasizedNumberToMatrix(emphasized);

				BaseData baseData = super.baseCalculation();

				data = new BaseDateData(baseData.getMatrix(), emphasized, baseData.getStability(), baseData.getDominance(), null, null);
				break;
			}

			case ARTIFICIAL_DATE: {
				ArtificialDateCalculator dateCalc = new ArtificialDateCalculator(year, month, day);
				emphasized = dateCalc.calculate();
				addEmphasizedNumberToMatrix(emphasized);

				BaseData baseData = super.baseCalculation();

				data = new BaseDateData(baseData.getMatrix(), emphasized, baseData.getStability(), baseData.getDominance(), null, null);

				break;
			}
		}

		return data;
	}

	private static abstract class BaseDateCalculator implements Calculator {

		private final int[] yearDigits;
		private final int[] monthDigits;
		private final int[] dayDigits;
		private int stepOneResult;
		private int stepTwoResult;
		private int stepThreeResult;
		private int stepFourResult;

		public BaseDateCalculator(int year, int month, int day) {
			this.yearDigits = Maths.separateDigits(year);
			this.monthDigits = Maths.separateDigits(month);
			this.dayDigits = Maths.separateDigits(day);
		}

		@Override
		public int stepOne() {

			stepOneResult = Maths.sumArrayToInt(yearDigits, monthDigits, dayDigits);
			return stepOneResult;
		}

		@Override
		public int stepTwo() {

			stepTwoResult = Maths.sumOfInt(stepOneResult);
			return stepTwoResult;
		}

		@Override
		public int stepThree() {

			stepThreeResult = stepOneResult + (dayDigits[0] * 2);
			return stepThreeResult;
		}

		@Override
		public int stepFour() {

			stepFourResult = Maths.sumOfInt(stepThreeResult);
			return stepFourResult;
		}

		public int[] getYearDigits() {
			return yearDigits;
		}

		public int[] getMonthDigits() {
			return monthDigits;
		}

		public int[] getDayDigits() {
			return dayDigits;
		}

		public int getStepOneResult() {
			return stepOneResult;
		}

		public void setStepOneResult(int stepOneResult) {
			this.stepOneResult = stepOneResult;
		}

		public int getStepTwoResult() {
			return stepTwoResult;
		}

		public void setStepTwoResult(int stepTwoResult) {
			this.stepTwoResult = stepTwoResult;
		}

		public int getStepThreeResult() {
			return stepThreeResult;
		}

		public void setStepThreeResult(int stepThreeResult) {
			this.stepThreeResult = stepThreeResult;
		}

		public int getStepFourResult() {
			return stepFourResult;
		}

		public void setStepFourResult(int stepFourResult) {
			this.stepFourResult = stepFourResult;
		}
	}

	protected static final class NormalDateCalculator extends BaseDateCalculator implements Energetics.Calculation,
			Success.calculation {

		public NormalDateCalculator(int year, int month, int day) {
			super(year, month, day);
		}

		@Override
		public int stepThree() {

			int number = getStepOneResult() - (getDayDigits()[0] * 2);

			if (number < 0) {
				number = Math.abs(number);
			}

			setStepThreeResult(number);

			return number;
		}

		public final EmphasizedNumber calculate() {

			final int develop = stepOne();
			final int mission = stepTwo();
			final int ancestor = stepThree();
			final int source = stepFour();

			return new EmphasizedNumber(develop, mission, ancestor, source);
		}

		@Override
		public Energetics calcEnergetics() {

			final int year = Maths.concat(getYearDigits());
			int month = Maths.concat(getMonthDigits());
			final int day = Maths.concat(getDayDigits());

			if (day < 10) {
				month = month * 10;
			}

			int monthAndDay = Maths.concat(month, day);

			final int value = year * monthAndDay;
			final int[] cyclogram = Maths.separateDigits(value);
			final int potential = Maths.sumOfInt(value);
			final float level = (float) potential / (float) Maths.getDigitCount(value);

			return new Energetics(potential, level, cyclogram);
		}

		@Override
		public Success calcSuccess(int[] matrix, SpiritualDominance dominance, Energetics energetics) {

			final int[] numberMatrix = matrix.clone();
			final int total = Maths.getTotalInMatrix(numberMatrix) - matrix[0];

			float stepOne = (float) (numberMatrix[1] + numberMatrix[4] + numberMatrix[7]) / (float) total;

			stepOne = stepOne * 100;
			stepOne = (int) ((stepOne + 5) / 10) * 10;

			int stepTwo = numberMatrix[2] * 10;

			int stepThree = -10;

			if (numberMatrix[3] == 2) {
				stepThree = 10;
			} else if (numberMatrix[3] == 1 || numberMatrix[3] == 3) {
				stepThree = 0;
			}

			int stepFour = -10;

			if (numberMatrix[5] == 2) {
				stepFour = 10;
			} else if (numberMatrix[5] == 1 || numberMatrix[5] == 3) {
				stepFour = 0;
			}

			int stepFive = 0;

			if (numberMatrix[7] == 2) {
				stepFive = 20;
			} else if (numberMatrix[7] == 0) {
				stepFive = -10;
			}

			int stepSix = -10;

			if (numberMatrix[8] == 0) {
				stepSix = 10;
			} else if (numberMatrix[8] == 1) {
				stepSix = 0;
			}

			int stepSeven = -10;
			float dom = dominance.get();

			if (1 <= dom && dom < 3) {
				stepSeven = 0;
			} else if (3 <= dom && dom < 20) {
				stepSeven = 10;
			} else if (20 <= dom) {
				stepSeven = 20;
			}

			float stepEight = Math.round((float) (energetics.get(Energetics.Type.GENERIC_ENERGETICS_LEVEL) - 3.5f)) * 10;

			int result = (int) (stepOne + stepTwo + stepThree + stepFour + stepFive + stepSix + stepSeven + stepEight);

			return new Success(result);
		}
	}

	protected static final class ArtificialDateCalculator extends BaseDateCalculator {

		public ArtificialDateCalculator(int year, int month, int day) {
			super(year, month, day);
		}

		public final EmphasizedNumber calculate() {

			final int ancestor = stepOne();
			final int source = stepTwo();
			final int develop = stepThree();
			final int mission = stepFour();

			return new EmphasizedNumber(develop, mission, ancestor, source);
		}
	}

	protected static final class HistoricalDateCalculator extends BaseDateCalculator {

		public HistoricalDateCalculator(int year, int month, int day) {
			super(year, month, day);
		}

		@Override
		public int stepOne() {
			return super.stepOne() + 1;
		}

		public final EmphasizedNumber calculate() {

			final int ancestor = stepOne();
			final int source = stepTwo();
			final int develop = stepThree();
			final int mission = stepFour();

			return new EmphasizedNumber(develop, mission, ancestor, source);
		}
	}

	protected class BaseDateData extends BaseData {

		private final Success success;
		private final Energetics energetics;

		BaseDateData(int[] matrix, EmphasizedNumber emphasized, Stability stability, SpiritualDominance dominance, Success success, Energetics energetics) {
			super(matrix, emphasized, stability, dominance);
			this.success = success;
			this.energetics = energetics;
		}

		public Success getSuccess() {
			return success;
		}

		public Energetics getEnergetics() {
			return energetics;
		}
	}
   /*
    private class ChosenCalculator extends DateCalculator {
		private final int year;
		private final int month;
		private final int day;
		private final CalculationMethod method;

		public ChosenCalculator(int year, int month, int day, CalculationMethod method) {
			super(year, month, day, method);

			this.year = year;
			this.month = month;
			this.day = day;
			this.method = method;
		}

		public ChosenCalculator(Date date, CalculationMethod method) {
			super(date, method);

			year = date.getYear();
			month = date.getMonth();
			day = date.getDay();
			this.method = method;
		}

		public Data calc() {

			return calculation(year, month, day, method);

		}
	}  */
}
