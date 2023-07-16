package com.hoest.utils

import android.content.Context

class UserAuth(val context: Context) {

    fun saveUserInfo(token: String, name: String): Boolean {
        val sharedPref = context.getSharedPreferences("userAuth", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("token", token)
        editor.putString("name", name)
        return editor.commit()
    }

    fun getToken(): String? {
        val sharedPref = context.getSharedPreferences("userAuth", Context.MODE_PRIVATE)
        return sharedPref.getString("token", null)
    }

    fun getName(): String? {
        val sharedPref = context.getSharedPreferences("userAuth", Context.MODE_PRIVATE)
        return sharedPref.getString("name", null)
    }

    fun clearUserInfo(): Boolean {
        val sharedPref = context.getSharedPreferences("userAuth", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.clear()
        return editor.commit()
    }

}