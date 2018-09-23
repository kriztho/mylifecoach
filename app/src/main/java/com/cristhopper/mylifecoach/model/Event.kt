package com.cristhopper.mylifecoach.model

import android.os.Parcelable
import com.cristhopper.mylifecoach.interfaces.IEvent
import com.cristhopper.mylifecoach.interfaces.Status
import com.cristhopper.mylifecoach.model.gcal.Recurrence
import kotlinx.android.parcel.Parcelize
import org.joda.time.DateTime

@Parcelize
class Event(
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
): Parcelable, IEvent