package com.cristhopper.mylifecoach.data.domain.gcal;

import android.os.Parcel;
import android.os.Parcelable;

import com.cristhopper.mylifecoach.data.interfaces.Frequency;

import org.joda.time.DateTime;

public class RRule implements Parcelable {

    private Frequency frequency;
    private int interval;
    private int count;
    private DateTime until;
    private DateTime byday;

    public RRule(Frequency frequency, int interval, int count, DateTime until, DateTime byday) {
        this.frequency = frequency;
        this.interval = interval;
        this.count = count;
        this.until = until;
        this.byday = byday;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public int getInterval() {
        return interval;
    }

    public int getCount() {
        return count;
    }

    public DateTime getUntil() {
        return until;
    }

    public DateTime getByday() {
        return byday;
    }







    /*
        Parcelable implementation
     */
    protected RRule(Parcel in) {
        frequency = (Frequency) in.readValue(Frequency.class.getClassLoader());
        interval = in.readInt();
        count = in.readInt();
        until = (DateTime) in.readValue(DateTime.class.getClassLoader());
        byday = (DateTime) in.readValue(DateTime.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(frequency);
        dest.writeInt(interval);
        dest.writeInt(count);
        dest.writeValue(until);
        dest.writeValue(byday);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<RRule> CREATOR = new Parcelable.Creator<RRule>() {
        @Override
        public RRule createFromParcel(Parcel in) {
            return new RRule(in);
        }

        @Override
        public RRule[] newArray(int size) {
            return new RRule[size];
        }
    };
}
