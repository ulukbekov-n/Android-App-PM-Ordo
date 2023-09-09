package com.example.pmordo.data.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseApiResponse {
    suspend fun <T> safeApiCalll(
        apiCall: suspend () -> Response<T>,
    ): DataRequestState<T> {
        try {
            val response = withContext(Dispatchers.IO) { apiCall() }
            if (response.isSuccessful) {
                val body = withContext(Dispatchers.Default) { response.body() }
                body?.let { return DataRequestState.Success(data = body) }
            }
            return DataRequestState.Error(error = java.lang.IllegalStateException())

        } catch (exception: Throwable) {
            return DataRequestState.Error(exception)
        }
    }
}