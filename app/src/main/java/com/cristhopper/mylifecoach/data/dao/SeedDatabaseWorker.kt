package com.cristhopper.mylifecoach.data.dao

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.cristhopper.mylifecoach.data.domain.Goal
import com.cristhopper.mylifecoach.utils.GOAL_DATA_FILENAME
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader

class SeedDatabaseWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    private val TAG by lazy { SeedDatabaseWorker::class.java.simpleName }

    override fun doWork(): Worker.Result {
        val goalType = object : TypeToken<List<Goal>>() {}.type
        var jsonReader: JsonReader? = null

        return try {
            val inputStream = applicationContext.assets.open(GOAL_DATA_FILENAME)
            jsonReader = JsonReader(inputStream.reader())
            val goalList: ArrayList<Goal> = Gson().fromJson(jsonReader, goalType)
            val database = AppDatabase.getInstance(applicationContext)
            database.goalDao().insertAll(goalList)
            Worker.Result.SUCCESS
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Worker.Result.FAILURE
        } finally {
            jsonReader?.close()
        }
    }
}