package com.sample.knowmerce.core.model.dto

import com.google.gson.annotations.SerializedName

/**
 * @param author 동영상 업로더
 * @param datetime 동영상 등록일, ISO 8601 [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
 * @param playTime 동영상 재생시간, 초 단위
 * @param thumbnail 동영상 썸네일 URL
 * @param title 동영상 제목
 * @param url 동영상 링크
 */
data class SearchVideoDTO(
    val author: String,
    val datetime: String,
    @SerializedName("play_time")
    val playTime: Int,
    val thumbnail: String,
    val title: String,
    val url: String
)