package com.nightbird.universalnumerology.Mediator;

import android.support.v4.app.Fragment;
import com.nightbird.universalnumerology.Data.BaseNumerologyData;

/**
 * Created by Nightbird on 2014.02.09..
 */
public interface Mediator {

    public void broadcast(BaseNumerologyData data);

    public void addFragment(Receiver fragment);

    public void deleteFragment(Receiver fragment);
}
