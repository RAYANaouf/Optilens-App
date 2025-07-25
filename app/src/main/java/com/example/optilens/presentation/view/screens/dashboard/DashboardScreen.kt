package com.example.optilens.presentation.view.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.optilens.presentation.theme.background_color_0
import com.example.optilens.presentation.theme.background_color_1
import com.example.optilens.presentation.theme.customBlack3
import com.example.optilens.presentation.theme.customWhite1
import com.example.optilens.presentation.theme.customWhite2
import com.example.optilens.presentation.theme.customWhite4
import com.example.optilens.presentation.theme.p_color1
import com.example.optilens.presentation.theme.p_color1_dark
import com.example.optilens.presentation.theme.p_color4
import com.example.optilens.presentation.theme.p_color5


@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(background_color_0)
    ) {
        item {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp , vertical = 16.dp)
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        color = p_color1.copy(alpha = 0.25f)
                    )
                    .border(
                        width = 5.dp,
                        color = p_color1,
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp , top = 12.dp)
                ) {
                    Text(
                        text = "OutStanding Amount",
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight(600),
                            color = p_color1
                        )
                    )
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 16.dp , top = 12.dp)
                ) {
                    Text(
                        text = "125 000 DA",
                        style = TextStyle(
                            fontSize = 27.sp,
                            fontWeight = FontWeight(700),
                            color = p_color1_dark
                        )
                    )
                }


            }
        }

        item {
            Spacer(Modifier.height(35.dp))
            Text(
                text = "Recent Invoices",
                style = TextStyle(
                    fontSize = 24.sp,
                    color = customBlack3,
                    fontWeight = FontWeight(500)
                ),
                modifier = Modifier
                    .padding(start = 16.dp)
            )
            Spacer(Modifier.height(15.dp))
        }

        items(
            count = 10,
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(500)
                                )
                            ){
                                append("Invoice : ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(400)
                                )
                            ){
                                append("F/POS/2025/059448")
                            }
                        },
                        style = TextStyle(
                            fontSize = 16.sp,
                        )
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(470)
                                )
                            ){
                                append("TTC : ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(400)
                                )
                            ){
                                append("8800 DA")
                            }
                        },
                        style = TextStyle(
                            fontSize = 16.sp,
                        )
                    )
                }
                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier
                ) {
                    Text(
                        text = "5500 DA",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(550),
                            color = p_color5
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

        }
    }

}



@Preview
@Composable
private fun DashboardScreen_prev() {
    DashboardScreen()
}