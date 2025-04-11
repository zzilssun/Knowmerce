package com.sample.knowmerce.core.data.repository

import androidx.annotation.IntRange
import com.sample.knowmerce.core.data.api.KakaoAPIService
import com.sample.knowmerce.core.model.dto.SearchImageAndVideoDTO
import com.sample.knowmerce.core.model.kakao.KakaoSearchResponseDTO
import com.sample.knowmerce.core.model.response.SearchImageAndVideoResponse
import javax.inject.Inject

class KakaoRepository @Inject constructor(
    private val service: KakaoAPIService,
) {
    suspend fun searchImageAndVideo(
        query: String,
        @IntRange(from = 1) page: Int = 1,
        @IntRange(from = 1, to = 30) size: Int,
    ): SearchImageAndVideoResponse {
        val images = requestSearchImages(
            query = query,
            page = page,
            size = size,
        )

        val videos = requestSearchVideos(
            query = query,
            page = page,
            size = size,
        )

        return SearchImageAndVideoResponse(
            isEnd = images.meta.isEnd && videos.meta.isEnd,
            documents = (images.documents + videos.documents).sortedByDescending {
                it.getDateTime()
            },
        )
    }

    /**
     * 카카오 이미지 검색
     * https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-image
     *
     * @param query 검색을 원하는 질의어
     * @param page 결과 페이지 번호, 1~50 사이의 값, 기본 값 1
     * @param size 한 페이지에 보여질 문서의 개수, 1~80 사이의 값, 기본 값 10
     */
    private suspend fun requestSearchImages(
        query: String,
        @IntRange(from = 1) page: Int = 1,
        @IntRange(from = 1, to = 80) size: Int,
    ): KakaoSearchResponseDTO<SearchImageAndVideoDTO.Image> {
        return service.requestSearchImages(
            query = query,
            page = page,
            size = size,
        )
    }

    /**
     * 카카오 동영상 검색
     * https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-video
     *
     * @param query 검색을 원하는 질의어
     * @param page 결과 페이지 번호, 1~15 사이의 값
     * @param size 한 페이지에 보여질 문서 수, 1~30 사이의 값, 기본 값 15
     */
    private suspend fun requestSearchVideos(
        query: String,
        @IntRange(from = 1) page: Int = 1,
        @IntRange(from = 1, to = 30) size: Int,
    ): KakaoSearchResponseDTO<SearchImageAndVideoDTO.Video> {
        return service.requestSearchVideos(
            query = query,
            page = page,
            size = size,
        )
    }
}