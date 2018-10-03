package com.cristhopper.mylifecoach.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cristhopper.mylifecoach.R
import com.cristhopper.mylifecoach.data.interfaces.IEvent
import com.cristhopper.mylifecoach.utils.EventDecorator
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.util.*
import kotlin.collections.ArrayList

class CalendarFragment: Fragment() {

    companion object {
        @JvmField
        val KEY_EVENTS: String = "key events"
    }

    var mCalendarView: MaterialCalendarView? = null
    var mEvents: ArrayList<IEvent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mEvents = arguments?.getParcelableArrayList<IEvent>(KEY_EVENTS)
        mEvents = ArrayList()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_calendar, container, false)

        mCalendarView = rootView.findViewById(R.id.calendarView)
        mCalendarView?.addDecorator(EventDecorator(R.color.colorAccent, HashSet(mEvents)))

        return rootView
    }
}