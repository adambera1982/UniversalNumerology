package com.nightbird.universalnumerology.Data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nightbird on 2014.02.14..
 */
public class InputData implements Parcelable {

    String name;
    String year;
    String number;
    int day;
    int month;
    int eventIndex;
    int methodIndex;

    public InputData(Parcel parcel) {
        name = parcel.readString();
        year = parcel.readString();
        number = parcel.readString();
        day = parcel.readInt();
        month = parcel.readInt();
        eventIndex = parcel.readInt();
        methodIndex = parcel.readInt();
    }

    public InputData() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(year);
        parcel.writeString(number);
        parcel.writeInt(day);
        parcel.writeInt(month);
        parcel.writeInt(eventIndex);
        parcel.writeInt(methodIndex);
    }

    public static final Parcelable.Creator<InputData> CREATOR
            = new Parcelable.Creator<InputData>() {
        public InputData createFromParcel(Parcel in) {
            return new InputData(in);
        }

        public InputData[] newArray(int size) {
            return new InputData[size];
        }
    };
}
