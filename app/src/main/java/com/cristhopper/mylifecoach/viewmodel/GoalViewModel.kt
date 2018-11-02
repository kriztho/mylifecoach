package com.cristhopper.mylifecoach.viewmodel

import android.arch.lifecycle.ViewModel
import com.cristhopper.mylifecoach.data.repository.GoalRepository

class GoalViewModel internal constructor(
        private val goalRepository: GoalRepository
): ViewModel() {

    fun getGoal(id: Int) = goalRepository.getGoal(id)
}