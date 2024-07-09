package com.example.twitterintegration.data.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

@Suppress("UNCHECKED_CAST")
class SharedPrefs (private val context: Context) {
    companion object {
        private const val PREF = "MyAppPref"
        private const val PREF_GENERATED_CODE = "generated_code"
        private const val PREF_TOKEN = "token"
    }

    private val sharedPref: SharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)


    fun saveToken(token: String?){
        put(PREF_TOKEN, token)
    }
    fun getToken() : String? {
        return get(PREF_TOKEN, String::class.java)
    }

    fun saveGeneratedCode(token: String?){
        put(PREF_GENERATED_CODE, token)
    }
    fun getGeneratedCode() : String? {
        return get(PREF_GENERATED_CODE, String::class.java)
    }


    private inline fun <reified T> get(key: String, clazz: Class<T>): T? =
        when (clazz) {
            String::class.java -> sharedPref.getString(key, "") as T
            Boolean::class.java -> sharedPref.getBoolean(key, false) as T
            Float::class.java -> sharedPref.getFloat(key, -1f) as T
            Double::class.java -> sharedPref.getFloat(key, -1f) as T
            Int::class.java -> sharedPref.getInt(key, -1) as T
            Long::class.java -> sharedPref.getLong(key, -1L) as T
            else -> {
                val json = sharedPref.getString(key, null)
                Gson().fromJson(json ,clazz )
            }
        }

    private fun <T> put(key: String, data: T) {
        val editor = sharedPref.edit()
        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Double -> editor.putFloat(key, data.toFloat())
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
        }
        editor.apply()
    }

    fun clear() {
        sharedPref.edit().run {
            remove(PREF_TOKEN)
        }.apply()
    }

}