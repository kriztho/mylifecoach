package com.cristhopper.mylifecoach.ui.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.cristhopper.mylifecoach.R
import com.cristhopper.mylifecoach.data.dao.AppDatabase
import com.cristhopper.mylifecoach.data.repository.GoalRepository
import com.cristhopper.mylifecoach.ui.fragments.CalendarFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import com.cristhopper.mylifecoach.utils.inTransaction
import com.cristhopper.mylifecoach.ui.fragments.GoalListFragment
import com.cristhopper.mylifecoach.utils.InjectorUtils

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var isShowingCalendar = false
    private lateinit var listFragment: GoalListFragment
    private lateinit var calendarFragment: CalendarFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        setupFab()
        setupDrawer()
        setupDefaultView()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_switch -> {
                switchView()
                return true
            }
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {

            R.id.nav_list-> {
                showListView()
            }

            R.id.nav_calendar-> {
                showCalendarView()
            }

            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            if (supportFragmentManager.backStackEntryCount > 1)
                supportFragmentManager.popBackStack()
            else
                finish()
        }
    }

    fun setupFab() {

        fab.setOnClickListener { view ->
            // New goal
            val showGoalIntent = Intent(this, EditGoalActivity::class.java)
            startActivity(showGoalIntent)
        }
    }

    fun setupDrawer() {
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    /**
     * Show List View as default
     */
    fun setupDefaultView() {

        listFragment = GoalListFragment()
        calendarFragment = CalendarFragment()

        supportFragmentManager.inTransaction {
            replace(R.id.container, listFragment)
            addToBackStack("home")
        }
        nav_view.setCheckedItem(R.id.nav_list)
    }

    /**
     * Switches the main view between list and calendar views
     */
    fun switchView() {

        if (isShowingCalendar) {
            showListView()

        } else {
            showCalendarView()
        }
    }

    fun showCalendarView() {

        //replace to calendar
        supportFragmentManager.inTransaction {
            replace(R.id.container, calendarFragment)
            addToBackStack("calendar")
        }

        nav_view.setCheckedItem(R.id.nav_calendar)
        isShowingCalendar = true
    }

    fun showListView() {

        //popback to home
        supportFragmentManager.popBackStack("home", 0)

        nav_view.setCheckedItem(R.id.nav_list)
        isShowingCalendar = false
    }
}
