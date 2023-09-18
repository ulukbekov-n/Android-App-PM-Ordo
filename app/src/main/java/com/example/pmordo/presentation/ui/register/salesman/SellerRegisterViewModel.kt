package com.example.pmordo.presentation.ui.register.seller

import androidx.lifecycle.viewModelScope
import com.example.pmordo.domain.base.DispatchersProvider
import com.example.pmordo.domain.base.Mapper
import com.example.pmordo.domain.models.SellerSignUpDomain
import com.example.pmordo.domain.repository.LoginRepository
import com.example.pmordo.domain.repository.UserCacheRepository
import com.example.pmordo.presentation.base.BaseResourceProvider
import com.example.pmordo.presentation.base.BaseViewModel
import com.example.pmordo.presentation.models.SellerSignUp
import com.example.pmordo.presentation.models.SellerSignUpDomainModel
import com.example.pmordo.presentation.utils.dispatchers.launchSafe

import kotlinx.coroutines.flow.asSharedFlow

class SellerRegisterViewModel(
    private val resourceProvider: BaseResourceProvider,
    private val repository: LoginRepository,
    private val userCacheRepository: UserCacheRepository,
    private val dispatchersProvider: DispatchersProvider,
    private val mapSellerSignUpToDomain: Mapper<SellerSignUp, SellerSignUpDomain>,
) : BaseViewModel() {

    private val _isProgressBarVisibleFlow = createMutableSharedFlowAsSingleLiveEvent<Boolean>()
    val isProgressBarVisibleFlow get() = _isProgressBarVisibleFlow.asSharedFlow()

    private val _isProgressDialogVisibleFlow = createMutableSharedFlowAsSingleLiveEvent<Boolean>()
    val isProgressDialogVisibleFlow get() = _isProgressDialogVisibleFlow.asSharedFlow()

    private val _isErrorMessageVisibleFlow = createMutableSharedFlowAsSingleLiveEvent<Boolean>()
    val isErrorMessageVisibleFlow get() = _isErrorMessageVisibleFlow.asSharedFlow()

    private val _handleSignUpFlow = createMutableSharedFlowAsSingleLiveEvent<Unit>()
    val handleSignUpFlow get() = _handleSignUpFlow.asSharedFlow()

    fun startSignUp(seller: SellerSignUp) {
        emitIsErrorMessageVisibleFlow(isVisible = false)
        signUpSeller(seller)
    }

    private fun signUpSeller(seller: SellerSignUp) {
        emitIsProgressDialogVisibleFlow(isVisible = true)
        viewModelScope.launchSafe(
            dispatcher = dispatchersProvider.io(),
            safeAction = { repository.signUpSeller(seller = mapSellerSignUpToDomain.map(seller)) },
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
