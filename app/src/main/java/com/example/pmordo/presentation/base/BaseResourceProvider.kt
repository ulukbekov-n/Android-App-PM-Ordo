package com.example.pmordo.presentation.base

import android.content.Context
import androidx.annotation.StringRes
import com.example.pmordo.App.Companion.instance
import com.example.pmordo.presentation.utils.IdResourceString
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.net.UnknownServiceException

interface BaseResourceProvider {

    fun getString(@StringRes id: Int): String

    fun fetchErrorMessage(exception: Exception): String

    fun fetchErrorMessage(exception: Throwable): String

    fun fetchIdErrorMessage(exception: Throwable): IdResourceString

    class Base : BaseResourceProvider {

        override fun getString(id: Int) = instance.getString(id)

        override fun fetchErrorMessage(exception: Exception): String {
            return when (exception) {
                is UnknownHostException -> getString(com.example.pmordo.R.string.network_error)
                is SocketTimeoutException -> getString(com.example.pmordo.R.string.network_error)
                is ConnectException -> getString(com.example.pmordo.R.string.network_error)
                else -> getString(com.example.pmordo.R.string.generic_error)
            }
        }

        override fun fetchErrorMessage(exception: Throwable): String {
            return when (exception) {
                is UnknownHostException -> getString(com.example.pmordo.R.string.network_error)
                is SocketTimeoutException -> getString(com.example.pmordo.R.string.network_error)
                is ConnectException -> getString(com.example.pmordo.R.string.network_error)
                else -> getString(com.example.pmordo.R.string.generic_error)
            }
        }

        override fun fetchIdErrorMessage(exception: Throwable): IdResourceString =
            when (exception) {
                is UnknownHostException -> IdResourceString(com.example.pmordo.R.string.network_error)
                is SocketTimeoutException -> IdResourceString(com.example.pmordo.R.string.network_error)
                is ConnectException -> IdResourceString(com.example.pmordo.R.string.network_error)
                else -> IdResourceString(com.example.pmordo.R.string.generic_error)
            }
    }
}