package com.cristhopper.mylifecoach.utils

import com.cristhopper.mylifecoach.data.interfaces.IEvent
import com.cristhopper.mylifecoach.data.domain.Event
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan

class EventDecorator(var color: Int, var dates: HashSet<IEvent>): DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay?): Boolean {

        day?.let {
            val eventDay = Event(it)
            return dates.contains(eventDay)
        }

        return false
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(DotSpan(5f, color))
    }
}