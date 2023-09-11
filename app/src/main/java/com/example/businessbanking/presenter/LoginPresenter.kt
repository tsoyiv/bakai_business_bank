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
    private val firebaseMessaging: FirebaseMessaging
) : LoginContract.Presenter {

    override fun performLogin(login: String, password: String) {
        val loginRequest = LoginRequest(login, password)
        val call = RetrofitInstance.apiAuth.login(loginRequest)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val token = response.body()?.token
                    val loginResponse = response.body()
                    if (token != null) {
                        firebaseMessaging.token.addOnCompleteListener { task ->
                            if (!task.isSuccessful) {
                                view.onLoginError("Failed to get FCM token")
                                return@addOnCompleteListener
                            }

                            // Get the FCM token
                            val fcmToken = task.result
                            Holder.access_token = token

                            Log.d("FCM Token", "Received FCM Token: $fcmToken")

                            navController.navigate(R.id.otpFragment)
                        }
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


//class LoginPresenter(
//    private val view: LoginContract.View,
//    private val navController: NavController
//) : LoginContract.Presenter {
//
//    override fun performLogin(login: String, password: String) {
//        val loginRequest = LoginRequest(login, password)
//        val call = RetrofitInstance.apiAuth.login(loginRequest)
//
//        call.enqueue(object : Callback<LoginResponse> {
//            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//                if (response.isSuccessful) {
//                    val token = response.body()?.token
//                    val loginResponse = response.body()
//                    if (token != null) {
//                        navController.navigate(R.id.otpFragment)
//                        Holder.access_token = token
//                    } else {
//                        view.onLoginError("Token not received")
//                    }
//                } else {
//                    view.onLoginError("Login failed")
//                }
//            }
//
//            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                view.onLoginError("Network error")
//            }
//        })
//    }
//}