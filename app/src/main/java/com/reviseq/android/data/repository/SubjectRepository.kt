package com.reviseq.android.data.repository

import com.reviseq.android.core.network.RetrofitClient
import com.reviseq.android.core.session.SessionManager
import com.reviseq.android.data.remote.api.SubjectApi
import com.reviseq.android.data.remote.dto.SubjectResponse

class SubjectRepository {

    private val subjectApi: SubjectApi = RetrofitClient.retrofit.create(SubjectApi::class.java)

    suspend fun getSubjects(): Result<List<SubjectResponse>> {
        val token = SessionManager.accessToken

        if (token.isNullOrBlank()) {
            return Result.failure(Exception("Not logged in"))
        }

        return try {
            val response = subjectApi.getSubjects(
                authorization = "Bearer $token"
            )
            Result.success(response.subjects)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
