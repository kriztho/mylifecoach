package com.cristhopper.mylifecoach.interfaces

import com.cristhopper.mylifecoach.model.gcal.Recurrence
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
    INCOMPLETE
}

interface IEvent {

    val id: String?
    val name: String
    val status: Status

    val description: String
    val location: String?

    val start: DateTime
    val end: DateTime
    val estimatedDuration: Int

    val recurrence: Recurrence?
    val recurringEventId: Int?
}