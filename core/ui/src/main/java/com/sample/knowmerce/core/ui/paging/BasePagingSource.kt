package com.sample.knowmerce.core.ui.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState

/**
 * 기본적인 값을 구현한 PagingSource
 *
 * Waug API 에 맞는 Page / Size 를 기반으로 한다.
 */
abstract class BasePagingSource<R : Any> : PagingSource<Int, R>() {

    override fun getRefreshKey(state: PagingState<Int, R>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    /**
     * LoadParams 으로부터 Page 를 생성
     */
    protected fun LoadParams<Int>.getPage(): Int {
        return key ?: 0
    }

    /**
     * 전달받은 Data 를 기반으로 Page 를 생성 (LoadMore 기반)
     */
    protected suspend fun <T> handlePageLoadMore(
        params: LoadParams<Int>,
        init: (suspend (page: Int) -> Unit)? = null,
        responseFactory: suspend (page: Int, loadSize: Int) -> T,
        dataFactory: (page: Int, response: T) -> LoadMoreData<R>,
    ): LoadResult<Int, R> {
        val page = params.getPage()
        init?.invoke(page)

        val data = runCatching {
            val response = responseFactory(
                page,
                params.loadSize,
            )

            dataFactory(page, response)
        }.getOrElse {
            LoadMoreData(
                loadMore = false,
                items = listOf(),
            )
        }

        val prevKey = if (page > 0) page - 1 else null
        val nextKey = if (data.loadMore) page + 1 else null

        return LoadResult.Page(
            data = data.items,
            prevKey = prevKey,
            nextKey = nextKey,
        )
    }

    protected class LoadMoreData<T>(
        val loadMore: Boolean,
        val items: List<T>,
    )
}