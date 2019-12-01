package com.droid.cleanarchitecture.utils.extensions

import android.graphics.Paint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.droid.cleanarchitecture.R
import com.squareup.picasso.Picasso

@BindingAdapter("wasPrice")
fun setWasPrice(view: TextView, text: String) {
    view.text = text
    view.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
}

@BindingAdapter("savePrice")
fun setSavePrice(view: TextView, text: String) {
    view.text = "Save " + text
}