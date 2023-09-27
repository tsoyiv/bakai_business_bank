package com.example.businessbanking.presenter

import com.example.businessbanking.utils.Holder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AccountPresenter(
    private val view: AccountContract.View,
    private val accountRepository: AccountRepository
) : AccountContract.Presenter {

    override suspend fun fetchAccounts(token: String) {

        val token1 = Holder.access_token
        val authHeader = "Bearer $token1"

        val accounts = accountRepository.fetchAccounts(authHeader)
        if (accounts != null) {
            view.showAccounts(accounts)
        } else {
            view.showError("Failed to fetch accounts")
        }
    }
}

