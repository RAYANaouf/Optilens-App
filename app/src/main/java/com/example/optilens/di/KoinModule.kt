package com.example.optilens.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.optilens.MainViewModel
import com.example.optilens.presentation.view.screens.invoice.viewModel.InvoiceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "USER-SETTING")


val koinModule = module {


    viewModel {
        MainViewModel()
    }

    viewModel {
        InvoiceViewModel()
    }


}