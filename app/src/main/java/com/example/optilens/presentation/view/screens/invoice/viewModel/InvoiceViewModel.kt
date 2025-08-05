package com.example.optilens.presentation.view.screens.invoice.viewModel

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.optilens.data.api.req_res_classes.InvoicesModules.GetInvoicesByCustomerCode.GetInvoicesByCustomerCodeResponse
import com.example.optilens.data.db.entities.Customer
import com.example.optilens.data.db.entities.Invoice
import com.example.optilens.domain.manager.InvoiceManager
import com.example.optilens.domain.manager.LocalUserManager
import com.example.optilens.presentation.view.screens.dashboard.event.DashboardEvent
import com.example.optilens.presentation.view.screens.invoice.event.InvoiceEvent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class InvoiceViewModel(
    private val localUserManager: LocalUserManager,
    private val invoiceManager  : InvoiceManager
) : ViewModel() {


    var customer by mutableStateOf<Customer?>(null)
        private set

    var invoices = mutableStateListOf<Invoice>()


    init {
        viewModelScope.launch {
            localUserManager.readAccount().onEach {
                customer = it
            }.launchIn(viewModelScope)
        }
    }

    fun onEvent(event : InvoiceEvent, onSuccess : ()->Unit, onFailure : () ->Unit){
        when(event){
            is InvoiceEvent.GetInvoicesByCustomerCode -> {
                viewModelScope.launch {
                    val result = invoiceManager.GetAllInvoiceByCustomerCode(event.code)
                    if (result is GetInvoicesByCustomerCodeResponse.Success){
                        onSuccess()
                        invoices.clear()
                        invoices.addAll(result.message.message.pos_invoices)
                        invoices.addAll(result.message.message.sales_invoices)
                    }else if (result is GetInvoicesByCustomerCodeResponse.Failure){
                        onFailure()
                        Log.d("get all invoice by customer code : " , result.toString())
                    }else if( result is GetInvoicesByCustomerCodeResponse.Exception){
                        onFailure()
                        Log.d("get all invoice by customer code : " , result.data.toString())
                    }
                }
            }
        }
    }


}