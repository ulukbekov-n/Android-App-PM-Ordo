package com.example.pmordo.data.base

import android.content.Context
import androidx.annotation.StringRes
import com.example.pmordo.App.Companion.instance
import com.example.pmordo.R
import com.example.pmordo.presentation.utils.IdResourceString
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

interface ResourceProvider {

    fun getString(@StringRes id: Int): String

    fun fetchErrorMessage(exception: Exception): String

    fun fetchErrorMessage(exception: Throwable): String

    fun fetchIdErrorMessage(exception: Throwable): IdResourceString


    class Base : ResourceProvider {

        override fun getString(id: Int) = instance.getString(id)

        override fun fetchErrorMessage(exception: Exception): String {
            return when (exception) {
                is UnknownHostException -> getString(R.string.network_error)
                is SocketTimeoutException -> getString(R.string.network_error)
                is ConnectException -> getString(R.string.network_error)
                is HttpException -> getString(R.string.service_unavailable)
                else -> getString(R.string.generic_error)
            }
        }

        override fun fetchErrorMessage(exception: Throwable): String {
            return when (exception) {
                is UnknownHostException -> getString(R.string.network_error)
                is SocketTimeoutException -> getString(R.string.network_error)
                is ConnectException -> getString(R.string.network_error)
                is HttpException -> getString(R.string.service_unavailable)
                else -> getString(R.string.generic_error)
            }
        }

        override fun fetchIdErrorMessage(exception: Throwable): IdResourceString =
            when (exception) {
                is UnknownHostException -> IdResourceString(R.string.network_error)
                is SocketTimeoutException -> IdResourceString(R.string.network_error)
                is ConnectException -> IdResourceString(R.string.network_error)
                is HttpException -> IdResourceString(R.string.service_unavailable)
                else -> IdResourceString(R.string.generic_error)
            }

    }
}