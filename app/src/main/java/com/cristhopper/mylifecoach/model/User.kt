package com.cristhopper.mylifecoach.model

data class User(val firstName: String, val lastName: String) {

    val calendar: Calendar = Calendar("default", "This is the default calendar")
}