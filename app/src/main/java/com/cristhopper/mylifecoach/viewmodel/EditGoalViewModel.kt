package com.cristhopper.mylifecoach.viewmodel

import android.arch.lifecycle.ViewModel
import com.cristhopper.mylifecoach.data.domain.Goal
import com.cristhopper.mylifecoach.data.repository.GoalRepository
import io.reactivex.Completable

class EditGoalViewModel internal constructor(
        private val goalRepository: GoalRepository
): ViewModel() {

    fun getGoal(id: Int) = goalRepository.getGoal(id)

    fun insertGoal(goal: Goal): Completable {

        return Completable.fromAction {
            goalRepository.insertGoal(goal)
        }
    }

    fun updateGoal(goal: Goal): Completable {

        return Completable.fromAction {
            goalRepository.updateGoal(goal)
        }
    }
}