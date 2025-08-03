package com.example.optilens.domain.manager

import com.example.optilens.data.api.req_res_classes.AuthModule.LogIn.LogInRequest
import com.example.optilens.data.api.req_res_classes.AuthModule.LogIn.LogInResponse

interface AuthManager {

    suspend fun logIn(request : LogInRequest) : LogInResponse


}