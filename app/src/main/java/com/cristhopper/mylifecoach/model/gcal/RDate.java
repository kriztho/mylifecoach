package com.cristhopper.mylifecoach.model.gcal;

import android.os.Parcel;
import android.os.Parcelable;

import org.joda.time.DateTime;

import java.util.ArrayList;

// Modeled after Google Calendar rules
public class RDate implements Parcelable {

    private ArrayList<DateTime> dates;

    public RDate(ArrayList<DateTime> dates) {
        this.dates = dates;
    }

    protected RDate(Parcel in) {
        if (in.readByte() == 0x01) {
            dates = new ArrayList<DateTime>();
            in.readList(dates, DateTime.class.getClassLoader());
        } else {
            dates = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (dates == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(dates);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<RDate> CREATOR = new Parcelable.Creator<RDate>() {
        @Override
        public RDate createFromParcel(Parcel in) {
            return new RDate(in);
        }

        @Override
        public RDate[] newArray(int size) {
            return new RDate[size];
        }
    };
}

