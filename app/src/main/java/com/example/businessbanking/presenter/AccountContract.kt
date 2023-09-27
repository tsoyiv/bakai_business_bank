package com.example.businessbanking.presenter

import com.example.businessbanking.models.Account

interface AccountContract {
    interface View {
        fun showAccounts(accounts: List<Account>)
        fun showError(message: String)
    }

    interface Presenter {
        suspend fun fetchAccounts(token: String)
    }
}
