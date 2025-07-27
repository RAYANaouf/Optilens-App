package com.example.optilens.presentation.view.screens.invoice

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier


@Composable
fun InvoiceScreen(
    modifier: Modifier = Modifier
) {

    var search by remember {
        mutableStateOf("")
    }


    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

    }

}