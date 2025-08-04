package com.example.optilens.presentation.view.screens.dashboard.event.DashboardEvent

sealed class DashboardEvent {

    class GetInvoicesByCustomerCode(val code : String) : DashboardEvent()

}