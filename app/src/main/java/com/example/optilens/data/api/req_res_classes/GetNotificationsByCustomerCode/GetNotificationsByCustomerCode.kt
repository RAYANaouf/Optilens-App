package com.example.optilens.data.api.req_res_classes.InvoicesModules.GetInvoicesByCustomerCode

import com.example.optilens.data.db.entities.Invoice
import com.example.optilens.data.db.entities.Notification
import kotlinx.serialization.Serializable



//request
@Serializable
data class GetNotificationsByCustomerCodeRequest(
    val code    : String
)



//response

@Serializable
sealed class GetNotificationsByCustomerCodeResponse{
    class Success(val data : GetNotificationsByCustomerCodeSuccessResponse)  : GetNotificationsByCustomerCodeResponse()
    class Failure(val data : GetNotificationsByCustomerCodeFailureResponse)     : GetNotificationsByCustomerCodeResponse()
    class Exception(val data : kotlin.Exception)                                : GetNotificationsByCustomerCodeResponse()
}

@Serializable
data class GetNotificationsByCustomerCodeSuccessResponse(
    val message : GetNotificationsSuccessMessageResponse
)

@Serializable
data class GetNotificationsSuccessMessageResponse(
    val notification  : List<Notification>,
)
@Serializable
data class GetNotificationsByCustomerCodeFailureResponse(
    val error      : String = "",
)