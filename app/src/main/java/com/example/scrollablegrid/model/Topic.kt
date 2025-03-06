package com.example.scrollablegrid.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val titleResourceId: Int,
    val courseCount: Int,
    @DrawableRes val imageResourceId: Int
)