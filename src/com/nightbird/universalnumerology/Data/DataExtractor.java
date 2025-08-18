package com.nightbird.universalnumerology.Data;

import android.content.Context;
import com.nightbird.universalnumerology.Data.Type.EmphasizedNumber;
import com.nightbird.universalnumerology.Data.Type.Energetics;
import com.nightbird.universalnumerology.Data.Type.Stability;
import com.nightbird.universalnumerology.R;

import java.text.DecimalFormat;

/**
 * Created by Nightbird on 2014.02.09..
 */
public abstract class DataExtractor {

    public static String getEnergetics(BaseNumerologyData numerologyData, Energetics.Type energeticsType) {

        if (numerologyData.getClass() == DateNumerologyData.class) {
            return  getEnergetics((DateNumerologyData) numerologyData, energeticsType);
        } else if (numerologyData.getClass() == NumberNumerologyData.class) {
            return getEnergetics((NumberNumerologyData) numerologyData, energeticsType);
        }

        throw new IllegalStateException();

    }

    private static String getEnergetics(DateNumerologyData numerologyData, Energetics.Type energeticsType) {

        switch (energeticsType) {

            case ENERGETIC_POTENTIAL: {
                int value = (int) numerologyData.getEnergetics().get(Energetics.Type.ENERGETIC_POTENTIAL);
                return Integer.toString(value);
            }

            case GENERIC_ENERGETICS_LEVEL: {
                DecimalFormat formatter = new DecimalFormat("0.0");
                return formatter.format(numerologyData.getEnergetics().get(Energetics.Type.GENERIC_ENERGETICS_LEVEL));
            }

            case ENERGETIC_CYCLOGRAM: {
                int[] cyclogram = numerologyData.getEnergetics().getCyclogram();

                return numerologyData.getEnergetics().getCyclogramString();
            }
        }

        throw new IllegalArgumentException();
    }

    private static String getEnergetics(NumberNumerologyData numberNumerologyData, Energetics.Type energeticsType) {
        return "-";
    }

    public static String[] getSuccess(BaseNumerologyData numerologyData, Context context) {

        if (numerologyData.getClass() == DateNumerologyData.class) {
            return getSuccess((DateNumerologyData) numerologyData, context);
        } else if (numerologyData.getClass() == NumberNumerologyData.class) {
            return getSuccess((NumberNumerologyData) numerologyData, context);

        }

        throw new IllegalStateException();
    }

    private static String[] getSuccess(DateNumerologyData numerologyData, Context context) {

        String overloaded = context.getResources().getString(R.string.overloaded_string);
        String notOverloaded = context.getResources().getString(R.string.not_overloaded_string);

        String[] success = new String[2];
        success[0] = Integer.toString(numerologyData.getSuccess().get());
        success[1] = numerologyData.getSuccess().isOverloaded() ? overloaded : notOverloaded;

        return success;
    }

    private static String[] getSuccess(NumberNumerologyData numerologyData, Context context) {

        String[] success = new String[2];
        success[0] = "-";
        success[1] = "-";

        return success;
    }

    public static String getEmphasizedNumber(BaseNumerologyData numerologyData, EmphasizedNumber.Type type) {

        switch (type) {
            case DEVELOP:
                return Integer.toString(numerologyData.getEmphasized().get(EmphasizedNumber.Type.DEVELOP));
            case MISSION:
                return Integer.toString(numerologyData.getEmphasized().get(EmphasizedNumber.Type.MISSION));
            case ANCESTOR:
                return Integer.toString(numerologyData.getEmphasized().get(EmphasizedNumber.Type.ANCESTOR));
            case SOURCE:
                return Integer.toString(numerologyData.getEmphasized().get(EmphasizedNumber.Type.SOURCE));
        }

        throw new IllegalArgumentException();
    }

    public static String getStability(BaseNumerologyData numerologyData, Stability.Type type) {

        switch (type) {
            case SPIRITUAL_STABILITY:
                return Integer.toString(numerologyData.getStability().get(Stability.Type.SPIRITUAL_STABILITY));
            case PHYSICAL_STABILITY:
                return Integer.toString(numerologyData.getStability().get(Stability.Type.SPIRITUAL_STABILITY));
        }

        throw new IllegalArgumentException();
    }

    public static String getDominance(BaseNumerologyData numerologyData) {
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(numerologyData.getDominance().get());
    }

    public static String[] getChosenNumbers(BaseNumerologyData numerologyData, Context context) {

        String[] chosenCategory = context.getResources().getStringArray(R.array.chosen_category);

        int length = numerologyData.getMatrix().length;
        String[] chosenType = new String[length];

        for (int i = 0; i < numerologyData.getMatrix().length; i++) {
            int index = numerologyData.getChosenNumber()[i].ordinal();
            chosenType[i] = chosenCategory[index];
        }

        return chosenType;
    }

    public static TransformState[] getTransformState(BaseNumerologyData numerologyData) {

        if (numerologyData.getClass() == DateNumerologyData.class) {
           return getTransformState((DateNumerologyData) numerologyData);
        } else if (numerologyData.getClass() == NumberNumerologyData.class) {
           return getTransformState((NumberNumerologyData) numerologyData);
        }

        throw new IllegalStateException();
    }

    private static TransformState[] getTransformState(DateNumerologyData numerologyData) {
        return numerologyData.getTransformState();
    }

    private static TransformState[] getTransformState(NumberNumerologyData numerologyData) {

        TransformState[] transformStates = null;

        for (int i = 0; i < numerologyData.getMatrix().length; i++) {
            transformStates[i] = new TransformState();
            for (int j = 0; j < numerologyData.getMatrix()[i]; j++)
                transformStates[i].addTransformState(null);
        }

        return transformStates;
    }
}
