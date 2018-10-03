package com.cristhopper.mylifecoach.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cristhopper.mylifecoach.R
import com.cristhopper.mylifecoach.data.interfaces.Frequency
import com.cristhopper.mylifecoach.data.interfaces.Status
import com.cristhopper.mylifecoach.data.domain.Goal
import com.cristhopper.mylifecoach.data.domain.gcal.RDate
import com.cristhopper.mylifecoach.data.domain.gcal.RRule
import com.cristhopper.mylifecoach.data.domain.gcal.Recurrence
import com.cristhopper.mylifecoach.ui.adapter.GoalListAdapter
import com.cristhopper.mylifecoach.viewmodel.GoalListViewModel
import kotlinx.android.synthetic.main.content_main.*
import org.joda.time.DateTime

class GoalListFragment: Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: GoalListAdapter
    lateinit var model: GoalListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model = ViewModelProviders.of(this).get(GoalListViewModel::class.java)

        // Observe the list of goals
        model.getGoals().observe(this, Observer { goals ->

            // Update the list of goals
            adapter.submitList(goals)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.content_main, container, false)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupList()
    }

    fun setupList() {
        linearLayoutManager = LinearLayoutManager(activity)
        rv_main.layoutManager = linearLayoutManager

        val goalsList = createTestData()

        adapter = GoalListAdapter(goalsList)
        rv_main.adapter = adapter
    }

    fun createTestData(): ArrayList<Goal> {

        val goalsList : ArrayList<Goal> = ArrayList()

        var goal = Goal("0", "Workout", Status.CONFIRMED, "At the gym 4 times a week",
                "Gym", DateTime.now(), DateTime.now().plusWeeks(1), 3600,
                Recurrence(RRule(Frequency.WEEKLY, 1, 4, DateTime.now().plusMonths(1), null),
                        RDate(null), RDate(null)), null)
        goalsList.add(goal)

        goal = Goal("1", "Study Japanese", Status.CONFIRMED, "At home 2 times a week",
                "Gym", DateTime.now(), DateTime.now().plusWeeks(1), 3600,
                Recurrence(RRule(Frequency.WEEKLY, 1, 4, DateTime.now().plusMonths(1), null),
                        RDate(null), RDate(null)), null)
        goalsList.add(goal)

        goal = Goal("2", "Cook lunch for the week", Status.CONFIRMED, "On sundays",
                "Gym", DateTime.now(), DateTime.now().plusWeeks(1), 3600,
                Recurrence(RRule(Frequency.WEEKLY, 1, 4, DateTime.now().plusMonths(1), null),
                        RDate(null), RDate(null)), null)
        goalsList.add(goal)

        goal = Goal("3", "Rezar el Rosario", Status.CONFIRMED, "Con mis amigas por whatsapp",
                "Gym", DateTime.now(), DateTime.now().plusWeeks(1), 3600,
                Recurrence(RRule(Frequency.WEEKLY, 1, 4, DateTime.now().plusMonths(1), null),
                        RDate(null), RDate(null)), null)
        goalsList.add(goal)

        return goalsList
    }
}