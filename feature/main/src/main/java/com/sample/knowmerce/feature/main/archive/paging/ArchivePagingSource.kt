package com.sample.knowmerce.feature.main.archive.paging

import com.google.gson.Gson
import com.sample.knowmerce.core.data.repository.KnowMerceDatabaseRepository
import com.sample.knowmerce.core.ui.paging.BasePagingSource
import com.sample.knowmerce.feature.main.card.models.SearchViewData

internal class ArchivePagingSource(
    private val repository: KnowMerceDatabaseRepository,
) : BasePagingSource<SearchViewData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchViewData> {
        return handlePageLoadMore(
            params = params,
            responseFactory = { page, loadSize ->
                repository.requestArchives(
                    page = page,
                    size = loadSize,
                )
            },
            dataFactory = { page, response ->
                LoadMoreData(
                    loadMore = response.size == params.loadSize,
                    items = response.mapNotNull { content ->
                        when (content.type) {
                            "image" -> {
                                Gson().fromJson(content.content, SearchViewData.Image::class.java)
                            }

                            "video" -> {
                                Gson().fromJson(content.content, SearchViewData.Video::class.java)
                            }

                            else -> {
                                null
                            }
                        }
                    },
                )
            },
        )
    }
}