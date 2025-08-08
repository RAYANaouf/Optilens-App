package com.example.optilens

import com.example.optilens.presentation.navgraph.AppScreen


sealed class MainEvent {

    class ScreenChangeEvent(val screen : AppScreen) : MainEvent()



    object LogOutEvent : MainEvent()
    object OpenDrawerEvent : MainEvent()


    data class GetNotificationsByCustomerCodeEvent(val code : String) : MainEvent()

}
