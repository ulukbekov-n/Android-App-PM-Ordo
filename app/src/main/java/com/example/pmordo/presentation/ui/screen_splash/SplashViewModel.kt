package com.example.pmordo.presentation.ui.screen_splash

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.pmordo.domain.base.Mapper
import com.example.pmordo.domain.models.UserDomain
import com.example.pmordo.domain.repository.UserCacheRepository
import com.example.pmordo.presentation.base.BaseViewModel
import com.example.pmordo.presentation.models.User
import com.example.pmordo.presentation.models.UserType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.withTimeout

class SplashViewModel(
    private val mapper: Mapper<UserDomain, User>,
    userCacheRepository: UserCacheRepository,
) : BaseViewModel() {


    private val _isProgressBarVisibleFlow = createMutableSharedFlowAsSingleLiveEvent<Boolean>()
    val isProgressBarVisibleFlow get() = _isProgressBarVisibleFlow.asSharedFlow()


    private val currentUserFromCacheFlow = userCacheRepository.fetchCurrentUserFromCache()
        .flowOn(Dispatchers.IO)
        .map(mapper::map)
        .filterNotNull()
        .flowOn(Dispatchers.Default)
        .onEach(::handleCurrentUserFromCache)
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    init {
        currentUserFromCacheFlow.launchIn(viewModelScope)
    }

    private val _navigateToFlow =
        createMutableSharedFlowAsSingleLiveEvent<StartNavigationDestination>()
    val navigateToFlow get() = _navigateToFlow.asSharedFlow()


    private fun handleCurrentUserFromCache(user: User) = launchInBackground {
        withTimeout(SPLASH_SCREEN_DEFAULT_DELAY_TIME) {
            delay(1000)
            _navigateToFlow.tryEmit(searchNavigateInUserType(user.userType))
            Log.i("Umar", "_navigateToFlow.tryEmit( ${user.userType} )")

        }
    }

    private fun searchNavigateInUserType(userType: UserType) =
        navigateActions[userType] ?: StartNavigationDestination.NavigateToMainScreen

    private val navigateActions: Map<UserType, StartNavigationDestination> by lazy {
        mapOf(
            UserType.unknown to StartNavigationDestination.NavigateToLoginScreen,
            UserType.salesman to StartNavigationDestination.NavigateToSalesmanScreen,
            UserType.buyer to StartNavigationDestination.NavigateToMainScreen,
        )
    }

    private companion object {
        const val SPLASH_SCREEN_DEFAULT_DELAY_TIME = 3000L
    }
}