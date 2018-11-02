package com.cristhopper.mylifecoach.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.cristhopper.mylifecoach.data.domain.Goal

/**
 * The Data Access Object for the Plant class.
 */
@Dao
interface GoalDao {

    @Query("SELECT * FROM goals ORDER BY title")
    fun getGoals(): LiveData<List<Goal>>

    @Query("SELECT * FROM goals WHERE id = :goalId")
    fun getGoal(goalId: Int): LiveData<Goal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(goals: ArrayList<Goal>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(goal: Goal)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(goal: Goal)
}