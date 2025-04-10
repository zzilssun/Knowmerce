package com.sample.knowmerce.core.model.response

import com.sample.knowmerce.core.model.dto.MetaDTO
import com.sample.knowmerce.core.model.dto.SearchImageDTO

data class SearchImageResponse(
    val meta: MetaDTO,
    val documents: List<SearchImageDTO>,
)