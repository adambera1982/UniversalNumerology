package com.nightbird.universalnumerology.Data.Type;

public class Success {
	private final int success;

	public int get() {
		return success < 100 ? success : 100 - (success - 100);
	}

	public boolean isOverloaded() {
		return success > 100;
	}

	public Success(int success) {
		this.success = success;
	}

	public interface calculation {
		public Success calcSuccess(int matrix[], SpiritualDominance dominance, Energetics energetics);
	}
}
