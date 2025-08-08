package com.example.optilens.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Serializable
@Entity
data class Notification(
    @PrimaryKey()
    val name               : String   ,
    val title              : String? = "" ,
    val msg                : String  = ""   ,
    val read               : Boolean = false ,
)