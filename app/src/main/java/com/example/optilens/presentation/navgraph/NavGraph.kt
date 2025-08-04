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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.optilens.presentation.theme.p_color1_dark
import com.example.optilens.presentation.theme.p_color2
import com.example.optilens.presentation.theme.p_color4
import com.example.optilens.presentation.view.screens.account.ProfileScreen
import com.example.optilens.presentation.view.screens.account.viewModel.ProfileViewModel
import com.example.optilens.presentation.view.screens.dashboard.DashboardScreen
import com.example.optilens.presentation.view.screens.dashboard.viewModel.DashboardViewModel
import com.example.optilens.presentation.view.screens.invoice.InvoiceScreen
import com.example.optilens.presentation.view.screens.invoiceDetails.InvoiceDetailsScreen
import com.example.optilens.presentation.view.screens.logIn.ClientLoginUiState
import com.example.optilens.presentation.view.screens.logIn.LoginScreen
import com.example.optilens.presentation.view.screens.logIn.event.LogInEvent
import com.example.optilens.presentation.view.screens.logIn.viewModel.LogInViewModel
import com.example.optilens.presentation.view.screens.payment.PaymentScreen
import com.example.optilens.unit.responsiveScreenTools.WindowInfo
import org.koin.androidx.compose.koinViewModel


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

                val viewModel = koinViewModel<DashboardViewModel>()


                DashboardScreen(
                    onEvent = viewModel::onEvent,
                    customer = viewModel.customer,
                    invoices = viewModel.invoices,
                    modifier = Modifier
                        .padding(paddingValues)
                )
            }
            /*********************************** invoice  *********************************/
            composable<invoiceScreen> {


                set_system_bars_color(
                    statusBarColor     =  customWhite0,
                    navigationBarColor =  customWhite0
                )

                SideEffect {
                    currentPage(invoiceScreen)
                }

                InvoiceScreen(
                    onNavigate = {
                        navController.navigate(it)
                    },
                    modifier = Modifier
                        .padding(paddingValues)
                )

            }
            /*********************************** payment  *********************************/
            composable<paymentScreen> {


                set_system_bars_color(
                    statusBarColor     =  customWhite0,
                    navigationBarColor =  customWhite0
                )

                SideEffect {
                    currentPage(paymentScreen)
                }


                PaymentScreen(
                    modifier = Modifier
                        .padding(paddingValues)
                )

            }
            /*********************************** account  *********************************/
            composable<accountScreen> {


                set_system_bars_color(
                    statusBarColor     =  customWhite0,
                    navigationBarColor =  customWhite0
                )

                SideEffect {
                    currentPage(accountScreen)
                }

                val viewModel = koinViewModel<ProfileViewModel>()


                ProfileScreen(
                    customer = viewModel.customer,
                    modifier = Modifier
                        .padding(paddingValues)
                )

            }


            /********************************  invoice details  ******************************/

            composable<invoiceDetailsScreen> {


                set_system_bars_color(
                    statusBarColor     =  customWhite0,
                    navigationBarColor =  customWhite0
                )

                SideEffect {
                    currentPage(invoiceDetailsScreen)
                }

                InvoiceDetailsScreen(
                    modifier = Modifier
                        .padding(paddingValues)
                )

            }


            /********************************  log in    ******************************/

            composable<logInScreen> {


                set_system_bars_color(
                    statusBarColor     =  customWhite0,
                    navigationBarColor =  p_color1_dark
                )

                SideEffect {
                    currentPage(logInScreen)
                }

                val viewModel = koinViewModel<LogInViewModel>()

                var uiState by remember {
                    mutableStateOf(
                        ClientLoginUiState(
                            clientCode = "",
                            isLoading = false,
                            errorMessage = null
                        )
                    )
                }

                LoginScreen(
                    uiState  = uiState,
                    onEvent = {
                        when(it){
                            is LogInEvent.OnClientCodeChange -> {
                                uiState = uiState.copy(clientCode = it.code)
                            }
                            is LogInEvent.OnLoginClick ->{
                                viewModel.onEvent(
                                    it,{
                                        navController.navigate(dashboardScreen){
                                            popUpTo(logInScreen){
                                                inclusive = true
                                            }
                                        }
                                    },{

                                    }
                                )
                            }
                            else ->{

                            }

                        }
                    },
                    modifier = Modifier
                        .padding(paddingValues)
                )

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