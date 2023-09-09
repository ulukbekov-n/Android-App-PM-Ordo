package com.example.pmordo.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pmordo.presentation.utils.IdResourceString
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseViewModel : ViewModel() {


    private var dispatchers = com.example.pmordo.presentation.utils.dispatchers.Dispatchers.Base()

    private val _isErrorMessageIdFlow = createMutableSharedFlowAsSingleLiveEvent<IdResourceString>()
    val isErrorMessageIdFlow: SharedFlow<IdResourceString> get() = _isErrorMessageIdFlow.asSharedFlow()


    fun emitToErrorMessageFlow(messageId: IdResourceString) =
        _isErrorMessageIdFlow.tryEmit(messageId)


    fun <T> createMutableSharedFlowAsLiveData(): MutableSharedFlow<T> =
        MutableSharedFlow(1, 0, BufferOverflow.DROP_OLDEST)


    fun <T> createMutableSharedFlowAsSingleLiveEvent(): MutableSharedFlow<T> =
        MutableSharedFlow(0, 1, BufferOverflow.DROP_OLDEST)


    fun <T> launchInBackground(backgroundCall: suspend () -> T) =
        dispatchers.launchInBackground(viewModelScope) { backgroundCall() }


}