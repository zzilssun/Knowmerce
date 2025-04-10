package com.sample.knowmerce.core.model.dto

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

/**
 * @param collection 컬렉션
 * @param datetime 문서 작성시간, ISO 8601 [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
 * @param displaySiteName 출처
 * @param docUrl 문서 URL
 * @param height 이미지의 세로 크기
 * @param width 이미지의 가로 크기
 * @param imageURL 이미지의 URL
 * @param thumbnailURL 이미지 썸네일 URL
 */
data class SearchImageDTO(
    val collection: String,
    val datetime: LocalDateTime?,
    @SerializedName("display_sitename")
    val displaySiteName: String,
    @SerializedName("doc_url")
    val docUrl: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    val width: Int,
    val height: Int,
)