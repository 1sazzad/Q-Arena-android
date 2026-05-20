package com.reviseq.android.data.repository

import com.reviseq.android.core.network.RetrofitClient
import com.reviseq.android.data.remote.api.AuthApi
import com.reviseq.android.data.remote.dto.LoginRequest
import com.reviseq.android.data.remote.dto.LoginResponse
import com.reviseq.android.data.remote.dto.UserResponse

class AuthRepository {

    private val authApi: AuthApi = RetrofitClient.retrofit.create(AuthApi::class.java)

    suspend fun login(email: String, password: String): Result<LoginResponse> {
        return try {
            val request = LoginRequest(
                email = email,
                password = password
            )
            val response = authApi.login(request)
            Result.success(response)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    suspend fun getCurrentUser(token: String): Result<UserResponse> {
        return try {
            val response = authApi.getCurrentUser(
                authorization = "Bearer $token"
            )
            Result.success(response)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
