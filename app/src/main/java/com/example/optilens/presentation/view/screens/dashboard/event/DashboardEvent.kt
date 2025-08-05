package com.example.optilens.presentation.view.screens.dashboard.event

sealed class DashboardEvent {

    class GetInvoicesByCustomerCode(val code : String) : DashboardEvent()

}