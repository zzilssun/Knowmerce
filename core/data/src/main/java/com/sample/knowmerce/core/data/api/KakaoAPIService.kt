package com.sample.knowmerce.core.data.api

import androidx.annotation.IntRange
import com.sample.knowmerce.core.data.constants.KakaoAPIConstants
import com.sample.knowmerce.core.data.constants.SearchConstants
import com.sample.knowmerce.core.model.dto.SearchImageAndVideoDTO
import com.sample.knowmerce.core.model.kakao.KakaoSearchResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoAPIService {

    /**
     * 카카오 이미지 검색
     * https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-image
     *
     * @param query 검색을 원하는 질의어
     * @param sort 결과 문서 정렬 방식, accuracy(정확도순) 또는 recency(최신순), 기본 값 accuracy
     * @param page 결과 페이지 번호, 1~50 사이의 값, 기본 값 1
     * @param size 한 페이지에 보여질 문서 수, 1~80 사이의 값, 기본 값 80
     */
    @GET(KakaoAPIConstants.API_IMAGE)
    suspend fun requestSearchImages(
        @Query("query") query: String,
        @Query("sort") sort: String = SearchConstants.SORT_RECENCY,
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("size") @IntRange(from = 1, to = 80) size: Int = 80,
    ): KakaoSearchResponseDTO<SearchImageAndVideoDTO.Image>

    /**
     * 카카오 동영상 검색
     * https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-video
     *
     * @param query 검색을 원하는 질의어
     * @param sort 결과 문서 정렬 방식, accuracy(정확도순) 또는 recency(최신순), 기본 값 accuracy
     * @param page 결과 페이지 번호, 1~15 사이의 값
     * @param size 한 페이지에 보여질 문서 수, 1~30 사이의 값, 기본 값 15
     */
    @GET(KakaoAPIConstants.API_VCLIP)
    suspend fun requestSearchVideos(
        @Query("query") query: String,
        @Query("sort") sort: String = SearchConstants.SORT_RECENCY,
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("size") @IntRange(from = 1, to = 30) size: Int = 15,
    ): KakaoSearchResponseDTO<SearchImageAndVideoDTO.Video>
}