package com.cristhopper.mylifecoach.data.interfaces

import android.arch.persistence.room.TypeConverter
import android.os.Parcelable
import com.cristhopper.mylifecoach.data.domain.gcal.Recurrence
import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime

enum class Frequency(val title: String) {
    NONE("Does not repeat"),
    DAILY("Every day"),
    WEEKLY("Every week"),
    MONTHLY("Every month"),
    ANNUALLY("Every year"),
    WEEKDAYS("Every weekday"),
    WEEKENDS("Every weekend");
//    CUSTOM("Custom");

    override fun toString(): String {
        return title
    }
}

enum class Duration(val title: String, val value: Int) {
    FIFTEENMIN("15 minutes", 900),
    THIRTYMIN("30 minutes", 1800),
    ONEHOUR("1 Hour", 3600),
    TWOHOURS("2 Hours", 7200),
    CUSTOM("Custom", 0);

    companion object {

        fun getTitles(): Array<String> {
            return arrayOf(
                    FIFTEENMIN.title,
                    THIRTYMIN.title,
                    ONEHOUR.title,
                    TWOHOURS.title,
                    CUSTOM.title)
        }

        fun getValues(): Array<Int> {
            return arrayOf(
                    FIFTEENMIN.value,
                    THIRTYMIN.value,
                    ONEHOUR.value,
                    TWOHOURS.value,
                    CUSTOM.value)
        }
    }
}

enum class Day(val title: String) {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    override fun toString(): String {
        return title
    }
}

enum class Status {
    @SerializedName("0")
    CONFIRMED,
    @SerializedName("1")
    CANCELED,
    @SerializedName("2")
    COMPLETE,
    @SerializedName("3")
    INCOMPLETE;
}

// Type converters are a pair of methods, annotated with @TypeConverter,
// that map the type for a single database column to a type for a Java field.
//class StatusConverters {
//
//    @TypeConverter
//    fun toInt(status: Status): Int {
//        return status.ordinal
//    }
//
//    @TypeConverter
//    fun fromInt(index: Int): Status {
//        return Status.values().get(index)
//    }
//}

interface IEvent: Parcelable {

    val id: String?
    val name: String
    val status: Status

    val description: String?
    val location: String?

    val start: DateTime
    val end: DateTime
    val estimatedDuration: Int

    val recurrence: Recurrence?
    val recurringEventId: Int?
}