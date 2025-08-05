package com.example.optilens.presentation.view.screens.invoice.event

import com.example.optilens.presentation.view.screens.dashboard.event.DashboardEvent

sealed class InvoiceEvent {

    class GetInvoicesByCustomerCode(val code : String) : InvoiceEvent()

}