package com.example.optilens.presentation.view.screens.invoiceDetails.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.optilens.presentation.view.screens.invoiceDetails.InvoiceDetailsUiState

class InvoiceDetailsViewModel(
    initialState: InvoiceDetailsUiState
) : ViewModel() {
    var uiState by mutableStateOf(initialState)
        private set
    // In a real ViewModel, you'd have functions to fetch data

    fun updateState(newState: InvoiceDetailsUiState) {
        uiState = newState
    }
}

