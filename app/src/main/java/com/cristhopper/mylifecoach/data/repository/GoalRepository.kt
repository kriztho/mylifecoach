package com.cristhopper.mylifecoach.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.cristhopper.mylifecoach.data.dao.GoalDao
import com.cristhopper.mylifecoach.data.domain.Goal
import org.joda.time.DateTime
import javax.inject.Inject

class GoalRepository private constructor(private val goalDao: GoalDao){

//    fun getGoals() = goalDao.getGoals()
    fun getGoals(): LiveData<ArrayList<Goal>> {

        return createTestData()
    }

    fun getGoal(goalId: String) = goalDao.getGoal(goalId)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: GoalRepository? = null

        fun getInstance(goalDao: GoalDao) =
                instance ?: synchronized(this) {
                    instance ?: GoalRepository(goalDao).also { instance = it }
                }
    }

    fun createTestData(): LiveData<ArrayList<Goal>> {

        val goalsList : ArrayList<Goal> = ArrayList()
        var goal = Goal("Workout", "At the gym 4 times a week", DateTime.now(), 900)
        goalsList.add(goal)

        goal = Goal("Study Japanese","At home 2 times a week", DateTime.now(), 3600)
        goalsList.add(goal)

        goal = Goal("Cook lunch for the week", "On sundays", DateTime.now(), 3600)
        goalsList.add(goal)

        goal = Goal("Rezar el Rosario","Con mis amigas por whatsapp", DateTime.now(), 3600)
        goalsList.add(goal)

        val list = MutableLiveData<ArrayList<Goal>>()
        list.value = goalsList

        return list
    }
}