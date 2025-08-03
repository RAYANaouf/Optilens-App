package com.example.optilens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.optilens.data.db.entities.Account
import com.example.optilens.data.db.entities.Customer
import com.example.optilens.domain.manager.LocalUserManager
import com.example.optilens.presentation.navgraph.AppScreen
import com.example.optilens.presentation.navgraph.accountScreen
import com.example.optilens.presentation.navgraph.dashboardScreen
import com.example.optilens.presentation.navgraph.invoiceScreen
import com.example.optilens.presentation.navgraph.logInScreen
import com.example.optilens.presentation.navgraph.paymentScreen
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel(
    private val localUserManager : LocalUserManager,
) : ViewModel() {

    var startDestination by mutableStateOf<AppScreen>(logInScreen)
        private set


    //topbar
    var show_topbar    by mutableStateOf(false)
        private set
    var topbar_shadow  by mutableStateOf(0.dp)
        private set
    var topBarImg by mutableStateOf<Int?>(null)
        private set
    var topBarTxt by mutableStateOf<String>("")
        private set


    var current_screen : AppScreen by mutableStateOf(dashboardScreen)
        private set
    var current_scene  : String    by mutableStateOf("")
        private set


    //bottom bar
    var show_bottombar by mutableStateOf(false)
        private set
    var bottombar_shadow  by mutableStateOf(0.dp)
        private set

    //navigation drawer

    var show_navigationDrawer    by mutableStateOf(false)
        private set




    //logic vars
    var customer : Customer? by mutableStateOf(null)
        private set


    init {

        viewModelScope.launch {
            localUserManager.readAccount().onEach { acc ->
                if (acc != null) {
                    val _account = localUserManager.readAccount().first()
                    if (_account != null) {
                        Log.d("success to log in", "success to read account : ${acc}")
                        customer = _account
                        startDestination = dashboardScreen
                    } else {
                        startDestination = logInScreen
                    }

                } else {
                    startDestination = logInScreen
                }

            }.launchIn(viewModelScope)

        }
    }





    private fun setTopBarInfo( txt : String?) {
        if (txt != null)
            topBarTxt = txt

    }


    private fun setCurrentScreen( appScreen : AppScreen) {
        current_screen = appScreen
        when(current_screen){
            logInScreen ->{
                show_topbar      =  false
                topbar_shadow    =  2.dp
                setTopBarInfo( txt = "" )

                //bottom bar
                show_bottombar   =  false
                bottombar_shadow =  0.dp

                //navigation drawer
                show_navigationDrawer = false
            }
            dashboardScreen->{
                show_topbar      =  true
                topbar_shadow    =  2.dp
                setTopBarInfo( txt = "Dashboard" )

                //bottom bar
                show_bottombar   =  true
                bottombar_shadow =  0.dp

                //navigation drawer
                show_navigationDrawer = true

            }
            invoiceScreen->{
                show_topbar      =  true
                topbar_shadow    =  2.dp
                setTopBarInfo( txt = "Invoice" )

                //bottom bar
                show_bottombar   =  true
                bottombar_shadow =  0.dp

                //navigation drawer
                show_navigationDrawer = true

            }
            paymentScreen->{
                show_topbar      =  true
                topbar_shadow    =  2.dp
                setTopBarInfo( txt = "Payment" )

                //bottom bar
                show_bottombar   =  true
                bottombar_shadow =  0.dp

                //navigation drawer
                show_navigationDrawer = true

            }
            accountScreen->{
                show_topbar      =  true
                topbar_shadow    =  2.dp
                setTopBarInfo( txt = "Profile" )

                //bottom bar
                show_bottombar   =  true
                bottombar_shadow =  0.dp

                //navigation drawer
                show_navigationDrawer = true

            }


            else ->{
                //top bar
                show_topbar      =  true
                topbar_shadow    =  0.dp
                //bottom bar
                show_bottombar   =  false
                bottombar_shadow =  0.dp

                //navigation drawer
                show_navigationDrawer = false
            }

        }
    }


    fun onEvent(event : MainEvent, onSuccees : () -> Unit = {}, onFailure : () -> Unit = {}){
        when(event){
            is MainEvent.ScreenChangeEvent -> {
                setCurrentScreen(event.screen)
            }
            else ->{

            }
        }

    }

}