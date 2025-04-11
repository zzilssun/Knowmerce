package com.sample.knowmerce.feature.main.search.paging

import com.sample.knowmerce.core.data.repository.KakaoRepository
import com.sample.knowmerce.core.model.dto.SearchImageAndVideoDTO
import com.sample.knowmerce.core.ui.paging.BasePagingSource
import com.sample.knowmerce.feature.main.card.models.SearchViewData

internal class SearchImageAndVideoPagingSource(
    private val kakaoRepository: KakaoRepository,
    private val keyword: String,
) : BasePagingSource<SearchViewData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchViewData> {
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
                            is SearchImageAndVideoDTO.Image -> {
                                SearchViewData.Image(
                                    thumbnail = content.thumbnailUrl,
                                    collection = content.collection,
                                    siteName = content.displaySiteName,
                                    link = content.docUrl,
                                )
                            }

                            is SearchImageAndVideoDTO.Video -> {
                                SearchViewData.Video(
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