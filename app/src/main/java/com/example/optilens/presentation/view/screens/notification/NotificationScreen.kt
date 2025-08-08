package com.example.optilens.presentation.view.screens.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.optilens.R
import com.example.optilens.data.db.entities.Notification
import com.example.optilens.presentation.theme.background_color_0
import com.example.optilens.presentation.theme.customBlack3
import com.example.optilens.presentation.theme.customWhite0
import com.example.optilens.presentation.theme.p_color1_dark


@Composable
fun NotificationScreen(
    code : String = "" ,
    notifications : List<Notification> = emptyList(),
    modifier: Modifier = Modifier
) {

    LazyColumn(
        contentPadding = PaddingValues(top = 16.dp),
        modifier = modifier
            .fillMaxSize()
    ) {




        items(notifications){
            notificationItem(
                notification = it
            )
            Spacer(Modifier.height(16.dp))
        }


    }

}


@Composable
fun notificationItem(
    notification : Notification ,
    modifier     : Modifier = Modifier
) {

    Surface(
        color = customWhite0,
        shadowElevation = 6.dp,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 6.dp , bottom = 6.dp, start = 6.dp , end = 6.dp )
        ) {
            Column(
                modifier = modifier
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.notification) ,
                        contentDescription = null ,
                        tint = p_color1_dark,
                        modifier = Modifier
                            .size(16.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = notification.title ?: "",
                        style = TextStyle(
                            fontSize   = 16.sp,
                            fontWeight = FontWeight(600),
                            color      = customBlack3
                        )
                    )
                }
                Spacer(Modifier.height(6.dp))
                Text(
                    text = notification.msg
                )
            }
        }
    }

}


@Preview
@Composable
private fun NotificationScreen_prev() {
    NotificationScreen(
        notifications = listOf(
            Notification(name = "Oussama-8-8-2025-0004" , title = "Payment Request" , msg = "Payment Request" ),
            Notification(name = "Oussama-8-8-2025-0004" , title = "Payment Request" , msg = "Payment Request" ),
            Notification(name = "Oussama-8-8-2025-0004" , title = "Payment Request" , msg = "Payment Request" ),
            Notification(name = "Oussama-8-8-2025-0004" , title = "Payment Request" , msg = "Payment Request" ),
        ),
        modifier = Modifier
            .fillMaxSize()
            .background(background_color_0)
    )
}