package com.cristhopper.mylifecoach.utils

import android.content.Context
import com.cristhopper.mylifecoach.data.dao.AppDatabase
import com.cristhopper.mylifecoach.data.repository.GoalRepository
import com.cristhopper.mylifecoach.viewmodel.EditGoalViewModelFactory
import com.cristhopper.mylifecoach.viewmodel.GoalListViewModelFactory
import com.cristhopper.mylifecoach.viewmodel.GoalViewModelFactory
import com.cristhopper.mylifecoach.viewmodel.TaskManagerViewModelFactory

object InjectorUtils {

    fun getGoalRepository(context: Context): GoalRepository {
        return GoalRepository.getInstance(AppDatabase.getInstance(context).goalDao())
    }

    fun provideGoalListViewModelFactory(context: Context): GoalListViewModelFactory {
        val repository = getGoalRepository(context)
        return GoalListViewModelFactory(repository)
    }

    fun provideGoalViewModelFactory(context: Context): GoalViewModelFactory {
        val repository = getGoalRepository(context)
        return GoalViewModelFactory(repository)
    }

    fun provideEditGoalViewModelFactory(context: Context): EditGoalViewModelFactory {
        val repository = getGoalRepository(context)
        return EditGoalViewModelFactory(repository)
    }

    fun provideTaskManagerViewModelFactory(context: Context): TaskManagerViewModelFactory {
        val repository = getGoalRepository(context)
        return TaskManagerViewModelFactory(repository)
    }
}