package com.cristhopper.mylifecoach.data.interfaces

import android.arch.persistence.room.TypeConverter
import android.os.Parcelable
import com.cristhopper.mylifecoach.data.domain.gcal.Recurrence
import org.joda.time.DateTime

enum class Frequency {
    NONE,
    DAILY,
    WEEKLY,
    MONTHLY,
    ANNUALLY,
    WEEKDAYS,
    WEEKENDS,
    CUSTOM
}

enum class Day {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}

enum class Status {
    CONFIRMED,
    CANCELED,
    COMPLETE,
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