package com.cristhopper.mylifecoach.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.cristhopper.mylifecoach.data.repository.GoalRepository

class GoalListViewModelFactory(
        private val repository: GoalRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = GoalListViewModel(repository) as T
}