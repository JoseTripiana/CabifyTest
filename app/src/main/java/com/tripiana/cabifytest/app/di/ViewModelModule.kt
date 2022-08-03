package com.tripiana.cabifytest.app.di

import com.tripiana.cabifytest.app.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MainViewModel(get()) }
}