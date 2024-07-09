package com.example.twittercounter.utils.binding.binding_adpter

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter


@BindingAdapter("app:imageResource")
fun setImageResource(image: ImageView, @DrawableRes icon: Int) {
    image.setImageResource(icon)
}
