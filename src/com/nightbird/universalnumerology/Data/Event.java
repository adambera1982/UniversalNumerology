package com.nightbird.universalnumerology.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nightbird on 2014.02.09..
 */
public class Event<T extends BaseNumerologyData> {
    private T numerologyData;
    private List<T> history;

    public Event(T numerologyData) {
        this.numerologyData = numerologyData;
        history = new ArrayList<T>();
    }

    public T getData() {
        return numerologyData;
    }

    public List<T> getHistory() {
        return history;
    }

    public T getHistoryAtIndex(int index) {

        if(index > history.size()) {
            throw new IndexOutOfBoundsException();
        }

        return history.get(index);
    }

    public T getLastHistoryItem() {

        if(history.size() == 0) {
            return null;
        }

        int index = history.size() - 1;
        return  history.get(index);
    }

    public Class getDataTypeClass() {
        return numerologyData.getClass();
    }

    public void addHistoryData(T data) {
        this.history.add(data);
    }

    public void deleteHistoryData(T data) {
        this.history.remove(data);
    }

    public void deleteHistoryData(int index) {
        this.history.remove(index);
    }


}