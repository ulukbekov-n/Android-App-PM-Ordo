package com.example.pmordo.presentation.ui.register.buyer

import androidx.lifecycle.viewModelScope
import com.example.pmordo.domain.base.DispatchersProvider
import com.example.pmordo.domain.base.Mapper
import com.example.pmordo.domain.models.SignUpResponseDomainModel
import com.example.pmordo.domain.models.UserSignUpDomain
import com.example.pmordo.domain.repository.LoginRepository
import com.example.pmordo.domain.repository.UserCacheRepository
import com.example.pmordo.presentation.base.BaseResourceProvider
import com.example.pmordo.presentation.base.BaseViewModel
import com.example.pmordo.presentation.models.UserSignUp
import com.example.pmordo.presentation.utils.dispatchers.launchSafe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow

class BuyerRegisterViewModel(
    private val resourceProvider: BaseResourceProvider,
    private val repository: LoginRepository,
    private val userCacheRepository: UserCacheRepository,
    private val dispatchersProvider: DispatchersProvider,
    private val mapUserSignUpToDomain: Mapper<UserSignUp, UserSignUpDomain>,
) : BaseViewModel() {

    private val _isProgressBarVisibleFlow = createMutableSharedFlowAsSingleLiveEvent<Boolean>()
    val isProgressBarVisibleFlow get() = _isProgressBarVisibleFlow.asSharedFlow()

    private val _isProgressDialogVisibleFlow = createMutableSharedFlowAsSingleLiveEvent<Boolean>()
    val isProgressDialogVisibleFlow get() = _isProgressDialogVisibleFlow.asSharedFlow()

    private val _isErrorMessageVisibleFlow = createMutableSharedFlowAsSingleLiveEvent<Boolean>()
    val isErrorMessageVisibleFlow get() = _isErrorMessageVisibleFlow.asSharedFlow()

    private val _handleSignUpFlow = createMutableSharedFlowAsSingleLiveEvent<Unit>()
    val handleSignUpFlow get() = _handleSignUpFlow.asSharedFlow()

    fun startSignUp(user: UserSignUp) {
        emitIsErrorMessageVisibleFlow(isVisible = false)
        signUpUser(user)
    }

    private fun signUpUser(user: UserSignUp) {
        emitIsProgressDialogVisibleFlow(isVisible = true)
        viewModelScope.launchSafe(
            dispatcher = dispatchersProvider.io(),
            safeAction = { repository.signUp(user = mapUserSignUpToDomain.map(user)) },
            onSuccess = {
                emitIsProgressDialogVisibleFlow(isVisible = false)
                handleAddingSessionTokenResult()
            },
            onError = {
                handleError(it)
                emitIsProgressDialogVisibleFlow(isVisible = false)
            }
        )
    }

    private fun handleAddingSessionTokenResult() {
        // Handle session token addition result if needed
        _handleSignUpFlow.tryEmit(Unit)
    }

    private fun handleError(exception: Throwable) {
        emitToErrorMessageFlow(resourceProvider.fetchIdErrorMessage(exception))
    }

    private fun emitIsProgressDialogVisibleFlow(isVisible: Boolean) {
        _isProgressDialogVisibleFlow.tryEmit(isVisible)
    }

    private fun emitIsErrorMessageVisibleFlow(isVisible: Boolean) {
        _isErrorMessageVisibleFlow.tryEmit(isVisible)
    }
}
