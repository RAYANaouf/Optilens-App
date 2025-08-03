package com.example.optilens.presentation.view.screens.logIn.event



sealed interface LogInEvent {
    data class OnClientCodeChange(val code: String) : LogInEvent
    data class OnLoginClick(val code: String)       : LogInEvent
    object ClearError                               : LogInEvent
}