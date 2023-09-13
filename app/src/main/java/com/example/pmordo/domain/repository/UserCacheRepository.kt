package com.example.pmordo.domain.repository

import com.example.pmordo.domain.models.UserDomain
import kotlinx.coroutines.flow.Flow

interface UserCacheRepository {

    fun fetchCurrentUserFromCache(): Flow<UserDomain>

    suspend fun saveCurrentUserFromCache(newUser: UserDomain): Boolean

    suspend fun clear()


}