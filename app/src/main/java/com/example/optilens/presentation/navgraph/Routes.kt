package com.example.optilens.presentation.navgraph

import kotlinx.serialization.Serializable


@Serializable
open class AppScreen()


@Serializable
object logInScreen : AppScreen()

@Serializable
object dashboardScreen : AppScreen()

@Serializable
object invoiceScreen : AppScreen()

@Serializable
object paymentScreen : AppScreen()

@Serializable
object accountScreen : AppScreen()
