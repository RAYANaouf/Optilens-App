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
import com.example.optilens.domain.manager.LocalUserManager
import com.example.optilens.unit.objects.Constants
import com.example.optilens.unit.objects.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManager_imp(
    private val context : Context
) : LocalUserManager {
    override suspend fun saveAccount(account: Account) {
        context.dataStore.edit { settings ->
            settings[PrefrencesKeys.CLIENT] = account.client
            settings[PrefrencesKeys.CLIENT_CODE] = account.clientCode
        }
    }

    override suspend fun readAccount(): Flow<Account?> {


        return context.dataStore.data.map { preferences ->

            if (preferences[PrefrencesKeys.CLIENT] == null || preferences[PrefrencesKeys.CLIENT] == ""){
                null
            }else{
                val account = Account(
                    client         = preferences[PrefrencesKeys.CLIENT]       ?: "",
                    clientCode     = preferences[PrefrencesKeys.CLIENT_CODE]  ?: "" ,
                )
                account
            }


        }


    }


}

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)


private object PrefrencesKeys{
    val CLIENT       = stringPreferencesKey(name = Constants.CLIENT)
    val CLIENT_CODE  = stringPreferencesKey(name = Constants.CLIENT_CODE)
    }