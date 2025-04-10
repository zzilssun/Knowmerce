package com.sample.knowmerce.core.data.repository

import androidx.annotation.IntRange
import com.sample.knowmerce.core.data.api.KakaoAPIService
import com.sample.knowmerce.core.model.response.SearchVideoResponse
import javax.inject.Inject

class KakaoRepository @Inject constructor(
    private val service: KakaoAPIService,
) {
    /**
     * 카카오 이미지 검색
     * https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-image
     *
     * @param query 검색을 원하는 질의어
     * @param page 결과 페이지 번호, 1~50 사이의 값, 기본 값 1
     */
    suspend fun requestSearchImages(
        query: String,
        @IntRange(from = 1) page: Int = 1,
    ): SearchVideoResponse {
        return service.requestSearchImages(
            query = query,
            page = page,
        )
    }

    /**
     * 카카오 동영상 검색
     * https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-video
     *
     * @param query 검색을 원하는 질의어
     * @param page 결과 페이지 번호, 1~15 사이의 값
     */
    suspend fun requestSearchVideos(
        query: String,
        @IntRange(from = 1) page: Int = 1,
    ): SearchVideoResponse {
        return service.requestSearchVideos(
            query = query,
            page = page,
        )
    }
}