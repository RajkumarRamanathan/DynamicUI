package com.rajkumar.dynamic.data

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserManager @Inject constructor(@ApplicationContext context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("dynamic_app_prefs", Context.MODE_PRIVATE)

    fun getUserName(): String? {
        return prefs.getString("user_name", null)
    }

    fun getUserId(): Int {
        return prefs.getInt("user_id", -1)
    }
    
    fun getRole(): String {
        return prefs.getString("user_role", "user") ?: "user"
    }

    fun setUserName(name: String) {
        prefs.edit().putString("user_name", name).apply()
    }
    
    fun saveUser(id: Int, name: String, role: String) {
        prefs.edit()
            .putInt("user_id", id)
            .putString("user_name", name)
            .putString("user_role", role)
            .apply()
    }
}
