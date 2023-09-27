package com.example.businessbanking.presenter

import android.util.Log
import androidx.navigation.NavController
import com.example.businessbanking.R
import com.example.businessbanking.api.RetrofitInstance
import com.example.businessbanking.models.LoginRequest
import com.example.businessbanking.models.LoginResponse
import com.example.businessbanking.utils.Holder
import com.google.firebase.messaging.FirebaseMessaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(
    private val view: LoginContract.View,
    private val navController: NavController,
    private val firebaseMessaging: FirebaseMessaging // Inject FirebaseMessaging
) : LoginContract.Presenter {

    override fun performLogin(login: String, password: String, fcmToken: String) {
        val loginRequest = LoginRequest(login, password, fcmToken)
        val call = RetrofitInstance.apiAuth.login(loginRequest)

        firebaseMessaging.token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                view.onLoginError("Failed to get FCM token")
                return@addOnCompleteListener
            }
            val fcmToken = task.result
            Log.d("FCM Token", "Received FCM Token: $fcmToken")
            navController.navigate(R.id.otpFragment)
        }

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val token = response.body()?.token
                    val loginResponse = response.body()
                    if (token != null) {
                        Holder.access_token = token
                        navController.navigate(R.id.otpFragment)
                    } else {
                        view.onLoginError("Token not received")
                    }
                } else {
                    view.onLoginError("Login failed")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                view.onLoginError("Network error")
            }
        })
    }
}