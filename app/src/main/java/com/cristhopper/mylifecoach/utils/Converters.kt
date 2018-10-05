package com.cristhopper.mylifecoach.utils

import android.arch.persistence.room.TypeConverter
import com.cristhopper.mylifecoach.data.domain.gcal.Recurrence
import com.cristhopper.mylifecoach.data.interfaces.Status
import org.joda.time.DateTime

object Converters {

    // DateTime
    @TypeConverter
    @JvmStatic
    fun fromTimestamp(value: Long?): DateTime? {
        return if (value == null) null else DateTime(value)
    }

    @TypeConverter
    @JvmStatic
    fun dateToTimestamp(date: DateTime?): Long? {
        return date?.millis
    }


    // Status
    @TypeConverter
    @JvmStatic
    fun toInt(status: Status): Int {
        return status.ordinal
    }

    @TypeConverter
    @JvmStatic
    fun fromInt(index: Int): Status {
        return Status.values().get(index)
    }


        // Recurrence
//    @TypeConverter
//    fun toString(value: Recurrence?): String? {
//        return value.toString()
//    }
//
//    @TypeConverter
//    fun fromString(value: String?): Recurrence? {
//        return Recurrence(value)
//    }
}