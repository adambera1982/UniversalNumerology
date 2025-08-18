package com.nightbird.universalnumerology.Data;

import com.nightbird.universalnumerology.Data.Type.*;
import com.nightbird.universalnumerology.Date.Date;
import com.nightbird.universalnumerology.Date.SimpleDate;


public class DateNumerologyData extends BaseNumerologyData {

	private final Energetics energetics;
	private final Success success;
	private TransformState[] transformStates;
	private DateEvent dateEvent = null;

	private DateNumerologyData(Builder builder) {
		super(builder.matrix, builder.emphasized, builder.chosenNumber, builder.stability, builder.dominance, builder.method);
		this.energetics = builder.energetics;
		this.success = builder.success;
		this.transformStates = builder.transformStates;

		if (builder.transformStates == null) {
			transformStates = new TransformState[10];

			for (int i = 0; i < getMatrix().length; i++) {
				transformStates[i] = new TransformState();
				for (int j = 0; j < getMatrix()[i]; j++)
					transformStates[i].addTransformState(null);
			}
		}
	}

	public Energetics getEnergetics() {
		return energetics;
	}

	public Success getSuccess() {
		return success;
	}

	public DateEvent getDateEvent() {
		return dateEvent;
	}

	public void setDateEvent(DateEvent dateEvent) {
		this.dateEvent = dateEvent;
	}

	public TransformState[] getTransformState() {
		return transformStates;
	}

	public static class Builder {

		private final int[] matrix;
		private final EmphasizedNumber emphasized;
		private final ChosenNumber[] chosenNumber;
		private final Stability stability;
		private final SpiritualDominance dominance;
		private final CalculationMethod method;
		private DateEvent dateEvent;
		private Energetics energetics = null;
		private Success success = null;
		private TransformState[] transformStates = null;

		public Builder(int[] matrix, EmphasizedNumber emphasized, ChosenNumber[] chosen, Stability stability, SpiritualDominance dominance, CalculationMethod method) {
			this.matrix = matrix.clone();
			this.emphasized = emphasized;
			this.chosenNumber = ChosenNumber.newInstance(chosen);
			chosenNumber[0] = ChosenNumber.ASCENDING;
			this.stability = stability;
			this.dominance = dominance;
			this.method = method;
		}

		public Builder energetics(Energetics energetics) {

			this.energetics = energetics;
			return this;
		}

		public Builder success(Success success) {

			this.success = success;
			return this;
		}

		public Builder dateEvent(DateEvent event) {

			this.dateEvent = event;
			return this;
		}

		public Builder transformState(TransformState[] state) {
			this.transformStates = state;

			return this;
		}

		public DateNumerologyData build() {
			return new DateNumerologyData(this);
		}
	}

	public static class DateEvent {

		private final int year;
		private final int month;
		private final int day;
		private final String name;
		private final EventType type;
		private final CalculationMethod method;

		public DateEvent(int year, int month, int day, String name, EventType type, CalculationMethod method) {
			this.year = year;
			this.month = month;
			this.day = day;
			this.name = name;
			this.type = type;
			this.method = method;
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

		public Date getDate() {
			return new SimpleDate(year, month, day);
		}

		public CalculationMethod getMethod() {
			return method;
		}

		public EventType getEventType() {
			return type;
		}

		public String getName() {
			return name;
		}
	}
}
