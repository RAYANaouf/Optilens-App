package com.example.optilens.presentation.view.material.topBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.optilens.MainEvent
import com.example.optilens.R
import com.example.optilens.presentation.theme.customWhite0
import com.example.optilens.presentation.theme.p_color1_dark
import com.example.optilens.presentation.theme.p_color2


@Composable
fun JethingTopBar(
    title    : String = "",
    onEvent  : (MainEvent)->Unit = {},
    modifier : Modifier = Modifier
) {


    var showMenu by rememberSaveable {
        mutableStateOf(false)
    }

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
            Image(
                painter = painterResource(R.drawable.optilens_logo),
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .height(55.dp)
                    .width(110.dp)
                    .clickable {
                        onEvent(MainEvent.OpenDrawerEvent)
                    }
                    .padding(start = 16.dp)
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight(600),
                                color = p_color2
                            )
                        ){
                            if (title.length > 0){
                                append(title[0])
                            }
                        }
                        withStyle(
                            style = SpanStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight(400),
                                color = p_color1_dark
                            )
                        ){
                            if (title.length > 1){
                                append(title.substring(1))
                            }
                        }
                    },
                    style = TextStyle(
                        fontSize = 16.sp,
                    )
                )
            }

            Box(
                contentAlignment =  Alignment.Center,
                modifier = Modifier
                    .width(110.dp)
                    .fillMaxHeight()
                    .clickable {
                        showMenu = !showMenu
                    }
            ){
                Image(
                    painter = painterResource(id = R.drawable.menu) ,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(8.dp)
                )
                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = {
                        showMenu = false
                    },
                    modifier = Modifier
                ) {
                    DropdownMenuItem(
                        text = {
                            Box(
                                contentAlignment = Alignment.CenterStart,
                                modifier = Modifier
                                    .height(45.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(text = "Log Out")
                            }
                        },
                        onClick = {
                            onEvent(MainEvent.LogOutEvent)
                        }
                    )
                }
            }


        }
    }

}