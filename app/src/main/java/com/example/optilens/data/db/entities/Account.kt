package com.example.optilens.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Account(
    @PrimaryKey()
    val client        : String  = "",
    val clientCode    : String  = "",
)
