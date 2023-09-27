package com.example.businessbanking.presenter

import com.example.businessbanking.api.AccountApi
import com.example.businessbanking.api.RetrofitInstance
import com.example.businessbanking.models.Account
import com.example.businessbanking.utils.Holder

class AccountRepository(private val api: AccountApi) {
    suspend fun fetchAccounts(token: String): List<Account>? {

        val token1 = Holder.access_token
        val authHeader = "Bearer $token1"

        val response = api.getAccounts(authHeader)
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }
}

