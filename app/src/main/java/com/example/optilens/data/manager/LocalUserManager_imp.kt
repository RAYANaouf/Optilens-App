package com.example.optilens.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.optilens.data.db.entities.Account
import com.example.optilens.data.db.entities.Customer
import com.example.optilens.domain.manager.LocalUserManager
import com.example.optilens.unit.objects.Constants
import com.example.optilens.unit.objects.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManager_imp(
    private val context : Context
) : LocalUserManager {
    override suspend fun saveAccount(customer: Customer) {
        context.dataStore.edit { settings ->
            settings[PrefrencesKeys.CLIENT] = customer.name
            settings[PrefrencesKeys.CLIENT_CODE] = customer.custom_customer_code ?: ""
            settings[PrefrencesKeys.CLIENT_DEBT] = customer.custom_debt ?: ""
        }
    }

    override suspend fun readAccount(): Flow<Customer?> {


        return context.dataStore.data.map { preferences ->

            if (preferences[PrefrencesKeys.CLIENT] == null || preferences[PrefrencesKeys.CLIENT] == ""){
                null
            }else{
                val account = Customer(
                    name                   = preferences[PrefrencesKeys.CLIENT]       ?: "",
                    custom_customer_code   = preferences[PrefrencesKeys.CLIENT_CODE]  ?: "" ,
                    custom_debt            = preferences[PrefrencesKeys.CLIENT_DEBT]  ?: "" ,
                )
                account
            }


        }


    }

    override suspend fun deleteAccount() {
        context.dataStore.edit { settings ->
            settings[PrefrencesKeys.CLIENT] = ""
            settings[PrefrencesKeys.CLIENT_CODE] = ""
            settings[PrefrencesKeys.CLIENT_DEBT] = ""
        }

    }


}

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)


private object PrefrencesKeys{
    val CLIENT       = stringPreferencesKey(name = Constants.CLIENT)
    val CLIENT_CODE  = stringPreferencesKey(name = Constants.CLIENT_CODE)
    val CLIENT_DEBT  = stringPreferencesKey(name = Constants.CLIENT_DEBT)
    }