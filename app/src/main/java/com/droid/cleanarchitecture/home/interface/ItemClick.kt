package com.droid.cleanarchitecture.home.`interface`

import android.os.Bundle

interface ItemClick {
    fun <T> onItemClicked(activity: Class<T>, bundle: Bundle)
}