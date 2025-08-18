package com.nightbird.universalnumerology.Data;

import com.nightbird.universalnumerology.Data.Type.*;

public class NumberNumerologyData extends BaseNumerologyData {

	private NumberEvent numberEvent = null;

	public NumberNumerologyData(Builder builder) {
		super(builder.matrix, builder.emphasized, builder.chosenNumber, builder.stability, builder.dominance, builder.method);
		this.numberEvent = builder.numberEvent;
	}

	public NumberEvent getNumberEvent() {
		return numberEvent;
	}

	public void setNumberEvent(NumberEvent numberEvent) {
		this.numberEvent = numberEvent;
	}

	public static class Builder {

		private final int[] matrix;
		private final EmphasizedNumber emphasized;
		private final ChosenNumber[] chosenNumber;
		private final Stability stability;
		private final SpiritualDominance dominance;
		private final CalculationMethod method;
		private NumberEvent numberEvent;

		public Builder(int[] matrix, EmphasizedNumber emphasized, ChosenNumber[] chosen, Stability stability, SpiritualDominance dominance, CalculationMethod method) {
			this.matrix = matrix.clone();
			this.emphasized = emphasized;
			this.chosenNumber = ChosenNumber.newInstance(chosen);
			this.stability = stability;
			this.dominance = dominance;
			this.method = method;
		}

		public Builder numberEvent(NumberEvent event) {
			this.numberEvent = event;
			return this;
		}

		public NumberNumerologyData build() {
			return new NumberNumerologyData(this);
		}
	}

	public static class NumberEvent {

		private final String name;
		private final int[] numberStream;
		private final CalculationMethod method;


		public NumberEvent(int[] numberStream, String name, CalculationMethod method) {
			this.numberStream = numberStream;
			this.name = name;
			this.method = method;
		}

		public String getName() {
			return name;
		}

		public int[] getNumberStream() {
			return numberStream;
		}

		public String getNumberStreamString() {

			StringBuilder builder = new StringBuilder(numberStream.length);
			for (int i : numberStream) {
				builder.append(Integer.toString(i));
			}

			return builder.toString();
		}

		public CalculationMethod getMethod() {
			return method;
		}
	}
}
