package com.sample.knowmerce.core.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class SearchImageDTO(
    val collection: String,
    val datetime: LocalDateTime?,
    @SerializedName("display_sitename")
    val displaySiteName: String,
    @SerializedName("doc_url")
    val docUrl: String,
    val height: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    val width: Int
)