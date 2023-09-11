package com.example.pmordo.data.data.repository

import android.content.Context
import com.example.pmordo.App.Companion.instance
import com.example.pmordo.data.data.models.UserSaveModel
import com.example.pmordo.domain.base.Mapper
import com.example.pmordo.domain.models.UserDomain
import com.example.pmordo.domain.repository.UserCacheRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserCacheRepositoryImpl(
    private val mapUserSaveToDomainModel: Mapper<UserSaveModel, UserDomain>,
    private val mapUserDomainToSave: Mapper<UserDomain, UserSaveModel>
) : UserCacheRepository {

    private companion object {
        const val CURRENT_USER_EDITOR_SAVE_KEY = "CURRENT_EDITOR_USER_SAVE_KEY"
        const val CURRENT_USER_SAVE_KEY = "CURRENT_USER_SAVE_KEY"
    }

    override fun fetchCurrentUserFromCache(): Flow<UserDomain> = flow {
        val pref =
            instance.getSharedPreferences(CURRENT_USER_EDITOR_SAVE_KEY, Context.MODE_PRIVATE)
        val userSaveModel = Gson()
            .fromJson(pref.getString(CURRENT_USER_SAVE_KEY, null), UserSaveModel::class.java)
            ?: UserSaveModel.unknown()
        emit(mapUserSaveToDomainModel.map(userSaveModel))
    }

    override suspend fun saveCurrentUserFromCache(newUser: UserDomain) = instance
        .getSharedPreferences(CURRENT_USER_EDITOR_SAVE_KEY, Context.MODE_PRIVATE)
        .edit()
        .putString(CURRENT_USER_SAVE_KEY, Gson().toJson(mapUserDomainToSave.map(newUser)))
        .commit()

    override suspend fun clear() = instance
        .getSharedPreferences(CURRENT_USER_EDITOR_SAVE_KEY, Context.MODE_PRIVATE)
        .edit().clear().apply()

}