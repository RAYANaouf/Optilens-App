package com.example.optilens.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Customer(
    @PrimaryKey()
    val name        : String   = "",
    val email_id    : String?  = "",
    val mobile_no   : String?  = "",
    val customer_debt          : String?  = "",
    val custom_debt_date       : String?  = "",
    val custom_customer_code   : String?  = "",
)
