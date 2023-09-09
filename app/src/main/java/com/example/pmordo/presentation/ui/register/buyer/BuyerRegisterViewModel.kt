package com.example.pmordo.presentation.ui.register.buyer

import androidx.lifecycle.viewModelScope
import com.example.pmordo.domain.base.DispatchersProvider
import com.example.pmordo.domain.base.Mapper
import com.example.pmordo.domain.models.SignUpResponseDomainModel
import com.example.pmordo.domain.models.UserDomain
import com.example.pmordo.domain.models.UserSignUpDomain
import com.example.pmordo.domain.repository.LoginRepository
import com.example.pmordo.domain.repository.UserCacheRepository
import com.example.pmordo.presentation.base.BaseResourceProvider
import com.example.pmordo.presentation.base.BaseViewModel
import com.example.pmordo.presentation.models.User
import com.example.pmordo.presentation.models.UserSignUp
import com.example.pmordo.presentation.utils.dispatchers.launchSafe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow

class BuyerRegisterViewModel(
    private val resourceProvider: BaseResourceProvider,
    private val repository: LoginRepository,
    private val userCacheRepository: UserCacheRepository,
    private val dispatchersProvider: DispatchersProvider,
    private val mapUserToDomain: Mapper<User, UserDomain>,
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

    private var currentUserFlow = MutableStateFlow(User.unknown())


    fun startSignUp(user: UserSignUp) {
        emitIsErrorMessageVisibleFlow(isVisible = false)
        signUpUser(user)
    }


    fun signUpUser(user: UserSignUp) {
        emitIsProgressDialogVisibleFlow(isVisible = true)
        viewModelScope.launchSafe(
            dispatcher = dispatchersProvider.io(),
            safeAction = { repository.signUp(user = mapUserSignUpToDomain.map(user)) },
            onSuccess = {
                emitIsProgressDialogVisibleFlow(isVisible = false)
                setAndMapToCurrentUser(it, user)
//                startAddingSessionToken()
            },
            onError = {
                handleError(it)
                emitIsProgressDialogVisibleFlow(isVisible = false)
            }
        )
    }
//
//    private fun startAddingSessionToken() {
//        val user = currentUserFlow.value
//        viewModelScope.launchSafe(
//            dispatcher = dispatchersProvider.io(),
//            safeAction = {
////                userRepository.addSessionToken(user.objectId, user.sessionToken)
//            },
//            onError = ::handleError,
//            onSuccess = { handleAddingSessionTokenResult() }
//        )
//    }

    private fun handleAddingSessionTokenResult() {
        saveNewCurrentUserToCache()
        _handleSignUpFlow.tryEmit(Unit)
    }

    private fun saveNewCurrentUserToCache() = launchInBackground {
        userCacheRepository.saveCurrentUserFromCache(mapUserToDomain.map(currentUserFlow.value))
    }

    private fun handleError(exception: Throwable) {
        emitToErrorMessageFlow(resourceProvider.fetchIdErrorMessage(exception))
    }

    private fun setAndMapToCurrentUser(
        requestAnswerDomain: SignUpResponseDomainModel,
        user: UserSignUp
    ) {
        val newUser = user.mapToUser(
            id = requestAnswerDomain.profileId,
//            sessionToken = requestAnswerDomain.sessionToken,
//            image = requestAnswerDomain.image
        )
        currentUserFlow.tryEmit(newUser)
    }

    private fun emitIsProgressDialogVisibleFlow(isVisible: Boolean) {
        _isProgressDialogVisibleFlow.tryEmit(isVisible)
    }

    private fun emitIsErrorMessageVisibleFlow(isVisible: Boolean) {
        _isErrorMessageVisibleFlow.tryEmit(isVisible)
    }


}