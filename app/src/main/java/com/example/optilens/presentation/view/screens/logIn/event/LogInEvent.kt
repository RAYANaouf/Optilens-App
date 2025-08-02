package com.example.optilens.presentation.view.screens.logIn.event



sealed interface LogInEvent {
    data class OnClientCodeChange(val code: String) : LogInEvent
    object OnLoginClick                             : LogInEvent
    object ClearError                               : LogInEvent
}