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
import com.cristhopper.mylifecoach.utils.InjectorUtils
import com.cristhopper.mylifecoach.viewmodel.GoalListViewModel
import com.cristhopper.mylifecoach.viewmodel.GoalListViewModelFactory
import kotlinx.android.synthetic.main.content_main.*
import org.joda.time.DateTime

class GoalListFragment: Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: GoalListAdapter
    private lateinit var viewModel: GoalListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context?.let {

            val factory = InjectorUtils.provideGoalListViewModelFactory(it)
            viewModel = ViewModelProviders.of(this, factory).get(GoalListViewModel::class.java)

            // Observe the list of goals
            viewModel.getGoals().observe(this, Observer { goals ->

                // Update the list of goals
                if(goals != null)
                    adapter.submitList(goals)
            })
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.content_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linearLayoutManager = LinearLayoutManager(activity)
        rv_main.layoutManager = linearLayoutManager

        adapter = GoalListAdapter()
        rv_main.adapter = adapter
    }
}