package com.nightbird.universalnumerology.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.nightbird.universalnumerology.Data.BaseNumerologyData;
import com.nightbird.universalnumerology.Data.DataExtractor;
import com.nightbird.universalnumerology.Data.DateNumerologyData;
import com.nightbird.universalnumerology.Interfaces.DataRequestor;
import com.nightbird.universalnumerology.Mediator.Receiver;
import com.nightbird.universalnumerology.NumerologyGrid.DateGrid;
import com.nightbird.universalnumerology.R;

/**
 * Created by Nightbird on 2014.02.09..
 */
public class GridFragment extends Fragment implements Receiver {

    private BaseNumerologyData numerologyData;
    private DataRequestor dataRequestor;

    private final int GRID_ID = 1;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        dataRequestor = (DataRequestor)activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dataRequestor = null;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.gridview, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadGridView();

    }

    private void loadGridView() {

        View view = getView();

        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.grid_layout);

        DateGrid dateGrid = new DateGrid(getActivity());
        dateGrid.setId(GRID_ID);
        dateGrid.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT, 1f));
        linearLayout.addView(dateGrid, 0);

        dataRequestor.requestData(this);
        dateGrid.setupMatrix(numerologyData.getMatrix(), DataExtractor.getTransformState(numerologyData));
    }

    @Override
    public void receive(BaseNumerologyData data) {
        this.numerologyData = data;
    }
}