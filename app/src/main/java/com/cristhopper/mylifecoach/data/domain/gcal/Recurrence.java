package com.cristhopper.mylifecoach.data.domain.gcal;

import android.os.Parcel;
import android.os.Parcelable;

public class Recurrence implements Parcelable {

    private RRule rrule;
    private RDate rdate;
    private RDate exdate;

    public Recurrence(RRule rrule, RDate rdate, RDate exdate) {

        this.rrule = rrule;
        this.rdate = rdate;
        this.exdate = exdate;
    }

    public RRule getRrule() {
        return rrule;
    }

    public RDate getRdate() {
        return rdate;
    }

    public RDate getExdate() {
        return exdate;
    }






    /*
        Parcelable implementation
     */
    protected Recurrence(Parcel in) {
        rrule = (RRule) in.readValue(RRule.class.getClassLoader());
        rdate = (RDate) in.readValue(RDate.class.getClassLoader());
        exdate = (RDate) in.readValue(RDate.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(rrule);
        dest.writeValue(rdate);
        dest.writeValue(exdate);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Recurrence> CREATOR = new Parcelable.Creator<Recurrence>() {
        @Override
        public Recurrence createFromParcel(Parcel in) {
            return new Recurrence(in);
        }

        @Override
        public Recurrence[] newArray(int size) {
            return new Recurrence[size];
        }
    };
}

