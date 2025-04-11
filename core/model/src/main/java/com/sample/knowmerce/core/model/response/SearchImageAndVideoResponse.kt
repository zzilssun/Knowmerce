package com.sample.knowmerce.core.model.response

import com.sample.knowmerce.core.model.dto.SearchImageAndVideoDTO

data class SearchImageAndVideoResponse(
    val isEnd: Boolean,
    val documents: List<SearchImageAndVideoDTO>
)