package com.cristhopper.mylifecoach.ui.activities

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.cristhopper.mylifecoach.R
import com.cristhopper.mylifecoach.data.domain.Goal
import com.cristhopper.mylifecoach.utils.validation.EditTextPatternValidator
import com.cristhopper.mylifecoach.utils.validation.EditTextValidator
import kotlinx.android.synthetic.main.activity_edit_goal.*
import org.joda.time.DateTime
import org.joda.time.Period
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.PeriodFormat
import java.util.*

class EditGoalActivity: AppCompatActivity() {

    var mGoal: Goal? = null
    val fmt = DateTimeFormat.forPattern(TIME_FORMAT)
    val fmd = DateTimeFormat.forPattern(DATE_FORMAT)

    companion object {
        @JvmField
        val KEY_GOAL: String = "key goal"

        @JvmField
        val DATE_FORMAT = "EEE, MMM dd"

        @JvmField
        val TIME_FORMAT = "hh:mm aa"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_goal)

        setupToolbar()

        // Get the variable from the intent
        mGoal = intent?.getParcelableExtra(KEY_GOAL)

        // Setup UI
        setupUI()

        // Populate the UI
        populate(mGoal)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.activity_edit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item?.itemId) {
            R.id.save -> {
                validate()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun setupToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    fun setupUI() {

        setupDatePicker()
        setupTimePicker()
    }

    fun setupDatePicker() {

        // Once set, this is what executes
        val datePickerListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            val newDate = DateTime(year, month, dayOfMonth, 0 , 0, 0)
            txt_startdate.setText(fmd.print(newDate))
        }

        // Set the dialog
        txt_startdate.setOnClickListener { _ ->

            // Either use today or the date set by the goal
            var date = DateTime.now()
            mGoal?.let {
                date = it.getStartDate()
            }

            // Original values
            val datePickerDialog = DatePickerDialog(
                    this,
                    R.style.DatePickerDialogTheme,
                    datePickerListener,
                    date.year,
                    date.monthOfYear - 1,   //It starts on 0
                    date.dayOfMonth
            )

            datePickerDialog.show()
        }
    }

    fun setupTimePicker() {

        // Once set, this is what executes
        val timePickerListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            val newDate = DateTime(1, 1, 1, hourOfDay , minute, 0)
            txt_starttime.setText(fmt.print(newDate))
        }

        txt_starttime.setOnClickListener { _ ->

            // Either use today or the date set by the goal
            var date = DateTime.now()
            mGoal?.let {
                date = it.getStartDate()
            }

            val timePickerDialog = TimePickerDialog(
                    this,
                    R.style.DatePickerDialogTheme,
                    timePickerListener,
                    date.hourOfDay,
                    date.minuteOfHour,
                    false
            )

            timePickerDialog.show()
        }
    }

    /**
     * Populates the UI with the goal if available
     */
    fun populate(goal: Goal?) {

        // Name and description
        edit_title.setText(goal?.name)
        edit_description.setText(goal?.description)

        // Either use today or the date set by the goal
        var date = DateTime.now()
        mGoal?.let {
            date = it.getStartDate()
        }

        // Set the start and end times
        txt_startdate.setText(String.format(Locale.US, "%s", fmd.print(date)))
        txt_starttime.setText(String.format(Locale.US, "%s", fmt.print(date)))

        // Set the frequency
        txt_frequency.setText("Every week night")

        // Set the duration
        goal?.also { goal1 ->
            val duration = PeriodFormat.getDefault().print(Period(0, 0, goal1.estimatedDuration, 0).normalizedStandard())
            txt_duration.setText(String.format(Locale.US, "%s", duration))
        }

        // Location
        edit_location.setText("")
    }

    /**
     * Validates the input
     */
    fun validate() {

        val titleValidator = EditTextPatternValidator(
                edit_title,
                getString(R.string.editgoal_title_error),
                null,
                null,
                EditTextPatternValidator.ALPHANUMERIC_WHITE_SPACE
        )

        val errorMessage = EditTextValidator.validate(titleValidator)

        if( errorMessage == null) {
            save()
        } else {
            Snackbar.make(layout, errorMessage, Snackbar.LENGTH_LONG).show()
        }
    }

    /**
     * Updates the goal object and sends it back
     */
    fun save() {
        setResult(Activity.RESULT_OK)
        finish()
    }
}