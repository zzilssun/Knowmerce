package com.sample.knowmerce.core.model.dto.kakao

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

sealed class KakaoSearchImageAndVideoDTO {
    abstract fun getDateTime(): LocalDateTime?

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
    data class Image(
        val collection: String = "",
        val datetime: LocalDateTime? = null,
        @SerializedName("display_sitename")
        val displaySiteName: String = "",
        @SerializedName("doc_url")
        val docUrl: String = "",
        @SerializedName("image_url")
        val imageUrl: String = "",
        @SerializedName("thumbnail_url")
        val thumbnailUrl: String = "",
        val width: Int = 0,
        val height: Int = 0,
    ) : KakaoSearchImageAndVideoDTO() {
        override fun getDateTime(): LocalDateTime? {
            return datetime
        }
    }

    /**
     * @param author 동영상 업로더
     * @param datetime 동영상 등록일, ISO 8601 [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
     * @param playTime 동영상 재생시간, 초 단위
     * @param thumbnail 동영상 썸네일 URL
     * @param title 동영상 제목
     * @param url 동영상 링크
     */
    data class Video(
        val author: String = "",
        val datetime: LocalDateTime? = null,
        @SerializedName("play_time")
        val playTime: Int = 0,
        val thumbnail: String = "",
        val title: String = "",
        val url: String = "",
    ) : KakaoSearchImageAndVideoDTO() {
        override fun getDateTime(): LocalDateTime? {
            return datetime
        }
    }
}