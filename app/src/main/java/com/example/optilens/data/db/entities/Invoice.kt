package com.example.optilens.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Serializable
@Entity
data class Invoice(
    @PrimaryKey()
    val name               : String   ,
    val posting_date       : String   ,
    val grand_total        : String   ,
    val outstanding_amount : String   ,
    val status             : String
)