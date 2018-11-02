package com.cristhopper.mylifecoach.ui.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cristhopper.mylifecoach.R
import com.cristhopper.mylifecoach.data.domain.Goal
import com.cristhopper.mylifecoach.utils.InjectorUtils
import com.cristhopper.mylifecoach.viewmodel.TaskManagerViewModel
import kotlinx.android.synthetic.main.activity_task_manager.*
import org.joda.time.Period
import org.joda.time.format.PeriodFormatterBuilder


class TaskManagerActivity : AppCompatActivity() {

    var goal: Goal? = null
    private lateinit var viewModel: TaskManagerViewModel

    companion object {
        @JvmField
        val KEY_GOAL_ID: String = "key goal id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_task_manager)

        // Get the goal id
        val goalId = intent.getIntExtra(KEY_GOAL_ID, 0)

        val factory = InjectorUtils.provideTaskManagerViewModelFactory(this)
        viewModel = ViewModelProviders.of(this, factory).get(TaskManagerViewModel::class.java)

        viewModel.getGoal(goalId).observe(this, Observer { goal ->

            this.goal = goal

            // populate
            setup(goal)
        })
    }

    private fun setup(goal: Goal?) {

        goal?.also { goal1 ->

            txt_title.text = goal1.title

            val durationPeriod = Period(0, 0, goal1.estimatedDuration, 0)

            val formatter = PeriodFormatterBuilder()
                    .printZeroAlways()
                    .minimumPrintedDigits(2)
                    .appendHours().appendSuffix(":")
                    .appendMinutes().appendSuffix(":")
                    .appendSeconds().toFormatter()

            txt_timer.text = formatter.print(durationPeriod.normalizedStandard())
        }
    }
}