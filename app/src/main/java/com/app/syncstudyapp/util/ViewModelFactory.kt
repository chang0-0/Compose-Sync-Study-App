package com.app.syncstudyapp.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.syncstudyapp.domain.Repository.NetworkDataRepository
import com.app.syncstudyapp.screen.HomeResponseViewModel

class ViewModelFactory(private val repo: NetworkDataRepository) :
    ViewModelProvider.Factory { // End of ViewModelFactory class
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeResponseViewModel::class.java)) {
            return HomeResponseViewModel(repo) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    } // End of create()
} // End of ViewModelFactory class