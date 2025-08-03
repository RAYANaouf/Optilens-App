package com.example.optilens.domain.manager

import com.example.optilens.data.db.entities.Account
import com.example.optilens.data.db.entities.Customer
import kotlinx.coroutines.flow.Flow


interface LocalUserManager {

    suspend fun saveAccount(account : Customer)
    suspend fun readAccount() : Flow<Customer?>


}