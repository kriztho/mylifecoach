package com.cristhopper.mylifecoach.data.domain

data class User(val firstName: String, val lastName: String) {

    val calendar: Calendar = Calendar("default", "This is the default calendar")
}