package com.nightbird.universalnumerology.Data;



import com.nightbird.universalnumerology.Calculation.DateTransformator;

import java.util.ArrayList;

public class TransformState {

	private final ArrayList<DateTransformator.TransformType> transformType;

	public TransformState() {
		this.transformType = new ArrayList<DateTransformator.TransformType>();
	}

	private TransformState(ArrayList<DateTransformator.TransformType> transformType) {
		this.transformType = transformType;
	}

	// Make a clone of the object
	public static TransformState[] newInstance(TransformState[] transformState) {

		TransformState[] newState = new TransformState[10];

		for (int i = 0; i < transformState.length; i++) {
			ArrayList<DateTransformator.TransformType> newType = new ArrayList<DateTransformator.TransformType>();
			ArrayList<DateTransformator.TransformType> type = transformState[i].getTranformTypeList();
			for (DateTransformator.TransformType t : type) {
				newType.add(t);
			}

			TransformState state = new TransformState(newType);
			newState[i] = state;
		}

		return newState;
	}

	public ArrayList<DateTransformator.TransformType> getTranformTypeList() {
		return this.transformType;
	}

	public void addTransformState(DateTransformator.TransformType type) {
		this.transformType.add(type);
	}

	public void addTransformState(int count, DateTransformator.TransformType type) {

		for (int i = 0; i < count; i++) {
			transformType.add(type);
		}
	}

	public void deleteTransformState(DateTransformator.TransformType type) {
		transformType.remove(type);
	}

	public void deleteTransformState(int count, DateTransformator.TransformType type) {

		for (int i = 0; i < count; i++) {
			transformType.remove(type);
		}
	}

	public void deleteTransformState(int index) {

		if (index > transformType.size()) {
			throw new IndexOutOfBoundsException();
		}

		transformType.remove(index);
	}

	public void replaceTransformState(DateTransformator.TransformType from, DateTransformator.TransformType to) {

		transformType.remove(from);
		transformType.add(to);
	}

	public void replaceTransformState(int count1, DateTransformator.TransformType from, int count2, DateTransformator.TransformType to) {

		for (int i = 0; i < count1; i++) {
			transformType.remove(from);
		}

		for (int i = 0; i < count2; i++) {
			transformType.add(to);
		}
	}

	public int getCountForState(DateTransformator.TransformType type) {

		int count = 0;
		for (DateTransformator.TransformType item : transformType) {
			if (item == type) {
				count = count + 1;
			}
		}

		return count;
	}
  /*
    public int[] getStateIndex(DateTransformator.TransformType type) {
        int count = getCountForState(type);

        if (count == 0) {
            return null;
        }

        int[] indexes = new int[count + 1];

         int k = 0;

         for(int i = 0; i < transformType.size(); i++) {
            if(transformType.get(i).equals(type)) {
                indexes[k] = i;
                k = k + 1;
                }
         }

         return indexes;
    }*/
}
