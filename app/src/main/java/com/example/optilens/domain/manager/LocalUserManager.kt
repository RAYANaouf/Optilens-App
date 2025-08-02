package com.example.optilens.domain.manager

import com.example.optilens.data.db.entities.Account
import kotlinx.coroutines.flow.Flow


interface LocalUserManager {

    suspend fun saveAccount(account : Account)
    suspend fun readAccount() : Flow<Account?>


}