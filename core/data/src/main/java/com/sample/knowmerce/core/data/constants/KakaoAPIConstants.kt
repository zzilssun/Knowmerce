package com.sample.knowmerce.core.data.constants

object KakaoAPIConstants {
    // Version
    private const val VERSION_2 = "v2"

    // Path
    private const val PATH_SEARCH = "search"
    private const val PATH_IMAGE = "image"
    private const val PATH_VCLIP = "vclip"

    // API
    private const val API_SEARCH = "$VERSION_2/$PATH_SEARCH"
    const val API_IMAGE = "$API_SEARCH/$PATH_IMAGE"
    const val API_VCLIP = "$API_SEARCH/$PATH_VCLIP"
}