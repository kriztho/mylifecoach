package com.cristhopper.mylifecoach.viewmodel

import android.arch.lifecycle.ViewModel
import com.cristhopper.mylifecoach.data.dao.GoalDao
import com.cristhopper.mylifecoach.data.repository.EventRepository
import com.cristhopper.mylifecoach.data.repository.GoalRepository

class GoalListViewModel internal constructor(): ViewModel() {

    private lateinit var userId: String
    private lateinit var eventRepo: EventRepository
    private var goalRepo: GoalRepository = GoalRepository.getInstance(GoalDao())

    private val goalList = goalRepo.getGoals()

    fun getGoals() = goalList
}