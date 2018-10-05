package com.cristhopper.mylifecoach.data.domain

import android.os.Parcelable
import com.cristhopper.mylifecoach.data.interfaces.IEvent
import com.cristhopper.mylifecoach.data.interfaces.Status
import com.cristhopper.mylifecoach.data.domain.gcal.Recurrence
import com.prolificinteractive.materialcalendarview.CalendarDay
import kotlinx.android.parcel.Parcelize
import org.joda.time.DateTime

@Parcelize
class Event(
        override val name: String,
        override val start: DateTime,
        override val estimatedDuration: Int,
        override val id: String? = null,
        override val description: String? = null,
        override val status: Status = Status.CONFIRMED,
        override val location: String? = null,
        override val end: DateTime = start.plusSeconds(estimatedDuration),
        override val recurrence: Recurrence? = null,
        override val recurringEventId: Int? = null
): Parcelable, IEvent {

    constructor(day: Int, month: Int, year: Int):
            this("", DateTime(year, month, day, 0, 0), 1)

    constructor(calDay: CalendarDay):
            this("", DateTime(calDay.year, calDay.month, calDay.day, 0, 0), 1)
}