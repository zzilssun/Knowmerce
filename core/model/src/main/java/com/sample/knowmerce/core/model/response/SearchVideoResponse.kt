package com.sample.knowmerce.core.model.response

import com.sample.knowmerce.core.model.dto.MetaDTO
import com.sample.knowmerce.core.model.dto.SearchVideoDTO

data class SearchVideoResponse(
    val meta: MetaDTO,
    val documents: List<SearchVideoDTO>,
)