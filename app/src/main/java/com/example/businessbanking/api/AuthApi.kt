package com.example.businessbanking.api

import com.example.businessbanking.models.LoginRequest
import com.example.businessbanking.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("api/Auth/Login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}