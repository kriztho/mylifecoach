package com.cristhopper.mylifecoach.data.domain

import android.os.Parcelable
import com.cristhopper.mylifecoach.data.interfaces.IEvent
import com.cristhopper.mylifecoach.data.interfaces.Status
import com.cristhopper.mylifecoach.data.domain.gcal.Recurrence
import kotlinx.android.parcel.Parcelize
import org.joda.time.DateTime
import org.joda.time.Seconds

@Parcelize
class Goal(
        override val id: String?,
        override val name: String,
        override val status: Status,

        override val description: String,
        override val location: String?,

        override val start: DateTime,
        override val end: DateTime,
        override val estimatedDuration: Int,

        override val recurrence: Recurrence?,
        override val recurringEventId: Int?
): Parcelable, IEvent {

    // The list of tasks associated to this goal
    var tasks: ArrayList<Task>? = null

    // The list of events scheduled for this goal
    var events: ArrayList<Event>? = null

    constructor(name: String, description: String) : this(
            null,
            name,
            Status.CONFIRMED,
            description,
            null,
            DateTime.now(),
            DateTime.now().plusMinutes(15),
            Seconds.secondsBetween(DateTime.now(), DateTime.now().plusMinutes(15)).seconds,
            null,
            null
    )
}