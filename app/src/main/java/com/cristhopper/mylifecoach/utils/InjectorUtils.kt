package com.cristhopper.mylifecoach.utils

import android.content.Context
import com.cristhopper.mylifecoach.data.dao.AppDatabase
import com.cristhopper.mylifecoach.data.repository.GoalRepository
import com.cristhopper.mylifecoach.viewmodel.GoalListViewModelFactory

object InjectorUtils {

    fun getGoalRepository(context: Context): GoalRepository {
        return GoalRepository.getInstance(AppDatabase.getInstance(context).goalDao())
    }

    fun provideGoalListViewModelFactory(context: Context): GoalListViewModelFactory {
        val repository = getGoalRepository(context)
        return GoalListViewModelFactory(repository)
    }
}