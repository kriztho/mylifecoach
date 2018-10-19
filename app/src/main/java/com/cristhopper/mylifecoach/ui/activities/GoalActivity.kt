package com.cristhopper.mylifecoach.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.cristhopper.mylifecoach.R
import com.cristhopper.mylifecoach.data.domain.Goal
import com.cristhopper.mylifecoach.data.domain.Task
import kotlinx.android.synthetic.main.activity_goal.*
import org.joda.time.Period
import java.util.*
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.PeriodFormat

class GoalActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {

    private val PERCENTAGE_TO_SHOW_IMAGE = 20
    private val REQUEST_CODE_EDIT_GOAL = 201
    private var mFab: View? = null
    private var mMaxScrollSize: Int = 0
    private var mIsImageHidden: Boolean = false

    var mGoal: Goal? = null

    companion object {
        @JvmField
        val KEY_GOAL: String = "key goal"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal)

        setupFab()
        setupToolbar()
        setupAppbar()

        // Get the variable from the intent
        mGoal = intent?.getParcelableExtra(KEY_GOAL)

        // Populate the UI
        populate(mGoal)
    }

    fun setupFab() {
        fab.setOnClickListener { view ->

            // Edit
            val showGoalIntent = Intent(this, EditGoalActivity::class.java)
            showGoalIntent.putExtra(EditGoalActivity.KEY_GOAL, mGoal)
            startActivityForResult(showGoalIntent, REQUEST_CODE_EDIT_GOAL)
        }
    }

    fun setupToolbar() {
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun setupAppbar() {
        appbar.addOnOffsetChangedListener(this)
    }

    /**
     * Load the UI with the current goal
     */
    fun populate(goal: Goal?) {

        txt_name.setText(goal?.name)
        txt_description.setText(goal?.description)

//        txt_frequency.setText(goal?.recurrence?.rrule?.frequency?.name)

        // Set the duration
        goal?.also { goal1 ->
            val duration = PeriodFormat.getDefault().print(Period(0, 0, goal1.estimatedDuration, 0).normalizedStandard())
            txt_duration.setText(String.format(Locale.US, "%s min", duration))
        }

        // Set the start and end times
        goal?.also { goal1 ->
            val fmt = DateTimeFormat.forPattern("hh:mm aa")
            txt_hours.setText(String.format(Locale.US, "%s - %s", fmt.print(goal1.getStartDate()), fmt.print(goal1.getStartDate()?.plusSeconds(goal1.estimatedDuration))))
        }

        // Set until
        val fmt = DateTimeFormat.forPattern("MMMM dd, yyyy")
        txt_end_date.setText(String.format(Locale.US, "Until %s", fmt.print(goal?.getEndDate())))

        // Load tasks into UI
//        populateTasks(goal?.tasks)
    }

    fun populateTasks(tasks: List<Task>?) {


    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, i: Int) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.totalScrollRange

        val currentScrollPercentage = Math.abs(i) * 100 / mMaxScrollSize

        if (currentScrollPercentage >= PERCENTAGE_TO_SHOW_IMAGE) {
            if (!mIsImageHidden) {
                mIsImageHidden = true

                ViewCompat.animate(mFab!!).scaleY(0f).scaleX(0f).start()
            }
        }

        if (currentScrollPercentage < PERCENTAGE_TO_SHOW_IMAGE) {
            if (mIsImageHidden) {
                mIsImageHidden = false
                ViewCompat.animate(mFab!!).scaleY(1f).scaleX(1f).start()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Update the changes
        if (requestCode == REQUEST_CODE_EDIT_GOAL && resultCode == Activity.RESULT_OK) {

        }
    }
}