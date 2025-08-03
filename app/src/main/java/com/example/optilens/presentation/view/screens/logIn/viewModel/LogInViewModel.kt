package com.example.optilens.presentation.view.screens.logIn.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.optilens.data.api.req_res_classes.AuthModule.LogIn.LogInRequest
import com.example.optilens.data.api.req_res_classes.AuthModule.LogIn.LogInResponse
import com.example.optilens.data.api.req_res_classes.AuthModule.LogIn.LogInSuccessResponse
import com.example.optilens.domain.manager.AuthManager
import com.example.optilens.domain.manager.LocalUserManager
import com.example.optilens.presentation.view.screens.logIn.event.LogInEvent
import kotlinx.coroutines.launch

class LogInViewModel(
    private val localUserManager: LocalUserManager,
    private val authManager: AuthManager,
    private val context    : Context
) : ViewModel() {



    fun onEvent( event : LogInEvent , onSuccess : ()->Unit , onFailed : ()->Unit ){
        when(event){
            is LogInEvent.OnLoginClick ->{
                try {

                    viewModelScope.launch {
                        val result = authManager.logIn(LogInRequest(code = event.code))
                        if (result is LogInResponse.Success){
                            onSuccess()
                            localUserManager.saveAccount(account = result.message.message.customer)
                            Toast.makeText(context , "Success : ${result.message.message.customer}" , Toast.LENGTH_LONG).show()
                        }else{
                            onFailed()
                            Toast.makeText(context , "Failed" , Toast.LENGTH_SHORT).show()
                        }
                    }

                }catch (e : Exception){
                    Toast.makeText(context , "Failed" , Toast.LENGTH_SHORT).show()
                }
            }
            else->{

            }
        }
    }
}