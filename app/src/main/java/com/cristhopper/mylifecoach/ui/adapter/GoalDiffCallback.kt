package com.cristhopper.mylifecoach.ui.adapter

import android.support.v7.util.DiffUtil
import com.cristhopper.mylifecoach.data.domain.Goal

class GoalDiffCallback : DiffUtil.ItemCallback<Goal>() {
    override fun areItemsTheSame(first: Goal, second: Goal): Boolean {
        return first.id == second.id
    }

    override fun areContentsTheSame(first: Goal, second: Goal): Boolean {
        return first == second
    }
}