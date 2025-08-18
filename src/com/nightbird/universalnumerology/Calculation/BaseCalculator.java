package com.nightbird.universalnumerology.Calculation;


import com.nightbird.universalnumerology.Calculation.Support.Maths;
import com.nightbird.universalnumerology.Data.Type.ChosenNumber;
import com.nightbird.universalnumerology.Data.Type.EmphasizedNumber;
import com.nightbird.universalnumerology.Data.Type.SpiritualDominance;
import com.nightbird.universalnumerology.Data.Type.Stability;

public abstract class BaseCalculator {

	private final int[] matrix = new int[10];

	public int[] getMatrix() {
		return matrix;
	}

	protected void addToMatrix(int... number) {

		for (int aNumber : number) {
			int digits[] = Maths.separateDigits(aNumber);
			for (int digit : digits) {
				matrix[digit] = matrix[digit] + 1;
			}
		}
	}

	protected void addEmphasizedNumberToMatrix(EmphasizedNumber data) {

		int develop = data.get(EmphasizedNumber.Type.DEVELOP);
		int mission = data.get(EmphasizedNumber.Type.MISSION);
		int ancestor = data.get(EmphasizedNumber.Type.ANCESTOR);
		int source = data.get(EmphasizedNumber.Type.SOURCE);

		addToMatrix(develop, mission, ancestor, source);
	}

	protected BaseData baseCalculation() {

		return baseCalculation(this.matrix);
	}

	protected BaseData baseCalculation(int[] matrix) {

		Stability stability = new StabilityCalculator(matrix).calculate();

		SpiritualDominance dominance = new SpiritualDominanceCalculator(stability).calculate();

		return new BaseData(matrix, null, stability, dominance);
	}

	protected ChosenNumber[] getChosenNumber(int[] prevMatrix, int[] nextMatrix) {

		int[] prev = prevMatrix.clone();
		int[] next = nextMatrix.clone();

		ChosenNumber[] chosenNumberType = new ChosenNumber[10];

		for (int i = 0; i < 10; i++) {
			if (prev[i] == matrix[i] && matrix[i] == next[i]) {
				chosenNumberType[i] = ChosenNumber.STABLE;
			} else if (prev[i] < matrix[i] && matrix[i] < next[i]) {
				chosenNumberType[i] = ChosenNumber.ASCENDING;
			} else if (prev[i] > matrix[i] && matrix[i] > next[i]) {
				chosenNumberType[i] = ChosenNumber.DESCENDING;
			} else if (prev[i] < matrix[i] && matrix[i] > next[i]) {
				chosenNumberType[i] = ChosenNumber.MOUNTAIN;
			} else if (prev[i] > matrix[i] && matrix[i] < next[i]) {
				chosenNumberType[i] = ChosenNumber.VALLEY;
			} else if (prev[i] == matrix[i] && matrix[i] < next[i]) {
				chosenNumberType[i] = ChosenNumber.RUNNER_UP;
			} else if (prev[i] > matrix[i] && matrix[i] == next[i]) {
				chosenNumberType[i] = ChosenNumber.HOLDER;
			} else if (prev[i] == matrix[i] && matrix[i] > next[i]) {
				chosenNumberType[i] = ChosenNumber.RUNNER_DOWN;
			} else if (prev[i] < matrix[i] && matrix[i] == next[i]) {
				chosenNumberType[i] = ChosenNumber.KEEPER;
			}
		}

		return chosenNumberType;
	}

	private static final class StabilityCalculator {

		private final int[] matrix;

		public StabilityCalculator(int[] matrix) {
			this.matrix = matrix.clone();
		}

		public final Stability calculate() {

			final int spiritualStability = calcSpiritualStability();
			final int physicalStability = calcPhysicalStability();

			return new Stability(spiritualStability, physicalStability);
		}

		private int calcSpiritualStability() {
			return (matrix[1] + matrix[5] + matrix[9]) *
					(matrix[1] + matrix[4] + matrix[7]) *
					(matrix[1] + matrix[2] + matrix[3]);
		}

		private int calcPhysicalStability() {
			return (matrix[3] + matrix[5] + matrix[7]) *
					(matrix[2] + matrix[5] + matrix[8]) *
					(matrix[3] + matrix[6] + matrix[9]);
		}
	}

	private static final class SpiritualDominanceCalculator {

		private final Stability stability;

		public SpiritualDominanceCalculator(Stability stability) {
			this.stability = stability;
		}

		public SpiritualDominance calculate() {
			final float spiritualDominance = calcSpiritualDominance();

			return new SpiritualDominance(spiritualDominance);
		}

		private float calcSpiritualDominance() {

			final int spiritualStability = stability.get(Stability.Type.SPIRITUAL_STABILITY);
			final int physicalStability = stability.get(Stability.Type.PHYSICAL_STABILITY);

			float dominance;

			if (spiritualStability == 0 || physicalStability == 0) {
				dominance = 0.0f;
			} else {
				dominance = (float) spiritualStability / (float) physicalStability;
			}

			return dominance;
		}
	}

	protected class BaseData {

		private final int[] matrix;
		private final EmphasizedNumber emphasized;
		private final Stability stability;
		private final SpiritualDominance dominance;

		BaseData(int[] matrix, EmphasizedNumber emphasized, Stability stability, SpiritualDominance dominance) {
			this.matrix = matrix.clone();
			this.stability = stability;
			this.dominance = dominance;
			this.emphasized = emphasized;
		}

		public int[] getMatrix() {
			return matrix;
		}

		public Stability getStability() {
			return stability;
		}

		public SpiritualDominance getDominance() {
			return dominance;
		}

		public EmphasizedNumber getEmphasized() {
			return emphasized;
		}
	}
}
