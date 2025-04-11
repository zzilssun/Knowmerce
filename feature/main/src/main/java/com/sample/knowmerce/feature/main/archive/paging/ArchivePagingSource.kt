package com.sample.knowmerce.feature.main.archive.paging

import com.google.gson.Gson
import com.sample.knowmerce.core.data.repository.KnowMerceDatabaseRepository
import com.sample.knowmerce.core.ui.paging.BasePagingSource
import com.sample.knowmerce.feature.main.card.models.KakoSearchViewData

internal class ArchivePagingSource(
    private val repository: KnowMerceDatabaseRepository,
) : BasePagingSource<KakoSearchViewData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, KakoSearchViewData> {
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
                                Gson().fromJson(content.content, KakoSearchViewData.Image::class.java)
                            }

                            "video" -> {
                                Gson().fromJson(content.content, KakoSearchViewData.Video::class.java)
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