package com.sample.knowmerce.core.ui.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState

/**
 * 비어있는 값을 구현한 PagingSource
 */
class EmptyPagingSource<Key : Any, Value : Any> : PagingSource<Key, Value>() {
    override fun getRefreshKey(state: PagingState<Key, Value>): Key? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey
                ?: state.closestPageToPosition(it)?.nextKey
        }
    }

    override suspend fun load(params: LoadParams<Key>): LoadResult<Key, Value> {
        return LoadResult.Page(
            data = listOf(),
            prevKey = null,
            nextKey = null,
        )
    }
}