package com.example.optilens.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.optilens.MainViewModel
import com.example.optilens.data.manager.AuthManager_imp
import com.example.optilens.data.manager.InvoiceManager_imp
import com.example.optilens.data.manager.LocalUserManager_imp
import com.example.optilens.domain.manager.AuthManager
import com.example.optilens.domain.manager.InvoiceManager
import com.example.optilens.domain.manager.LocalUserManager
import com.example.optilens.presentation.view.screens.account.viewModel.ProfileViewModel
import com.example.optilens.presentation.view.screens.dashboard.DashboardScreen
import com.example.optilens.presentation.view.screens.dashboard.viewModel.DashboardViewModel
import com.example.optilens.presentation.view.screens.invoice.viewModel.InvoiceViewModel
import com.example.optilens.presentation.view.screens.logIn.viewModel.LogInViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "USER-SETTING")


val koinModule = module {



    /***************    GSON     ***********************/

    single<Json> {
        Json {
            ignoreUnknownKeys = true // Ignore unknown keys during deserialization
            isLenient = true         // Allow lenient parsing of JSON
            encodeDefaults = true    // Include default values in serialization
        }
    }

    /**************   HttpClient   ***********************/

    single<HttpClient> {
        HttpClient(Android){
            install(Logging){
                level = LogLevel.ALL
            }

            install(ContentNegotiation) {
                json(get()) // 👈 Inject my custom Json instance here
            }

            // 🔧 ✅ FIX TO ENABLE HEADERS
            install(DefaultRequest) {
                header(HttpHeaders.UserAgent, "ktor-client") // Prevent Android from blocking headers
            }
        }
    }



    /********************  manager  ***********************/

    single<LocalUserManager>{
        LocalUserManager_imp(
            context = get()
        )
    }


    single<AuthManager> {
        AuthManager_imp(
            client = get()
        )
    }


    single<InvoiceManager> {
        InvoiceManager_imp(
            client = get()
        )
    }






    viewModel {
        MainViewModel(
            localUserManager = get()
        )
    }

    viewModel {
        InvoiceViewModel(
            localUserManager = get(),
            invoiceManager = get()
        )
    }

    viewModel {
        LogInViewModel(
            localUserManager = get(),
            authManager = get(),
            context = get()
        )
    }

    viewModel {
        DashboardViewModel(
            localUserManager = get(),
            invoiceManager = get()
        )
    }

    viewModel {
        ProfileViewModel(
            localUserManager = get()
        )
    }

}