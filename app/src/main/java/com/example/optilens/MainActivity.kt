package com.example.optilens

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.optilens.presentation.navgraph.NavGraph
import com.example.optilens.presentation.navgraph.dashboardScreen
import com.example.optilens.presentation.theme.OptilensTheme
import com.example.optilens.presentation.theme.background_color_0
import com.example.optilens.presentation.theme.customWhite0
import com.example.optilens.presentation.theme.p_color1
import com.example.optilens.presentation.theme.p_color2
import com.example.optilens.presentation.view.material.topBar.JethingBottomBar
import com.example.optilens.presentation.view.material.topBar.JethingTopBar
import com.example.optilens.unit.responsiveScreenTools.rememberWindowInfo
import org.koin.androidx.compose.koinViewModel
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OptilensTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    innerPadding.calculateTopPadding()
                    mainScreen()
                }
            }
        }
    }
}

@Composable
fun mainScreen(
    modifier         : Modifier = Modifier
) {

    //   set nav host controller with main view model
    val navController = rememberNavController()

    val windowInfo = rememberWindowInfo()

    val viewModel = koinViewModel<MainViewModel>()


    var drawerState by remember{
        mutableStateOf(false)
    }


    val configuration = LocalConfiguration.current
    val density = LocalDensity.current.density
    val screenWidthDp = remember{
        derivedStateOf { (configuration.screenWidthDp * density).roundToInt() }
    }

    val offsetValue by remember { derivedStateOf { (screenWidthDp.value / 6.0).dp } }
    val animatedOffset by animateDpAsState(
        targetValue = if (drawerState) offsetValue else 0.dp,
        label = "animatedOffset",
    )
    val animatedScale by animateFloatAsState(
        targetValue = if (drawerState) 0.60f else 1f,
        label = "animatedOffset",
    )

    BackHandler(enabled = drawerState) {
        drawerState = false
    }



    Box(
        modifier = modifier
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .safeDrawingPadding()
                .align(Alignment.TopEnd)
                .size(85.dp)
                .background(p_color1)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.close) ,
                contentDescription = null ,
                tint = p_color1,
                modifier = Modifier
                    .size(26.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null // This disables the ripple effect
                    ) {
                        drawerState = !drawerState
                    }
            )
        }

        val context = LocalContext.current

        Row {
            AnimatedVisibility(
                visible = drawerState
            ) {
            }
        }

        Surface(
            shape = RoundedCornerShape( if(drawerState) 14.dp else 0.dp ),
            shadowElevation = 12.dp,
            color = p_color1,
            modifier = Modifier
                .offset(animatedOffset)
                .scale(animatedScale)
                .fillMaxSize()
        ) {
            Scaffold(
                topBar = {
                    Column(
                        modifier = Modifier
                            .windowInsetsPadding(WindowInsets.statusBars)
                    ) {
                        AnimatedVisibility(visible = true) {
                            JethingTopBar(
                                title = viewModel.topBarTxt
                            )
                        }
                    }
                },
                bottomBar = {
                    Column(
                        modifier = Modifier
                            .windowInsetsPadding(WindowInsets.navigationBars)
                    ) {
                        AnimatedVisibility(visible = true) {
                            JethingBottomBar(
                                selectedScreen = viewModel.current_screen,
                                onClick = {
                                    navController.navigate(it)
                                }
                            )
                        }
                    }
                },
                containerColor = background_color_0,
                modifier = Modifier
                    .fillMaxSize()
            ) {padding->


                NavGraph(
                    navController = navController,
                    startDestination = dashboardScreen,
                    paddingValues = padding,
                    windowInfo = windowInfo,
                    currentScene = {

                    },
                    currentPage = {
                        viewModel.onEvent(MainEvent.ScreenChangeEvent(it))
                    }
                )

            }
        }


        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(p_color2)
        ) {
            AnimatedVisibility(visible = drawerState) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(bottom = 45.dp)
                        .fillMaxWidth()
                ){
                    Text(
                        text = "Powered by Jethings",
                        fontSize = 20.sp
                    )
                }
            }
        }
    }

}


@Composable
fun set_system_bars_color(
    statusBarColor     : Color,
    navigationBarColor : Color
) {
    val window1 = LocalView.current.context as Activity

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            window1.window.statusBarColor     = statusBarColor.toArgb()
            window1.window.navigationBarColor = navigationBarColor.toArgb()

        }
    }

}