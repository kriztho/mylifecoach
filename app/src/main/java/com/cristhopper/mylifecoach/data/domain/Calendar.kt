package com.cristhopper.mylifecoach.data.domain

import com.cristhopper.mylifecoach.data.interfaces.IEvent

data class Calendar(val name: String, val description: String) {

    var events: ArrayList<IEvent>? = null

    init {
        events = ArrayList()
    }

    fun addEvent(event: IEvent) {
        events?.add(event)
    }

    fun removeEvent(event: IEvent) {
        events?.remove(event)
    }

    fun clear() {
        events?.clear()
    }
}