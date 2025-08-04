package com.example.optilens.data.manager

import android.util.Log
import com.example.optilens.data.api.req_res_classes.AuthModule.LogIn.LogInResponse
import com.example.optilens.data.api.req_res_classes.InvoicesModules.GetInvoicesByCustomerCode.GetInvoicesByCustomerCodeRequest
import com.example.optilens.data.api.req_res_classes.InvoicesModules.GetInvoicesByCustomerCode.GetInvoicesByCustomerCodeResponse
import com.example.optilens.domain.manager.InvoiceManager
import com.example.optilens.unit.objects.Constants.BASE_URL
import com.example.optilens.unit.objects.Constants.GET_INVOICES_BY_CUSTOMER_CODE
import com.example.optilens.unit.objects.Constants.LOG_IN
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

class InvoiceManager_imp(
    private val client : HttpClient
) : InvoiceManager {

    override suspend fun GetAllInvoiceByCustomerCode(code: String): GetInvoicesByCustomerCodeResponse {
        return try {
            val response = client.post(BASE_URL + GET_INVOICES_BY_CUSTOMER_CODE) {
                contentType(ContentType.Application.Json)
                setBody(GetInvoicesByCustomerCodeRequest(code))
            }

            Log.d("get invoices " , response.body<String>().toString())
            if ( response.status == HttpStatusCode.OK ) {
                GetInvoicesByCustomerCodeResponse.Success(response.body())
            }else{
                GetInvoicesByCustomerCodeResponse.Failure(response.body())
            }
        }catch (e : Exception){
            GetInvoicesByCustomerCodeResponse.Exception(e)
        }
    }

}