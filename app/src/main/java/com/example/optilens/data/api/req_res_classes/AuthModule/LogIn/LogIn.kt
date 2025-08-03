package com.example.optilens.data.api.req_res_classes.AuthModule.LogIn

import com.example.optilens.data.db.entities.Customer
import kotlinx.serialization.Serializable


//request
@Serializable
data class LogInRequest(
    val code    : String
)



//response

@Serializable
sealed class LogInResponse{
    class Success(val message : LogInSuccessResponse)  : LogInResponse()
    class Failure(val data : LogInFailureResponse)  : LogInResponse()
    class Exception(val data : kotlin.Exception)    : LogInResponse()
}

@Serializable
data class LogInSuccessResponse(
    val message : LogInSuccessMessageResponse
)

@Serializable
data class LogInSuccessMessageResponse(
    val customer  : Customer
)
@Serializable
data class LogInFailureResponse(
    val error      : String = "",
)