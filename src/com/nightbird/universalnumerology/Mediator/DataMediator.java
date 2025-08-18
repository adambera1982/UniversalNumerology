package com.nightbird.universalnumerology.Mediator;

import com.nightbird.universalnumerology.Data.BaseNumerologyData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nightbird on 2014.02.09..
 */
public class DataMediator implements Mediator {

    List<Receiver> fragments;

    public DataMediator() {
        this.fragments = new ArrayList<Receiver>();
    }


    @Override
    public void broadcast(BaseNumerologyData data) {
        for (Receiver frag : this.fragments) {
         //   if (fragment != frag) {
                frag.receive(data);
         //   }
        }
    }

    @Override
    public void addFragment(Receiver fragment) {
        this.fragments.add(fragment);
    }

    public void deleteFragment(Receiver fragment) {
        this.fragments.remove(fragment);
    }
}
