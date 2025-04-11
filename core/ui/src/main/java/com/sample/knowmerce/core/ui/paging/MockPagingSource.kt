package com.sample.knowmerce.core.ui.paging

import androidx.compose.runtime.Composable
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.compose.collectAsLazyPagingItems

private class MockPagingSource<T : Any>(
    private val samples: List<T>,
) : PagingSource<Int, T>() {
    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        return LoadResult.Page(
            data = samples,
            prevKey = null,
            nextKey = null,
        )
    }
}

/**
 * Preview 를 위한 LazyPagingItems 를 생성
 */
@Composable
fun <T : Any> mockingLazyPagingItems(samples: List<T>) = Pager(
    config = PagingConfig(pageSize = 20),
    pagingSourceFactory = { MockPagingSource(samples) }
).flow.collectAsLazyPagingItems()