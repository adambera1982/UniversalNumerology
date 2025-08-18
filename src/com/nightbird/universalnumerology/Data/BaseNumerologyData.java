package com.nightbird.universalnumerology.Data;


import com.nightbird.universalnumerology.Data.Type.*;

public abstract class BaseNumerologyData {

	private final int[] matrix;
	private final EmphasizedNumber emphasized;
	private final ChosenNumber[] chosenNumber;

	private final Stability stability;
	private final SpiritualDominance dominance;
	private final CalculationMethod method;

	public BaseNumerologyData(int[] matrix, EmphasizedNumber emphasized, ChosenNumber[] chosenNumber, Stability stability, SpiritualDominance dominance, CalculationMethod method) {
		this.matrix = matrix.clone();
		this.emphasized = emphasized;
		this.stability = stability;
		this.dominance = dominance;
		this.chosenNumber = chosenNumber;
		this.method = method;
	}

	public int[] getMatrix() {
		return matrix;
	}

	public EmphasizedNumber getEmphasized() {
		return emphasized;
	}

	public Stability getStability() {
		return stability;
	}

	public SpiritualDominance getDominance() {
		return dominance;
	}

	public ChosenNumber[] getChosenNumber() {
		return chosenNumber;
	}

	public CalculationMethod getMethod() {
		return method;
	}
}
