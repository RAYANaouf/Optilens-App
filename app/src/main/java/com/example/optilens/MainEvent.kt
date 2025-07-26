package com.example.optilens

import com.example.optilens.presentation.navgraph.AppScreen


sealed class MainEvent {

    class ScreenChangeEvent(val screen : AppScreen) : MainEvent()


}
