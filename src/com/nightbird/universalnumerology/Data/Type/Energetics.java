package com.nightbird.universalnumerology.Data.Type;

public class Energetics {

	private final int potential;
	private final float level;
	private final int[] cyclogram;

	public Energetics(int potential, float level, int[] cyclogram) {
		this.potential = potential;
		this.level = level;
		this.cyclogram = cyclogram;
	}

	public float get(Type type) {

		switch (type) {
			case ENERGETIC_POTENTIAL: {
				return potential;
			}
			case GENERIC_ENERGETICS_LEVEL: {
				return level;
			}
		}

		throw new IllegalArgumentException("Error returning energetics");
	}

	public enum Type {
		ENERGETIC_POTENTIAL, GENERIC_ENERGETICS_LEVEL, ENERGETIC_CYCLOGRAM
	}

	public interface Calculation {
		public Energetics calcEnergetics();
	}

	public int[] getCyclogram() {
		return cyclogram;
	}

	public String getCyclogramString() {
		StringBuilder string = new StringBuilder(cyclogram.length);

		for (int i : cyclogram) {
			string.append(i).append(" ");
		}

		return string.toString();

	}

	public interface Data {
		public float get(Type type);

		public int[] getCyclogram();
	}

}
