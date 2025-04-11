package com.sample.knowmerce.feature.main.search.paging

import com.sample.knowmerce.core.data.repository.KakaoRepository
import com.sample.knowmerce.core.model.dto.kakao.KakaoSearchImageAndVideoDTO
import com.sample.knowmerce.core.ui.paging.BasePagingSource
import com.sample.knowmerce.feature.main.card.models.KakoSearchViewData

internal class SearchImageAndVideoPagingSource(
    private val kakaoRepository: KakaoRepository,
    private val keyword: String,
) : BasePagingSource<KakoSearchViewData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, KakoSearchViewData> {
        return handlePageLoadMore(
            params = params,
            responseFactory = { page, loadSize ->
                kakaoRepository.searchImageAndVideo(
                    query = keyword,
                    page = page,
                    size = loadSize,
                )
            },
            dataFactory = { page, response ->
                LoadMoreData(
                    loadMore = !response.isEnd,
                    items = response.documents.map { content ->
                        when (content) {
                            is KakaoSearchImageAndVideoDTO.Image -> {
                                KakoSearchViewData.Image(
                                    thumbnail = content.thumbnailUrl,
                                    collection = content.collection,
                                    siteName = content.displaySiteName,
                                    link = content.docUrl,
                                )
                            }

                            is KakaoSearchImageAndVideoDTO.Video -> {
                                KakoSearchViewData.Video(
                                    thumbnail = content.thumbnail,
                                    author = content.author,
                                    title = content.title,
                                    link = content.url,
                                )
                            }
                        }
                    },
                )
            },
        )
    }
}