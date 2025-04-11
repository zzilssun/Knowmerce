package com.sample.knowmerce.core.model.kakao

import com.sample.knowmerce.core.model.dto.MetaDTO

data class KakaoSearchResponseDTO<T>(
    val meta: MetaDTO,
    val documents: List<T> = listOf(),
)