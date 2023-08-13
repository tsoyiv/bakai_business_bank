package com.example.businessbanking.presenter

interface LoginContract {
    interface View {
        fun onLoginSuccess(token: String)
        fun onLoginError(error: String)
    }

    interface Presenter {
        fun performLogin(login: String, password: String)
    }
}