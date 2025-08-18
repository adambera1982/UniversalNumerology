package com.nightbird.universalnumerology.Data.Type;

public class EmphasizedNumber {

	private final int develop;
	private final int mission;
	private final int ancestor;
	private final int source;

	public EmphasizedNumber(int develop, int mission, int ancestor, int source) {
		this.develop = develop;
		this.mission = mission;
		this.ancestor = ancestor;
		this.source = source;
	}

	public int get(Type type) {

		switch (type) {
			case DEVELOP:
				return develop;
			case MISSION:
				return mission;
			case ANCESTOR:
				return ancestor;
			case SOURCE:
				return source;
		}

		throw new IllegalArgumentException("Error in EmphasizedNumber.get");
	}

	public enum Type {
		DEVELOP, MISSION, ANCESTOR, SOURCE
	}
}
