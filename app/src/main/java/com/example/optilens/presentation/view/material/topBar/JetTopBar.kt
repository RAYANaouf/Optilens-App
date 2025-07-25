package com.example.optilens.presentation.view.material.topBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.optilens.presentation.theme.customWhite0
import com.example.optilens.presentation.theme.p_color1_dark


@Composable
fun JethingTopBar(
    title    : String = "",
    modifier : Modifier = Modifier
) {

    Surface(
        shadowElevation = 6.dp,
        color = customWhite0,
        modifier = modifier
            .height(55.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .height(55.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight(600),
                            color = p_color1_dark
                        )
                    ){
                        append("D")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight(400),
                            color = p_color1_dark
                        )
                    ){
                        append("ashboard")
                    }
                },
                style = TextStyle(
                    fontSize = 16.sp,
                )
            )
        }
    }

}