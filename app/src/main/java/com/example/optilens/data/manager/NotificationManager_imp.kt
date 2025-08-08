package com.example.optilens.data.manager

import android.util.Log
import com.example.optilens.data.api.req_res_classes.InvoicesModules.GetInvoicesByCustomerCode.GetInvoicesByCustomerCodeRequest
import com.example.optilens.data.api.req_res_classes.InvoicesModules.GetInvoicesByCustomerCode.GetInvoicesByCustomerCodeResponse
import com.example.optilens.data.api.req_res_classes.InvoicesModules.GetInvoicesByCustomerCode.GetNotificationsByCustomerCodeRequest
import com.example.optilens.data.api.req_res_classes.InvoicesModules.GetInvoicesByCustomerCode.GetNotificationsByCustomerCodeResponse
import com.example.optilens.data.db.entities.Notification
import com.example.optilens.domain.manager.NotificationManager
import com.example.optilens.unit.objects.Constants.BASE_URL
import com.example.optilens.unit.objects.Constants.GET_INVOICES_BY_CUSTOMER_CODE
import com.example.optilens.unit.objects.Constants.GET_NOTIFICATIONS_BY_CUSTOMER_CODE
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

class NotificationManager_imp(
    private val client: HttpClient
) : NotificationManager {

    override suspend fun getNotificationByCustomerCode(code: String) : GetNotificationsByCustomerCodeResponse {
        return try {
            val response = client.post(BASE_URL + GET_NOTIFICATIONS_BY_CUSTOMER_CODE) {
                contentType(ContentType.Application.Json)
                setBody(GetNotificationsByCustomerCodeRequest(code))
            }

            Log.d("get notification by customer code response : " , response.body<String>().toString())
            if ( response.status == HttpStatusCode.OK ) {
                GetNotificationsByCustomerCodeResponse.Success(response.body())
            }else{
                GetNotificationsByCustomerCodeResponse.Failure(response.body())
            }
        }catch (e : Exception){
            GetNotificationsByCustomerCodeResponse.Exception(e)
        }
    }

}