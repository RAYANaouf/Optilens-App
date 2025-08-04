package com.example.optilens.presentation.view.screens.dashboard.viewModel

import android.content.Context
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
import com.example.optilens.presentation.view.screens.dashboard.event.DashboardEvent.DashboardEvent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val localUserManager: LocalUserManager,
    private val invoiceManager  : InvoiceManager,
    private val context: Context
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

    fun onEvent( event : DashboardEvent , onSuccess : ()->Unit , onFailure : () ->Unit){
        when(event){
            is DashboardEvent.GetInvoicesByCustomerCode -> {
                viewModelScope.launch {
                    val result = invoiceManager.GetAllInvoiceByCustomerCode(event.code)
                    if (result is GetInvoicesByCustomerCodeResponse.Success){
                        onSuccess()
                        invoices.clear()
                        invoices.addAll(result.message.message.pos_invoices)
                        invoices.addAll(result.message.message.sales_invoices)
                        Log.d("get all invoice by customer code : " , result.toString())
                        Toast.makeText(context , "success" , Toast.LENGTH_LONG).show()
                    }else if (result is GetInvoicesByCustomerCodeResponse.Failure){
                        onFailure()
                        Log.d("get all invoice by customer code : " , result.toString())
                        Toast.makeText(context , "Failure" , Toast.LENGTH_LONG).show()
                    }else if( result is GetInvoicesByCustomerCodeResponse.Exception){

                        onFailure()
                        Log.d("get all invoice by customer code : " , result.data.toString())
                        Toast.makeText(context , "Exception" , Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}