package com.nightbird.universalnumerology.Calculation;



import com.nightbird.universalnumerology.Data.DateNumerologyData;
import com.nightbird.universalnumerology.Data.TransformState;
import com.nightbird.universalnumerology.Data.Type.CalculationMethod;
import com.nightbird.universalnumerology.Data.Type.Energetics;
import com.nightbird.universalnumerology.Data.Type.Success;
import com.nightbird.universalnumerology.Date.Date;

import java.util.HashSet;
import java.util.Set;

public class DateTransformator extends DateCalculator {

	private final DateNumerologyData origData;
	private final int year;
	private final int month;
	private final int day;
	private final CalculationMethod method;
	private final int simulateNumber;
	private final TransformType transformType;
	private final TransformState[] transformState;

	public DateTransformator(DateNumerologyData data, TransformType transformType) {
		super(data.getDateEvent().getDate(), data.getMethod());

		Date date = data.getDateEvent().getDate();

		this.year = date.getYear();
		this.month = date.getMonth();
		this.day = date.getDay();

		this.origData = data;
		this.method = data.getMethod();
		this.simulateNumber = 0;
		this.transformType = transformType;
		this.transformState = data.getTransformState();
		//this.transformState = TransformState.newInstance(data.getTransformState());
	}

	public DateTransformator(DateNumerologyData data, int simulateNumber) {
		super(data.getDateEvent().getDate(), data.getMethod());

		Date date = data.getDateEvent().getDate();

		this.year = date.getYear();
		this.month = date.getMonth();
		this.day = date.getDay();

		this.origData = data;
		this.method = data.getMethod();
		this.simulateNumber = simulateNumber;
		this.transformType = TransformType.SIMULATION;
		this.transformState = data.getTransformState();
		//this.transformState = TransformState.newInstance(data.getTransformState());
	}

	public static Set<TransformType> canTransform(int[] matrix, TransformState[] state) {

		Set<TransformType> transforms = new HashSet<TransformType>();

		if (matrix[1] >= 2) {

			if (state[1].getCountForState(null) >= 2) {
				transforms.add(TransformType.ONE_EIGHT_FOUR);
				transforms.add(TransformType.ONE_EIGHT_TWO);
			} else {

				if (state[1].getCountForState(TransformType.EIGHT_ONE_FOUR) >= 2) {
					transforms.add(TransformType.ONE_EIGHT_FOUR);
				}

				if (state[1].getCountForState(TransformType.EIGHT_ONE_TWO) >= 2) {
					transforms.add(TransformType.ONE_EIGHT_TWO);
				}
			}
		}

		if (matrix[8] >= 1 && matrix[2] >= 2) {
			if (state[8].getCountForState(null) >= 1 && state[2].getCountForState(null) >= 2) {
				transforms.add(TransformType.EIGHT_ONE_TWO);
			} else if (state[8].getCountForState(TransformType.ONE_EIGHT_TWO) >= 1) {
				transforms.add(TransformType.EIGHT_ONE_TWO);
			}
		}

		if (matrix[8] >= 1 && matrix[4] >= 1) {
			if (state[8].getCountForState(null) >= 1 && state[4].getCountForState(null) >= 1) {
				transforms.add(TransformType.EIGHT_ONE_FOUR);
			} else if (state[8].getCountForState(TransformType.ONE_EIGHT_FOUR) >= 1) {
				transforms.add(TransformType.EIGHT_ONE_FOUR);
			}
		}

		if (matrix[6] >= 1) {
			if (state[6].getCountForState(null) >= 1) {
				transforms.add(TransformType.SIX_SEVEN_FOUR);
				transforms.add(TransformType.SIX_SEVEN_TWO);
			} else {
				if (state[6].getCountForState(TransformType.SEVEN_SIX_TWO) >= 1) {
					transforms.add(TransformType.SIX_SEVEN_TWO);
				}

				if (state[6].getCountForState(TransformType.SEVEN_SIX_FOUR) >= 1) {
					transforms.add(TransformType.SIX_SEVEN_FOUR);
				}
			}
		}

		if (matrix[7] >= 1 && matrix[2] >= 2) {
			if (state[7].getCountForState(null) >= 1 && state[2].getCountForState(null) >= 2) {
				transforms.add(TransformType.SEVEN_SIX_TWO);
			} else if (state[7].getCountForState(TransformType.SIX_SEVEN_TWO) >= 1) {
				transforms.add(TransformType.SEVEN_SIX_TWO);
			}
		}

		if (matrix[7] >= 1 && matrix[4] >= 1) {
			if (state[7].getCountForState(null) >= 1 && state[4].getCountForState(null) >= 1) {
				transforms.add(TransformType.SEVEN_SIX_FOUR);
			} else if (state[7].getCountForState(TransformType.SIX_SEVEN_FOUR) >= 1) {
				transforms.add(TransformType.SEVEN_SIX_FOUR);
			}
		}

		if (matrix[4] >= 1) {
			if (state[4].getCountForState(null) >= 1) {
				transforms.add(TransformType.FOUR_TWO);
			} else if (state[4].getCountForState(TransformType.TWO_FOUR) >= 1) {
				transforms.add(TransformType.FOUR_TWO);
			}
		}

		if (matrix[2] >= 2) {
			if (state[2].getCountForState(null) >= 2) {
				transforms.add(TransformType.TWO_FOUR);
			} else if (state[2].getCountForState(TransformType.FOUR_TWO) >= 2) {
				transforms.add(TransformType.TWO_FOUR);
			}
		}

		if (matrix[9] >= 2) {
			if (state[9].getCountForState(null) >= 2) {
				transforms.add(TransformType.NINE_FIVE);
			} else if (state[9].getCountForState(TransformType.FIVE_NINE) >= 2) {
				transforms.add(TransformType.NINE_FIVE);
			}
		}

		if (matrix[5] >= 2) {
			if (state[5].getCountForState(null) >= 2) {
				transforms.add(TransformType.FIVE_NINE);
			} else if (state[5].getCountForState(TransformType.NINE_FIVE) >= 2) {
				transforms.add(TransformType.FIVE_NINE);
			}
		}

		return transforms;
	}

	public static Set<TransformType> selectTransforms(int numberIndex, Set<TransformType> types) {

		Set<TransformType> selected = new HashSet<TransformType>();

		switch (numberIndex) {
			case 1: {
				if (types.contains(TransformType.ONE_EIGHT_TWO)) {
					selected.add(TransformType.ONE_EIGHT_TWO);
				}
				if (types.contains(TransformType.ONE_EIGHT_FOUR)) {
					selected.add(TransformType.ONE_EIGHT_FOUR);
				}

				return selected;
			}

			case 2: {
				if (types.contains(TransformType.TWO_FOUR)) {
					selected.add(TransformType.TWO_FOUR);
				}

				return selected;
			}

			case 4: {
				if (types.contains(TransformType.FOUR_TWO)) {
					selected.add(TransformType.FOUR_TWO);
				}

				return selected;
			}

			case 5: {
				if (types.contains(TransformType.FIVE_NINE)) {
					selected.add(TransformType.FIVE_NINE);
				}

				return selected;
			}

			case 6: {
				if (types.contains(TransformType.SIX_SEVEN_TWO)) {
					selected.add(TransformType.SIX_SEVEN_TWO);
				}

				if (types.contains(TransformType.SIX_SEVEN_FOUR)) {
					selected.add(TransformType.SIX_SEVEN_FOUR);
				}

				return selected;
			}

			case 7: {
				if (types.contains(TransformType.SEVEN_SIX_TWO)) {
					selected.add(TransformType.SEVEN_SIX_TWO);
				}
				if (types.contains(TransformType.SEVEN_SIX_FOUR)) {
					selected.add(TransformType.SEVEN_SIX_FOUR);
				}

				return selected;
			}

			case 8: {
				if (types.contains(TransformType.EIGHT_ONE_TWO)) {
					selected.add(TransformType.EIGHT_ONE_TWO);
				}

				if (types.contains(TransformType.EIGHT_ONE_FOUR)) {
					selected.add(TransformType.EIGHT_ONE_FOUR);
				}

				return selected;
			}

			case 9: {
				if (types.contains(TransformType.NINE_FIVE)) {
					selected.add(TransformType.NINE_FIVE);
				}

				return selected;
			}

		}

		// don't return null, as in case for example 3, there is no transform
		// and the activity does not check for null returns. Why should it?
		return selected;
	}

	public DateNumerologyData transform() {

		BaseDateData base = calculation();
		TransformState[] newState = transformState();

		return new DateNumerologyData.Builder(base.getMatrix(), origData.getEmphasized(), origData.getChosenNumber(), base.getStability(), base.getDominance(), method).
				energetics(origData.getEnergetics()).success(base.getSuccess()).dateEvent(origData.getDateEvent()).transformState(newState).build();
	}

	private BaseDateData calculation() {

		BaseDateData data = null;

		int[] newMatrix = transformMatrix(origData.getMatrix());

		switch (method) {
			case NORMAL_DATE: {
				NormalDateCalculator dateCalc = new NormalDateCalculator(year, month, day);
				BaseData baseData = super.baseCalculation(newMatrix);

				//Energetics energetics = dateCalc.calcEnergetics();
				Energetics energetics = origData.getEnergetics();
				Success success = dateCalc.calcSuccess(newMatrix, baseData.getDominance(), energetics);

				data = new BaseDateData(baseData.getMatrix(), null, baseData.getStability(), baseData.getDominance(), success, energetics);

				break;
			}

			case HISTORICAL_DATE: {
				HistoricalDateCalculator dateCalc = new HistoricalDateCalculator(year, month, day);
				BaseData baseData = super.baseCalculation(newMatrix);

				data = new BaseDateData(baseData.getMatrix(), null, baseData.getStability(), baseData.getDominance(), null, null);

				break;
			}

			case ARTIFICIAL_DATE: {
				ArtificialDateCalculator dateCalc = new ArtificialDateCalculator(year, month, day);
				BaseData baseData = super.baseCalculation(newMatrix);

				data = new BaseDateData(baseData.getMatrix(), null, baseData.getStability(), baseData.getDominance(), null, null);

				break;
			}
		}

		return data;
	}

	private TransformState[] transformState() {

		TransformState[] newState = TransformState.newInstance(this.transformState);

		switch (transformType) {

			case ONE_EIGHT_TWO: {
				if (newState[1].getCountForState(TransformType.EIGHT_ONE_TWO) == 2) {
					newState[1].deleteTransformState(2, TransformType.EIGHT_ONE_TWO);
					newState[8].replaceTransformState(TransformType.EIGHT_ONE_TWO, null);
					newState[2].replaceTransformState(2, TransformType.EIGHT_ONE_TWO, 2, null);
				} else {
					newState[1].replaceTransformState(2, null, 2, TransformType.ONE_EIGHT_TWO);

					newState[8].addTransformState(TransformType.ONE_EIGHT_TWO);
					newState[2].addTransformState(2, TransformType.ONE_EIGHT_TWO);
				}
				return newState;
			}
			case ONE_EIGHT_FOUR: {
				if (newState[1].getCountForState(TransformType.EIGHT_ONE_FOUR) == 2) {
					newState[1].deleteTransformState(2, TransformType.EIGHT_ONE_FOUR);
					newState[8].replaceTransformState(TransformType.EIGHT_ONE_FOUR, null);
					newState[4].replaceTransformState(TransformType.EIGHT_ONE_FOUR, null);
				} else {
					newState[1].replaceTransformState(2, null, 2, TransformType.ONE_EIGHT_FOUR);

					newState[8].addTransformState(TransformType.ONE_EIGHT_FOUR);
					newState[4].addTransformState(TransformType.ONE_EIGHT_FOUR);
				}
				return newState;
			}
			case EIGHT_ONE_TWO: {
				if (newState[8].getCountForState(TransformType.ONE_EIGHT_TWO) == 1) {
					newState[8].deleteTransformState(TransformType.ONE_EIGHT_TWO);
					newState[2].deleteTransformState(TransformType.ONE_EIGHT_TWO);
					newState[1].replaceTransformState(2, TransformType.ONE_EIGHT_TWO, 2, null);
				} else {
					newState[8].replaceTransformState(null, TransformType.EIGHT_ONE_TWO);
					newState[2].replaceTransformState(2, null, 2, TransformType.EIGHT_ONE_TWO);
					newState[1].addTransformState(2, TransformType.EIGHT_ONE_TWO);
				}

				return newState;
			}
			case EIGHT_ONE_FOUR: {
				if (newState[8].getCountForState(TransformType.ONE_EIGHT_FOUR) == 1) {
					newState[8].deleteTransformState(TransformType.ONE_EIGHT_FOUR);
					newState[4].deleteTransformState(TransformType.ONE_EIGHT_FOUR);
					newState[1].replaceTransformState(2, TransformType.ONE_EIGHT_FOUR, 2, null);

				} else {
					newState[8].replaceTransformState(null, TransformType.EIGHT_ONE_FOUR);
					newState[4].replaceTransformState(null, TransformType.EIGHT_ONE_FOUR);
					newState[1].addTransformState(2, TransformType.EIGHT_ONE_FOUR);
				}


				return newState;
			}
			case SIX_SEVEN_TWO: {
				if (newState[6].getCountForState(TransformType.SEVEN_SIX_TWO) == 1) {
					newState[6].deleteTransformState(TransformType.SEVEN_SIX_TWO);
					newState[7].replaceTransformState(TransformType.SEVEN_SIX_FOUR, null);
					newState[2].replaceTransformState(2, TransformType.SEVEN_SIX_FOUR, 2, null);
				} else {
					newState[6].replaceTransformState(null, TransformType.SIX_SEVEN_TWO);

					newState[7].addTransformState(TransformType.SIX_SEVEN_TWO);
					newState[2].addTransformState(2, TransformType.SIX_SEVEN_TWO);
				}
				return newState;
			}
			case SIX_SEVEN_FOUR: {
				if (newState[6].getCountForState(TransformType.SEVEN_SIX_FOUR) == 1) {
					newState[6].deleteTransformState(TransformType.SEVEN_SIX_FOUR);
					newState[7].replaceTransformState(TransformType.SEVEN_SIX_FOUR, null);
					newState[4].replaceTransformState(TransformType.SEVEN_SIX_FOUR, null);
				} else {
					newState[6].replaceTransformState(null, TransformType.SIX_SEVEN_FOUR);

					newState[7].addTransformState(TransformType.SIX_SEVEN_FOUR);
					newState[4].addTransformState(TransformType.SIX_SEVEN_FOUR);
				}
				return newState;
			}
			case SEVEN_SIX_TWO: {
				if (newState[7].getCountForState(TransformType.SIX_SEVEN_TWO) == 1) {
					newState[7].deleteTransformState(TransformType.SIX_SEVEN_TWO);
					newState[2].deleteTransformState(2, TransformType.SIX_SEVEN_TWO);
					newState[6].replaceTransformState(TransformType.SIX_SEVEN_TWO, null);
				} else {
					newState[7].replaceTransformState(null, TransformType.SEVEN_SIX_TWO);
					newState[2].replaceTransformState(2, null, 2, TransformType.SEVEN_SIX_TWO);
					newState[6].addTransformState(TransformType.SEVEN_SIX_TWO);
				}

				return newState;
			}

			case SEVEN_SIX_FOUR: {
				if (newState[7].getCountForState(TransformType.SIX_SEVEN_FOUR) == 1) {
					newState[7].deleteTransformState(TransformType.SIX_SEVEN_FOUR);
					newState[4].deleteTransformState(TransformType.SIX_SEVEN_FOUR);
					newState[6].replaceTransformState(TransformType.SIX_SEVEN_FOUR, null);
				} else {
					newState[7].replaceTransformState(null, TransformType.SEVEN_SIX_FOUR);
					newState[4].replaceTransformState(null, TransformType.SEVEN_SIX_FOUR);
					newState[6].addTransformState(TransformType.SEVEN_SIX_FOUR);
				}

				return newState;
			}
			case TWO_FOUR: {
				if (newState[2].getCountForState(TransformType.FOUR_TWO) == 2) {
					newState[2].deleteTransformState(2, TransformType.FOUR_TWO);
					newState[4].replaceTransformState(TransformType.FOUR_TWO, null);
				} else {
					newState[2].replaceTransformState(2, null, 2, TransformType.TWO_FOUR);
					newState[4].addTransformState(TransformType.TWO_FOUR);
				}

				return newState;
			}
			case FOUR_TWO: {
				if (newState[4].getCountForState(TransformType.TWO_FOUR) == 1) {
					newState[4].deleteTransformState(TransformType.TWO_FOUR);
					newState[2].replaceTransformState(2, TransformType.FOUR_TWO, 2, null);
				} else {
					newState[4].replaceTransformState(null, TransformType.FOUR_TWO);
					newState[2].addTransformState(2, TransformType.FOUR_TWO);
				}
				return newState;
			}
			case FIVE_NINE: {
				if (newState[5].getCountForState(TransformType.NINE_FIVE) == 1) {
					newState[5].replaceTransformState(2, TransformType.NINE_FIVE, 2, null);
				} else {
					newState[5].replaceTransformState(2, null, 2, TransformType.FIVE_NINE);
					newState[9].addTransformState(TransformType.FIVE_NINE);
				}

				return newState;
			}
			case NINE_FIVE: {
				if (newState[9].getCountForState(TransformType.FIVE_NINE) == 1) {
					newState[9].replaceTransformState(2, TransformType.FIVE_NINE, 2, null);
				} else {
					newState[9].replaceTransformState(2, null, 2, TransformType.NINE_FIVE);
					newState[5].addTransformState(TransformType.NINE_FIVE);
				}

				return newState;
			}
			case SIMULATION: {
				newState[simulateNumber].addTransformState(TransformType.SIMULATION);

				return newState;
			}
		}

		throw new IllegalArgumentException();
	}

	private int[] transformMatrix(int[] matrix) {

		int[] newMatrix = matrix.clone();

		if (transformType != TransformType.SIMULATION) {
			Set<TransformType> transforms = canTransform(newMatrix, transformState);
			if (!transforms.contains(transformType)) {
				throw new IllegalStateException("Can't transform matrix!");
			}
		}

		switch (transformType) {
			case ONE_EIGHT_TWO: {
				newMatrix[1] = newMatrix[1] - 2;
				newMatrix[8] = newMatrix[8] + 1;
				newMatrix[2] = newMatrix[2] + 2;
				return newMatrix;
			}
			case ONE_EIGHT_FOUR: {
				newMatrix[1] = newMatrix[1] - 2;
				newMatrix[8] = newMatrix[8] + 1;
				newMatrix[4] = newMatrix[4] + 1;
				return newMatrix;
			}
			case EIGHT_ONE_TWO: {
				newMatrix[8] = newMatrix[8] - 1;
				newMatrix[1] = newMatrix[1] + 2;
				newMatrix[2] = newMatrix[2] - 2;
				return newMatrix;
			}
			case EIGHT_ONE_FOUR: {
				newMatrix[8] = newMatrix[8] - 1;
				newMatrix[1] = newMatrix[1] + 2;
				newMatrix[4] = newMatrix[4] - 1;
				return newMatrix;
			}
			case SIX_SEVEN_TWO: {
				newMatrix[6] = newMatrix[6] - 1;
				newMatrix[7] = newMatrix[7] + 1;
				newMatrix[2] = newMatrix[2] + 2;
				return newMatrix;
			}
			case SIX_SEVEN_FOUR: {
				newMatrix[6] = newMatrix[6] - 1;
				newMatrix[7] = newMatrix[7] + 1;
				newMatrix[4] = newMatrix[4] + 1;
				return newMatrix;
			}
			case SEVEN_SIX_TWO: {
				newMatrix[7] = newMatrix[7] - 1;
				newMatrix[6] = newMatrix[6] + 1;
				newMatrix[2] = newMatrix[2] - 2;
				return newMatrix;
			}
			case SEVEN_SIX_FOUR: {
				newMatrix[7] = newMatrix[7] - 1;
				newMatrix[6] = newMatrix[6] + 1;
				newMatrix[4] = newMatrix[4] - 1;
				return newMatrix;
			}
			case FOUR_TWO: {
				newMatrix[4] = newMatrix[4] - 1;
				newMatrix[2] = newMatrix[2] + 2;
				return newMatrix;
			}
			case TWO_FOUR: {
				newMatrix[2] = newMatrix[2] - 2;
				newMatrix[4] = newMatrix[4] + 1;
				return newMatrix;
			}

			case NINE_FIVE: {
				newMatrix[5] = newMatrix[5] + 1;
				return newMatrix;
			}
			case FIVE_NINE:
				newMatrix[9] = newMatrix[9] + 1;
				return newMatrix;
			case SIMULATION:
				newMatrix[simulateNumber] = newMatrix[simulateNumber] + 1;
				return newMatrix;
		}

		throw new IllegalArgumentException();
	}

	public static enum TransformType {
		ONE_EIGHT_TWO, ONE_EIGHT_FOUR,
		EIGHT_ONE_TWO, EIGHT_ONE_FOUR,
		SIX_SEVEN_TWO, SIX_SEVEN_FOUR,
		SEVEN_SIX_TWO, SEVEN_SIX_FOUR,
		TWO_FOUR, FOUR_TWO,
		FIVE_NINE, NINE_FIVE,
		SIMULATION
	}
}
