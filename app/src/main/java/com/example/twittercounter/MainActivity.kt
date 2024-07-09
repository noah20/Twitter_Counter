package com.example.twittercounter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.twittercounter.databinding.ActivityMainBinding
import com.example.twitterintegration.ui.PostTweetFragment
import com.example.twitterintegration.utils.LocaleUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var mBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        LocaleUtils.setLocale(this, "en")
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding?.root)

    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }



    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d("TAG", "onNewIntent: noah here ->")
    }
}