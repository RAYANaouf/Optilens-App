package com.example.optilens.domain.manager

import com.example.optilens.data.api.req_res_classes.InvoicesModules.GetInvoicesByCustomerCode.GetInvoicesByCustomerCodeRequest
import com.example.optilens.data.api.req_res_classes.InvoicesModules.GetInvoicesByCustomerCode.GetInvoicesByCustomerCodeResponse

interface InvoiceManager {

    suspend fun GetAllInvoiceByCustomerCode(code : String) : GetInvoicesByCustomerCodeResponse

}