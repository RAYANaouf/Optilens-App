package com.example.optilens.data.api.req_res_classes.InvoicesModules.GetInvoicesByCustomerCode

import com.example.optilens.data.db.entities.Customer
import com.example.optilens.data.db.entities.Invoice
import kotlinx.serialization.Serializable



//request
@Serializable
data class GetInvoicesByCustomerCodeRequest(
    val code    : String
)



//response

@Serializable
sealed class GetInvoicesByCustomerCodeResponse{
    class Success(val message : GetInvoicesByCustomerCodeSuccessResponse)  : GetInvoicesByCustomerCodeResponse()
    class Failure(val data : GetInvoicesByCustomerCodeFailureResponse)     : GetInvoicesByCustomerCodeResponse()
    class Exception(val data : kotlin.Exception)       : GetInvoicesByCustomerCodeResponse()
}

@Serializable
data class GetInvoicesByCustomerCodeSuccessResponse(
    val message : LogInSuccessMessageResponse
)

@Serializable
data class LogInSuccessMessageResponse(
    val sales_invoices  : List<Invoice>,
    val pos_invoices    : List<Invoice>,
)
@Serializable
data class GetInvoicesByCustomerCodeFailureResponse(
    val error      : String = "",
)