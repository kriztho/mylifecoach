package com.cristhopper.mylifecoach.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.support.annotation.WorkerThread
import com.cristhopper.mylifecoach.data.domain.Goal
import com.cristhopper.mylifecoach.utils.Converters

/**
 * The Data Access Object for the Plant class.
 */
@Dao
@TypeConverters(Converters.class)
interface GoalDao {

    @Query("SELECT * FROM goals ORDER BY name")
    fun getGoals(): LiveData<List<Goal>>

    @Query("SELECT * FROM goals WHERE id = :goalId")
    fun getGoal(goalId: String): LiveData<Goal>

    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(goals: ArrayList<Goal>)
}