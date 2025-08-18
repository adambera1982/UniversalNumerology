package com.nightbird.universalnumerology.Data.Type;

public class Stability {

	private final int spiritualStability;
	private final int physicalStability;

	public Stability(int spiritualStability, int physicalStability) {
		this.spiritualStability = spiritualStability;
		this.physicalStability = physicalStability;
	}

	public int get(Type type) {

		switch (type) {
			case SPIRITUAL_STABILITY:
				return spiritualStability;
			case PHYSICAL_STABILITY:
				return physicalStability;
		}

		throw new IllegalArgumentException("Error in Stability.get");
	}

	public enum Type {
		SPIRITUAL_STABILITY, PHYSICAL_STABILITY
	}
}
