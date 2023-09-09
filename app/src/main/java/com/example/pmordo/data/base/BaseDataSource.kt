package com.example.pmordo.data.base

abstract class BaseDataSource {
//
//    protected suspend fun <T> invokeRequest(request: suspend () -> Response<T>): ResultModel<T> {
//        return try {
//            val response = request()
//            if (response.isSuccessful)
//                ResultModel.success(response.body())
//            else ResultModel.error(
//                AnotherError(
//                    response.message(),
//                    response.code()
//                )
//            )
//        } catch (exception: Exception) {
//            ResultModel.error(exception)
//        }
//    }
}