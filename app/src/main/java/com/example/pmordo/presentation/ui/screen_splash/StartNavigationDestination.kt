package com.example.pmordo.presentation.ui.screen_splash

sealed class StartNavigationDestination {

    object NavigateToLoginScreen : StartNavigationDestination()

    object NavigateToMainScreen : StartNavigationDestination()

    object NavigateToSalesmanScreen : StartNavigationDestination()

    object NavigateToAccountHasDeletedScreen : StartNavigationDestination()
}