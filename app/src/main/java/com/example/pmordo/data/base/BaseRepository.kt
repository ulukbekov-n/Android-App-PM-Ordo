package com.example.pmordo.data.base

import com.example.pmordo.domain.base.RequestState


interface BaseRepository {

    suspend fun <T> renderResult(
        result: DataRequestState<T>,
        onSuccess: (suspend (T) -> Unit?)? = null,
        onError: ((Throwable) -> Unit?)? = null,
    ) = when (result) {
        is DataRequestState.Success -> {
            onSuccess?.invoke(result.data)
            RequestState.Success(result.data)
        }

        is DataRequestState.Error -> {
            onError?.invoke(result.error)
            RequestState.Error(result.error)
        }
    }
}