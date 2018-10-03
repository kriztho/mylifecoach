package com.cristhopper.mylifecoach.data.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.joda.time.DateTime

@Parcelize
data class Task(val name: String, val estimateDurationSeconds: Int) : Parcelable {

    var actualDurationSeconds: Int = estimateDurationSeconds //In seconds
    var startDateTime: DateTime = DateTime.now()
    var endDateTime: DateTime = startDateTime.plusSeconds(actualDurationSeconds)
    var subtasks: ArrayList<Task>? = null

    //playlist
    //phrases
    //stats
    //breaks
    //segments
    //break duration
}