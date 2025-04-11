package com.sample.knowmerce.core.ui.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * 마지막 발행 시간과 현재 시간 비교해서 이벤트 발행, 나머지는 무시.
 */
fun <T> Flow<T>.throttleFirst(windowDuration: Long): Flow<T> = flow {
    var lastEmissionTime = 0L
    collect { upstream ->
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastEmissionTime > windowDuration) {
            lastEmissionTime = currentTime
            emit(upstream)
        }
    }
}