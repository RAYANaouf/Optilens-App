package com.example.optilens.presentation.navgraph

import android.app.Activity
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.optilens.presentation.theme.background_color_0
import com.example.optilens.presentation.theme.customWhite0
import com.example.optilens.presentation.theme.p_color2
import com.example.optilens.presentation.theme.p_color4
import com.example.optilens.presentation.view.screens.dashboard.DashboardScreen
import com.example.optilens.presentation.view.screens.invoice.InvoiceScreen
import com.example.optilens.unit.responsiveScreenTools.WindowInfo


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun  NavGraph(
    navController    : NavHostController,
    startDestination : AppScreen,
    currentPage      : (AppScreen)->Unit = {},
    currentScene     : (String)->Unit    = {},
    windowInfo       : WindowInfo,
    paddingValues    : PaddingValues
) {

    set_system_bars_color(
        statusBarColor     =  background_color_0,
        navigationBarColor =  background_color_0
    )

    SharedTransitionLayout{

        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.fillMaxSize()
        ) {

            /*********************************** dashboard  *********************************/
            composable<dashboardScreen> {


                set_system_bars_color(
                    statusBarColor     =  customWhite0,
                    navigationBarColor =  customWhite0
                )

                SideEffect {
                    currentPage(dashboardScreen)
                }


                DashboardScreen(
                    modifier = Modifier
                        .padding(paddingValues)
                )
            }
            /*********************************** invoice  *********************************/
            composable<invoiceScreen> {


                SideEffect {
                    currentPage(invoiceScreen)
                }

                InvoiceScreen(
                    modifier = Modifier
                        .padding(paddingValues)
                )

            }
            /*********************************** payment  *********************************/
            composable<paymentScreen> {


                SideEffect {
                    currentPage(paymentScreen)
                }

            }
            /*********************************** account  *********************************/
            composable<accountScreen> {



                SideEffect {
                    currentPage(accountScreen)
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