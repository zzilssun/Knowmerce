package com.sample.knowmerce.core.model

import com.google.gson.annotations.SerializedName

data class SearchVideoDTO(
    val author: String,
    val datetime: String,
    @SerializedName("play_time")
    val playTime: Int,
    val thumbnail: String,
    val title: String,
    val url: String
)