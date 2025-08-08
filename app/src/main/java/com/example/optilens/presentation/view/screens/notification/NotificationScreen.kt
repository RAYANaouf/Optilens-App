package com.example.optilens.presentation.view.screens.notification

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.optilens.data.db.entities.Notification


@Composable
fun NotificationScreen(
    notifications : List<Notification> = emptyList(),
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {


        items(notifications){

        }


    }

}


@Preview
@Composable
private fun NotificationScreen_prev() {
    NotificationScreen()
}