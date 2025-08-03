package com.example.optilens.presentation.view.screens.account.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.optilens.data.db.entities.Customer
import com.example.optilens.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ProfileViewModel(
    localUserManager: LocalUserManager
) : ViewModel() {


    var customer by mutableStateOf<Customer?>(null)
        private set

    init {
        viewModelScope.launch {
            localUserManager.readAccount().onEach {
                customer = it
            }.launchIn(viewModelScope)
        }
    }
}