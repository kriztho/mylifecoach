package com.cristhopper.mylifecoach.data.domain

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import android.os.Parcelable
import com.cristhopper.mylifecoach.data.interfaces.Status
import com.cristhopper.mylifecoach.data.domain.gcal.Recurrence
import com.cristhopper.mylifecoach.utils.Converters
import kotlinx.android.parcel.Parcelize
import org.joda.time.DateTime
import org.joda.time.Duration

@Parcelize
@Entity(tableName = "goals")
class Goal(
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,                                     //Autogenerate by Room
        val name: String,
        val description: String,
        val status: Status = Status.CONFIRMED,

        val start: DateTime,
        val estimatedDuration: Int,
        val end: DateTime? = start.plusSeconds(estimatedDuration),
        val location: String? = null

//        @TypeConverters(Converters::class)
//        val recurrence: Recurrence? = null,                      // It doesn't repeat
//        val recurringEventId: Int? = null
): Parcelable {

    // The list of tasks associated to this goal
//    var tasks: ArrayList<Task>? = null

    // The list of events scheduled for this goal
//    var events: ArrayList<Event>? = null

    @Ignore
    constructor(name: String, description: String, start: DateTime, duration: Int) : this(
            null,
            name,
            description,
            Status.CONFIRMED,
            start,
            duration,
            DateTime.now().plusSeconds(duration),
            null
//            null,
//            null
    )
}