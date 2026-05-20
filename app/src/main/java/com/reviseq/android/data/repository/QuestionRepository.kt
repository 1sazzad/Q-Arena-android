package com.reviseq.android.data.repository

import com.reviseq.android.core.network.RetrofitClient
import com.reviseq.android.core.session.SessionManager
import com.reviseq.android.data.remote.api.QuestionApi
import com.reviseq.android.data.remote.dto.QuestionResponse

class QuestionRepository {

    private val questionApi: QuestionApi = RetrofitClient.retrofit.create(QuestionApi::class.java)

    suspend fun getQuestions(
        subjectCode: String,
        page: Int = 1,
        limit: Int = 20
    ): Result<List<QuestionResponse>> {
        val token = SessionManager.accessToken
        val trimmedSubjectCode = subjectCode.trim()

        if (token.isNullOrBlank()) {
            return Result.failure(Exception("Not logged in"))
        }

        if (trimmedSubjectCode.isBlank()) {
            return Result.failure(Exception("Subject code is required"))
        }

        return try {
            val response = questionApi.getQuestions(
                authorization = "Bearer $token",
                subjectCode = trimmedSubjectCode,
                page = page,
                limit = limit
            )
            Result.success(response.questions ?: emptyList())
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
