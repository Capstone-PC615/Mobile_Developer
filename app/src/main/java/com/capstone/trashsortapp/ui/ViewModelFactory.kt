package com.capstone.trashsortapp.ui

import androidx.lifecycle.ViewModelProvider
import com.capstone.trashsortapp.data.remote.Repository

class ViewModelFactory private constructor(private val repositoryMain: Repository) :
    ViewModelProvider.NewInstanceFactory() {
}