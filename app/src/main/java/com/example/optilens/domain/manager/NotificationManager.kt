package com.example.optilens.domain.manager

import com.example.optilens.data.api.req_res_classes.InvoicesModules.GetInvoicesByCustomerCode.GetNotificationsByCustomerCodeResponse
import com.example.optilens.data.db.entities.Notification

interface NotificationManager{

    suspend fun getNotificationByCustomerCode(code : String) : GetNotificationsByCustomerCodeResponse

}