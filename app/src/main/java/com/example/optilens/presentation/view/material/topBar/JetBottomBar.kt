package com.example.optilens.presentation.view.material.topBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.optilens.R
import com.example.optilens.presentation.navgraph.AppScreen
import com.example.optilens.presentation.navgraph.accountScreen
import com.example.optilens.presentation.navgraph.dashboardScreen
import com.example.optilens.presentation.navgraph.invoiceScreen
import com.example.optilens.presentation.navgraph.paymentScreen
import com.example.optilens.presentation.theme.background_color_0
import com.example.optilens.presentation.theme.customWhite0
import com.example.optilens.presentation.theme.p_color1_dark


@Composable
fun JethingBottomBar(
    modifier : Modifier = Modifier,
    onClick  : (AppScreen)->Unit = {  }
) {

    Surface(
        shadowElevation = 6.dp,
        color = customWhite0,
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .drawBehind {
                        drawLine(
                            start = Offset.Zero,
                            end = Offset(x = size.width, y = 0f),
                            color = p_color1_dark,
                            strokeWidth = 4.dp.toPx()
                        )
                    }
                    .clickable {
                        onClick(dashboardScreen)
                    }
            ){
                Image(
                    painter = painterResource(R.drawable.home),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(23.dp)
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable {
                        onClick(invoiceScreen)
                    }
            ){
                Image(
                    painter = painterResource(R.drawable.invoice),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(23.dp)
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable {
                        onClick(paymentScreen)
                    }
            ){
                Image(
                    painter = painterResource(R.drawable.payment),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(23.dp)
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable {
                        onClick(accountScreen)
                    }
            ){
                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(23.dp)
                )
            }
        }
    }

}