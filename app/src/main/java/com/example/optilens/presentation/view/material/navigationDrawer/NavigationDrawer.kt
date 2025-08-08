package com.example.optilens.presentation.view.material.navigationDrawer

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.example.optilens.presentation.navgraph.AppScreen
import com.example.optilens.presentation.theme.background_color_0
import com.example.optilens.presentation.theme.customBlack3
import com.example.optilens.presentation.theme.p_color1
import com.example.optilens.presentation.theme.p_color1_dark
import com.example.optilens.presentation.theme.p_color2
import kotlinx.coroutines.delay


data class DrawerItem(
    val text : String,
    @DrawableRes val icon : Int
)

@Composable
fun NavigationDrawer(
    onEvent   : (MainEvent, onSuccess : ()->Unit, onFailure : ()->Unit, )->Unit = { _, _, _->},
    modifier  : Modifier = Modifier,
    items     : List<DrawerItem> = emptyList(),
    onNavigate   : (AppScreen) -> Unit = {},
    onClose      : () -> Unit = {} // Optional callback if needed
) {


    Surface(
        color = Color.Transparent,
        tonalElevation = 4.dp,
        shadowElevation = 0.dp,
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth(0.75f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 32.dp)
        ) {

            // Logo
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.optilens_logo),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                )
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = p_color2 , fontSize = 35.sp , fontWeight = FontWeight(900))){
                                append("")
                            }
                            withStyle(style = SpanStyle(color = p_color1 , fontSize = 32.sp , fontWeight = FontWeight(600))){
                                append("")
                            }
                        }
                    )
                }
            }



            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 65.dp)
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                items.forEachIndexed { index, item ->
                    drawerItem(
                        text = item.text,
                        icon = item.icon,
                        delayMillis = index * 200L,
                        onClick = {
                            onClose()
                        }
                    )
                }
            }

        }
    }

}

@Composable
fun drawerItem(
    text : String,
    @DrawableRes icon : Int,
    delayMillis: Long,
    onClick: () -> Unit
) {
    val visible = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(delayMillis)
        visible.value = true
    }

    AnimatedVisibility(
        visible = visible.value,
        enter = fadeIn(animationSpec = tween(durationMillis = 800)) + slideInHorizontally(
            initialOffsetX = { -it / 2 },
            animationSpec = tween(durationMillis = 800)
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(55.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .clickable { onClick() }
                .padding(start = 8.dp)
        ) {

            Icon(
                painter = painterResource(id = icon),
                contentDescription =null,
                tint = p_color1,
                modifier = Modifier
                    .size(26.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = text,
                style = TextStyle(
                    fontSize = 16.sp,
                    color    = customBlack3
                )
            )
        }
    }
}


@Composable
fun NavigationDrawer_prev(
    modifier: Modifier = Modifier
) {
    NavigationDrawer()
}