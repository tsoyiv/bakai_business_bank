package com.example.businessbanking.api

import com.example.businessbanking.models.Account
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface AccountApi {
    @GET("api/Account/GetCustomerAccounts")
    suspend fun getAccounts(@Header("Authorization") token: String): Response<List<Account>>
}
