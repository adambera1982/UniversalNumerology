package com.nightbird.universalnumerology.Calculation;


import com.nightbird.universalnumerology.Calculation.Support.Maths;
import com.nightbird.universalnumerology.Data.NumberNumerologyData;
import com.nightbird.universalnumerology.Data.Type.*;

public class NumberCalculator extends BaseCalculator {

	private final int[] numberStream;
	private final CalculationMethod method;

	public NumberCalculator(int[] numberStream, CalculationMethod method) {
		this.numberStream = numberStream.clone();

		this.method = method;
	}

	public final NumberNumerologyData calculate() {

		int[] prevMatrix = Maths.decreaseNumberStream(numberStream);
		int[] nextMatrix = Maths.increaseNumberStream(numberStream);

		BaseNumberData currNumber = calculation(numberStream, method);

		BaseNumberData prevNumber = new NumberCalculator(prevMatrix, method).calcChosenMatrices();
		BaseNumberData nextNumber = new NumberCalculator(nextMatrix, method).calcChosenMatrices();

		ChosenNumber[] chosen = getChosenNumber(prevNumber.getMatrix(), nextNumber.getMatrix());

		return new NumberNumerologyData.Builder(currNumber.getMatrix(), currNumber.getEmphasized(), chosen, currNumber.getStability(), currNumber.getDominance(), method).build();

		//return new NumberNumerologyData(currNumber.getMatrix(), chosen, currNumber.getStability(), currNumber.getDominance(), method);
	}

	private BaseNumberData calcChosenMatrices() {
		return calculation(numberStream, method);
	}

	private BaseNumberData calculation(int[] stream, CalculationMethod method) {

		addToMatrix(numberStream);
		EmphasizedNumber emphasized;

		BaseNumberData data = null;

		switch (method) {
			case NORMAL_NUMBER: {
				NormalNumberCalculator numberCalc = new NormalNumberCalculator(stream);
				emphasized = numberCalc.calculate();
				addEmphasizedNumberToMatrix(emphasized);

				BaseData baseData = super.baseCalculation();

				data = new BaseNumberData(baseData.getMatrix(), emphasized, baseData.getStability(), baseData.getDominance());

				break;
			}
			case ARTIFICIAL_NUMBER: {
				ArtificialNumberCalculator numberCalc = new ArtificialNumberCalculator(stream);
				emphasized = numberCalc.calculate();
				addEmphasizedNumberToMatrix(emphasized);

				BaseData baseData = super.baseCalculation();

				data = new BaseNumberData(baseData.getMatrix(), emphasized, baseData.getStability(), baseData.getDominance());
				break;
			}
		}

		return data;
	}

	private static abstract class BaseNumberCalculator implements Calculator {

		private final int[] numberStream;
		private int stepOneResult;
		private int stepTwoResult;
		private int stepThreeResult;
		private int stepFourResult;

		public BaseNumberCalculator(int[] numberStream) {
			this.numberStream = numberStream;
		}

		@Override
		public int stepOne() {

			stepOneResult = Maths.sumArrayToInt(numberStream);
			return stepOneResult;
		}

		@Override
		public int stepTwo() {

			stepTwoResult = Maths.sumOfInt(stepOneResult);
			return stepTwoResult;
		}

		@Override
		public int stepThree() {
			return 0;
		}

		@Override
		public int stepFour() {

			stepFourResult = Maths.sumOfInt(stepThreeResult);
			return stepFourResult;
		}

		public int[] getNumberStream() {
			return numberStream;
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

	private static final class NormalNumberCalculator extends BaseNumberCalculator {

		public NormalNumberCalculator(int[] numberStream) {
			super(numberStream);
		}

		@Override
		public int stepThree() {

			int number = getStepOneResult() - getNumberStream()[0] * 2;

			if (number < 0) {
				number = Math.abs(number);
			}

			setStepThreeResult(number);

			return number;
		}

		public EmphasizedNumber calculate() {

			final int develop = stepOne();
			final int mission = stepTwo();
			final int ancestor = stepThree();
			final int source = stepFour();

			return new EmphasizedNumber(develop, mission, ancestor, source);
		}
	}

	private static final class ArtificialNumberCalculator extends BaseNumberCalculator {

		public ArtificialNumberCalculator(int[] numberStream) {
			super(numberStream);
		}

		@Override
		public int stepThree() {

			int[] stream = getNumberStream();

			final int lastDigit = stream.length - 1;

			final int number = getStepOneResult() + stream[lastDigit] * 2;

			setStepThreeResult(number);

			return number;
		}

		public EmphasizedNumber calculate() {

			final int ancestor = stepOne();
			final int source = stepTwo();
			final int develop = stepThree();
			final int mission = stepFour();

			return new EmphasizedNumber(develop, mission, ancestor, source);
		}
	}

	private final class BaseNumberData extends BaseData {

		BaseNumberData(int[] matrix, EmphasizedNumber emphasized, Stability stability, SpiritualDominance dominance) {
			super(matrix, emphasized, stability, dominance);
		}
	}
}
