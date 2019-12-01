package com.droid.cleanarchitecture.utils.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.droid.cleanarchitecture.R
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String) {
    Picasso.get().load(imageUrl).placeholder(R.mipmap.placeholder)
        .into(view)
}