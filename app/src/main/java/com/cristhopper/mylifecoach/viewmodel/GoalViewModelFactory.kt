package com.cristhopper.mylifecoach.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.cristhopper.mylifecoach.data.repository.GoalRepository

class GoalViewModelFactory (
        private val repository: GoalRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = GoalViewModel(repository) as T
}