package com.cristhopper.mylifecoach.viewmodel

import android.arch.lifecycle.ViewModel
import com.cristhopper.mylifecoach.data.repository.EventRepository
import com.cristhopper.mylifecoach.data.repository.GoalRepository
import javax.inject.Inject

class GoalListViewModel internal constructor(
    private val goalRepository: GoalRepository
): ViewModel() {

    private val goalList = goalRepository.getGoals()

    fun getGoals() = goalList
}