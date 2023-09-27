package com.example.businessbanking.models

import java.util.*

data class User(
    val id: Int,
    val customerID: Int,
    val login: String,
    val password: String,
    val userAccess: Int,
    val customerAccounts: List<String>
)

data class Currency(
    val id: Int,
    val currencyID: String,
    val currencySymbol: String,
    val currencyName: String,
    val customerAccounts: List<String>
)

data class Account(
    val id: Int,
    val customerID: Int,
    val accountType: Int,
    val accountNo: String,
    val currencyID: String,
    val accountName: String,
    val availableBalance: Int,
    val openDate: Date?,
    val endDate: Date?,
    val closeDate: Date?,
    val user: User,
    val currency: Currency
)
