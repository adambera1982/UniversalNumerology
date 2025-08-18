package com.nightbird.universalnumerology.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.nightbird.universalnumerology.Data.BaseNumerologyData;
import com.nightbird.universalnumerology.Data.DataExtractor;
import com.nightbird.universalnumerology.Data.DateNumerologyData;
import com.nightbird.universalnumerology.Data.NumberNumerologyData;
import com.nightbird.universalnumerology.Data.Type.EmphasizedNumber;
import com.nightbird.universalnumerology.Data.Type.Energetics;
import com.nightbird.universalnumerology.Data.Type.Stability;
import com.nightbird.universalnumerology.Interfaces.DataRequestor;
import com.nightbird.universalnumerology.Mediator.Receiver;
import com.nightbird.universalnumerology.R;
import org.w3c.dom.Text;

/**
 * Created by Nightbird on 2014.02.14..
 */
public class DetailsFragment extends Fragment implements Receiver {

    private BaseNumerologyData mNumerologyData;
    private DataRequestor mDataRequestor;

    private String[] mGeneralCategory;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mDataRequestor = (DataRequestor)activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mDataRequestor = null;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.detailsview, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDataRequestor.requestData(this);

        loadCategoryStrings();

        setupGeneralCategoryStrings();
        setupGeneralDetails();
        setupEnergeticDetails();
        setupSuccessDetails();
        setupChosenDetails();
    }

    private void setupChosenDetails() {
        View view = getView();


        TextView textView = (TextView)view.findViewById(R.id.chosen_item_1);
        textView.setText(DataExtractor.getChosenNumbers(mNumerologyData, view.getContext())[0]);

        textView = (TextView)view.findViewById(R.id.chosen_item_2);
        textView.setText(DataExtractor.getChosenNumbers(mNumerologyData, view.getContext())[1]);

        textView = (TextView)view.findViewById(R.id.chosen_item_3);
        textView.setText(DataExtractor.getChosenNumbers(mNumerologyData, view.getContext())[2]);

        textView = (TextView)view.findViewById(R.id.chosen_item_4);
        textView.setText(DataExtractor.getChosenNumbers(mNumerologyData, view.getContext())[3]);

        textView = (TextView)view.findViewById(R.id.chosen_item_5);
        textView.setText(DataExtractor.getChosenNumbers(mNumerologyData, view.getContext())[4]);

        textView = (TextView)view.findViewById(R.id.chosen_item_6);
        textView.setText(DataExtractor.getChosenNumbers(mNumerologyData, view.getContext())[5]);

        textView = (TextView)view.findViewById(R.id.chosen_item_7);
        textView.setText(DataExtractor.getChosenNumbers(mNumerologyData, view.getContext())[6]);

        textView = (TextView)view.findViewById(R.id.chosen_item_8);
        textView.setText(DataExtractor.getChosenNumbers(mNumerologyData, view.getContext())[7]);

        textView = (TextView)view.findViewById(R.id.chosen_item_9);
        textView.setText(DataExtractor.getChosenNumbers(mNumerologyData, view.getContext())[8]);

        textView = (TextView)view.findViewById(R.id.chosen_item_10);
        textView.setText(DataExtractor.getChosenNumbers(mNumerologyData, view.getContext())[9]);
    }

    private void setupSuccessDetails() {
        View view = getView();

        TextView textView = (TextView)view.findViewById(R.id.success_item_1);
        textView.setText(DataExtractor.getSuccess(mNumerologyData, view.getContext())[0]);

        textView = (TextView)view.findViewById(R.id.success_item_2);
        textView.setText(DataExtractor.getSuccess(mNumerologyData, view.getContext())[1]);
    }

    private void setupEnergeticDetails() {
        View view = getView();

        TextView textView = (TextView)view.findViewById(R.id.energetics_item_1);
        textView.setText(DataExtractor.getEnergetics(mNumerologyData, Energetics.Type.ENERGETIC_POTENTIAL));

        textView = (TextView)view.findViewById(R.id.energetics_item_2);
        textView.setText(DataExtractor.getEnergetics(mNumerologyData, Energetics.Type.GENERIC_ENERGETICS_LEVEL));

        textView = (TextView)view.findViewById(R.id.energetics_item_3);
        textView.setText(DataExtractor.getEnergetics(mNumerologyData, Energetics.Type.ENERGETIC_CYCLOGRAM));
    }

    private void setupGeneralDetails() {
        View view = getView();

        TextView textView = (TextView)view.findViewById(R.id.general_item_1);
        textView.setText(DataExtractor.getEmphasizedNumber(mNumerologyData, EmphasizedNumber.Type.DEVELOP));

        textView = (TextView)view.findViewById(R.id.general_item_2);
        textView.setText(DataExtractor.getEmphasizedNumber(mNumerologyData, EmphasizedNumber.Type.MISSION));

        textView = (TextView)view.findViewById(R.id.general_item_3);
        textView.setText(DataExtractor.getEmphasizedNumber(mNumerologyData, EmphasizedNumber.Type.ANCESTOR));

        textView = (TextView)view.findViewById(R.id.general_item_4);
        textView.setText(DataExtractor.getEmphasizedNumber(mNumerologyData, EmphasizedNumber.Type.SOURCE));

        textView = (TextView)view.findViewById(R.id.general_item_5);
        textView.setText(DataExtractor.getStability(mNumerologyData, Stability.Type.SPIRITUAL_STABILITY));

        textView = (TextView)view.findViewById(R.id.general_item_6);
        textView.setText(DataExtractor.getStability(mNumerologyData, Stability.Type.PHYSICAL_STABILITY));

        textView = (TextView)view.findViewById(R.id.general_item_7);
        textView.setText(DataExtractor.getDominance(mNumerologyData));
    }

    private void setupGeneralCategoryStrings() {
        View view = getView();

        TextView textView = (TextView)view.findViewById(R.id.generaL_category_1);
        textView.setText(mGeneralCategory[0]);

        textView = (TextView)view.findViewById(R.id.generaL_category_2);
        textView.setText(mGeneralCategory[1]);

        textView = (TextView)view.findViewById(R.id.generaL_category_3);
        textView.setText(mGeneralCategory[2]);

        textView = (TextView)view.findViewById(R.id.generaL_category_4);
        textView.setText(mGeneralCategory[3]);

        textView = (TextView)view.findViewById(R.id.generaL_category_5);
        textView.setText(mGeneralCategory[4]);

        textView = (TextView)view.findViewById(R.id.generaL_category_6);
        textView.setText(mGeneralCategory[5]);

        textView = (TextView)view.findViewById(R.id.generaL_category_7);
        textView.setText(mGeneralCategory[6]);
    }

    private void loadCategoryStrings() {

        Context context = getActivity();
        if(mNumerologyData.getClass() == DateNumerologyData.class) {
        mGeneralCategory = context.getResources().getStringArray(R.array.details_general_date_category);
        } else if (mNumerologyData.getClass() == NumberNumerologyData.class) {
            mGeneralCategory = context.getResources().getStringArray(R.array.details_general_number_category);
        }
    }

    @Override
    public void receive(BaseNumerologyData data) {
        this.mNumerologyData = data;
    }
}
