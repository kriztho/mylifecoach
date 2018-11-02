package com.cristhopper.mylifecoach.ui.adapter

import android.content.Intent
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import android.view.View
import com.cristhopper.mylifecoach.R
import com.cristhopper.mylifecoach.data.domain.Goal
import com.cristhopper.mylifecoach.ui.activities.GoalActivity
import com.cristhopper.mylifecoach.utils.inflate
import kotlinx.android.synthetic.main.item_row_main.view.*

class GoalListAdapter: ListAdapter<Goal, ViewHolder>(GoalDiffCallback()) {

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val goal = getItem(position)
        holder.bindGoal(goal)
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        // Inflate the view using
        return ViewHolder(parent.inflate(R.layout.item_row_main, false))
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

    private val TAG = "ViewHolder"

    // Holds the TextView that will add each animal to
    private var view: View = view
    private var goal: Goal? = null

    init {
        view.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Log.d(TAG, "[onClick]: " + goal?.title)

        val context = itemView.context
        val showGoalIntent = Intent(context, GoalActivity::class.java)
        showGoalIntent.putExtra(GoalActivity.KEY_GOAL_ID, goal?.id)
        context.startActivity(showGoalIntent)
    }

    fun bindGoal(goal: Goal) {

        // The item
        this.goal = goal

        // The views
        view.item_title.setText(goal.title)
        view.item_description.setText(goal.description)
    }
}