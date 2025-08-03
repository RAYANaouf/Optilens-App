package com.example.optilens.data.manager

import android.util.Log
import com.example.optilens.data.api.req_res_classes.AuthModule.LogIn.LogInRequest
import com.example.optilens.data.api.req_res_classes.AuthModule.LogIn.LogInResponse
import com.example.optilens.data.api.req_res_classes.AuthModule.LogIn.LogInSuccessMessageResponse
import com.example.optilens.data.db.entities.Customer
import com.example.optilens.domain.manager.AuthManager
import com.example.optilens.unit.objects.Constants.BASE_URL
import com.example.optilens.unit.objects.Constants.LOG_IN
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

class AuthManager_imp(
    private val client : HttpClient
) : AuthManager {


    override suspend fun logIn(request: LogInRequest): LogInResponse {
        return try {
            val response = client.post(BASE_URL + LOG_IN) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }
            if ( response.status == HttpStatusCode.OK ) {
                LogInResponse.Success(response.body())
            }else{
                LogInResponse.Failure(response.body())
            }
        }catch (e : Exception){
            LogInResponse.Exception(e)
        }
    }

}