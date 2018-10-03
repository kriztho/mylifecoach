package com.cristhopper.mylifecoach.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.cristhopper.mylifecoach.data.interfaces.IEvent
import com.cristhopper.mylifecoach.data.network.GoogleCalendarService
import retrofit2.Callback

class EventRepository private constructor() {

    val service: GoogleCalendarService? = null

    fun getEvents(userId: String): LiveData<List<IEvent>> {

        val data = MutableLiveData<List<IEvent>>()
//        service!!.getEvents(userId).enqueue(Callback<List<IEvent>>() {
//
//        })
//
        return data
    }

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: EventRepository? = null

        fun getInstance() =
                instance ?: synchronized(this) {
                    instance ?: EventRepository().also { instance = it }
                }
    }
}