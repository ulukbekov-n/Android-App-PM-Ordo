package com.example.pmordo.presentation.utils.pref

import android.app.Activity
import android.content.Context
import com.example.pmordo.presentation.models.User
import com.google.gson.Gson

const val CURRENT_USER_EDITOR_SAVE_KEY = "CURRENT_EDITOR_USER_SAVE_KEY"
const val CURRENT_USER_SAVE_KEY = "CURRENT_USER_SAVE_KEY"
class SharedPreferences {

    fun saveCurrentUser(user: User, activity: Activity) {
        activity.getSharedPreferences(CURRENT_USER_EDITOR_SAVE_KEY, Context.MODE_PRIVATE)
            .edit().putString(CURRENT_USER_SAVE_KEY, Gson().toJson(user)).apply()
    }

    fun getCurrentUser(activity: Context): User? {
        val pref =
            activity.getSharedPreferences(CURRENT_USER_EDITOR_SAVE_KEY, Context.MODE_PRIVATE)
        return Gson().fromJson(pref.getString(CURRENT_USER_SAVE_KEY, null), User::class.java)
    }

    fun clearCurrentUser(activity: Activity) =
        activity.getSharedPreferences(CURRENT_USER_EDITOR_SAVE_KEY, Context.MODE_PRIVATE).edit()
            .clear().commit()


}