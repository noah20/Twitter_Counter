package com.example.twittercounter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.twittercounter.databinding.ActivityMainBinding
import com.example.twitterintegration.ui.PostTweetFragment
import com.example.twitterintegration.utils.LocaleUtils
import dagger.hilt.android.AndroidEntryPoint

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
}