package com.example.twitterintegration.utils

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build

import java.util.Locale


object LocaleUtils {
    fun setLocale(context: Context, languageCode: String?) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources: Resources = context.resources
        val config: Configuration = resources.configuration
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocale(locale)
        } else {
            config.locale = locale
        }
        context.createConfigurationContext(config)
    }
}