package com.cristhopper.mylifecoach.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.cristhopper.mylifecoach.data.dao.GoalDao
import com.cristhopper.mylifecoach.data.domain.Goal

class GoalRepository private constructor(private val goalDao: GoalDao){

    fun getGoals() = MutableLiveData<List<Goal>>()// = goalDao.getGoals()

    fun getGoal(goalId: String){}// = goalDao.getGoal(goalId)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: GoalRepository? = null

        fun getInstance(goalDao: GoalDao) =
                instance ?: synchronized(this) {
                    instance ?: GoalRepository(goalDao).also { instance = it }
                }
    }
}