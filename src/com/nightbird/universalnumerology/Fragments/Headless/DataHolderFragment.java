package com.nightbird.universalnumerology.Fragments.Headless;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.nightbird.universalnumerology.Data.BaseNumerologyData;
import com.nightbird.universalnumerology.Data.Event;
import com.nightbird.universalnumerology.Mediator.Mediator;

/**
 * Created by Nightbird on 2014.02.09..
 */
public class DataHolderFragment extends Fragment {

    private Event<BaseNumerologyData> event;

    private Mediator mediator;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    public void addMediator(Mediator mediator) {
        this.mediator = mediator;
        //mediator.broadcast();
    }

    public void addEventData(Event<BaseNumerologyData> event) {
        this.event = event;
    }

    public Mediator getMediator() {
        return  mediator;
    }

    public Event<BaseNumerologyData> getEventData() {
        return this.event;
    }
}