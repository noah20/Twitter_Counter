package com.example.theming.binding.model

import androidx.annotation.DrawableRes
import com.example.theming.R


data class ToolBarConfig(
    val title: String,
    @DrawableRes val backIcon: Int = R.drawable.ic_back,
    val backAction: () -> Unit
){
    fun handleBackAction() {
        backAction.invoke()
    }
}
