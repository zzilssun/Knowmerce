package com.sample.knowmerce.core.ui.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource

/**
 * 기본 PagingConfig
 */
private val defaultPagingConfig = PagingConfig(
    pageSize = 30,
    initialLoadSize = 30,
    enablePlaceholders = false,
)

/**
 * Pager 3 기본 값을 추가하여 전달
 */
@OptIn(ExperimentalPagingApi::class)
fun <Key : Any, Value : Any> getPager(
    config: PagingConfig = defaultPagingConfig,
    initialKey: Key? = null,
    pagingSourceFactory: () -> PagingSource<Key, Value>?,
): Pager<Key, Value> {
    return Pager(
        config = config,
        initialKey = initialKey,
        remoteMediator = null,
        pagingSourceFactory = {
            // PagingSourceFactory 로 부터 null 값이 들어오면 비어있는 PagingSource 를 리턴한다.
            pagingSourceFactory.invoke() ?: EmptyPagingSource<Key, Value>()
        },
    )
}

/**
 * 빈 Pager 를 전달
 */
@OptIn(ExperimentalPagingApi::class)
fun <Key : Any, Value : Any> getEmptyPager(
    config: PagingConfig = defaultPagingConfig,
): Pager<Key, Value> {
    return Pager(
        config = config,
        initialKey = null,
        remoteMediator = null,
        pagingSourceFactory = {
            EmptyPagingSource<Key, Value>()
        },
    )
}