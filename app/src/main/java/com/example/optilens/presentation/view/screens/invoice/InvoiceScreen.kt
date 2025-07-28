package com.example.optilens.presentation.view.screens.invoice

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.optilens.presentation.navgraph.AppScreen
import com.example.optilens.presentation.navgraph.invoiceDetailsScreen
import com.example.optilens.presentation.theme.p_color1
import com.example.optilens.presentation.theme.p_color1_dark
import com.example.optilens.presentation.theme.p_color5
import com.example.optilens.presentation.view.material.AlphaTextFields.JethingsTextField


@Composable
fun InvoiceScreen(
    onNavigate : (AppScreen)->Unit = {},
    modifier: Modifier = Modifier
) {

    var search by remember {
        mutableStateOf("")
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Spacer(Modifier.height(55.dp))

        JethingsTextField(
            text = search,
            onValueChange = {
                search = it
            },
            textFieldStyle = TextStyle(),
            hint = "Search",
            hintStyle = TextStyle(),
            modifier = Modifier
                .height(45.dp)
                .fillMaxWidth(1f)
                .padding(horizontal = 16.dp)
        )

        Spacer(Modifier.height(25.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(horizontal = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        width = 2.dp,
                        color = p_color1_dark,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .background(p_color1.copy(alpha = 0.2f))
                    .clickable {

                    }
                    .padding(horizontal = 16.dp , vertical = 6.dp)
            ) {
                Text(
                    text = "Date",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500)
                    )
                )
            }

            Spacer(Modifier.width(16.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        width = 2.dp,
                        color = p_color1_dark,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .background(p_color1.copy(alpha = 0.2f))
                    .clickable {

                    }
                    .padding(horizontal = 16.dp , vertical = 6.dp)
            ) {
                Text(
                    text = "Status",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500)
                    )
                )
            }
        }

        Spacer(Modifier.height(30.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "All Invoice",
                style = TextStyle(
                    fontSize = 26.sp,
                    fontWeight = FontWeight(600)
                )
            )
        }


        Spacer(Modifier.height(16.dp))


        for ( i in 1..20){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onNavigate(invoiceDetailsScreen)
                    }
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

            Spacer(Modifier.height(16.dp))
        }
    }

}


@Preview
@Composable
private fun InvoiceScreen_prev() {
    InvoiceScreen()
}