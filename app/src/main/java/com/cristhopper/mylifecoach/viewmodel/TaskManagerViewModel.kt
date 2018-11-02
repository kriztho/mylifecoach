package com.cristhopper.mylifecoach.viewmodel

import android.arch.lifecycle.ViewModel
import com.cristhopper.mylifecoach.data.repository.GoalRepository

class TaskManagerViewModel internal constructor(
    private val goalRepository: GoalRepository
): ViewModel() {

    fun getGoal(goalId: Int) = goalRepository.getGoal(goalId)
}