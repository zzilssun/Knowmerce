package com.sample.knowmerce.core.model.kakao

import com.sample.knowmerce.core.model.dto.kakao.KakaoSearchMetaDTO

data class KakaoSearchResponse<T>(
    val meta: KakaoSearchMetaDTO,
    val documents: List<T> = listOf(),
)