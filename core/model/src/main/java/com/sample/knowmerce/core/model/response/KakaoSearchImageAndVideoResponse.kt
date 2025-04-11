package com.sample.knowmerce.core.model.response

import com.sample.knowmerce.core.model.dto.kakao.KakaoSearchImageAndVideoDTO

data class KakaoSearchImageAndVideoResponse(
    val isEnd: Boolean,
    val documents: List<KakaoSearchImageAndVideoDTO>
)