package com.example.newspaper.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newspaper.repository.NewsRepository

class NewsViewModelProviderFactory(
    val countryCode :String,
    val app: Application,
    val newsRepository: NewsRepository
) :ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(countryCode,app, newsRepository) as T
    }
}