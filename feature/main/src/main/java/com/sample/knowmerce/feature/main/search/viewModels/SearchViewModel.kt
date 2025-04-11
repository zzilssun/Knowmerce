package com.sample.knowmerce.feature.main.search.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.sample.knowmerce.core.data.repository.KakaoRepository
import com.sample.knowmerce.core.data.repository.KnowMerceDatabaseRepository
import com.sample.knowmerce.core.data.room.entity.ArchiveEntity
import com.sample.knowmerce.core.ui.paging.getEmptyPager
import com.sample.knowmerce.core.ui.paging.getPager
import com.sample.knowmerce.feature.main.card.models.KakoSearchViewData
import com.sample.knowmerce.feature.main.search.paging.SearchImageAndVideoPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDateTime
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val kakaoRepository: KakaoRepository,
    private val knowMerceDatabase: KnowMerceDatabaseRepository,
) : ViewModel() {
    private val searchKeyword = MutableStateFlow<SearchKeyword?>(null)

    val isInit = searchKeyword.map { searchKeyword ->
        searchKeyword == null
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = true,
    )
    internal val pager = searchKeyword.map { searchKeyword ->
        // 매번 갱신을 할 수 있도록 처리를 했는데도 여기가 트리거 되지않아
        // 이 안쪽이 왜 트리거가 되지 않을까?
        getPager {
            if (searchKeyword == null) return@getPager null

            SearchImageAndVideoPagingSource(
                kakaoRepository = kakaoRepository,
                keyword = searchKeyword.keyword,
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = getEmptyPager(),
    )

    val successToArchive = MutableSharedFlow<Unit>()

    fun setKeyword(keyword: String) {
        val preSearchKeyword = searchKeyword.value
        if (preSearchKeyword != null) {
            // 기존 키워드가 있는 경우 갱신을 해야 하는 경우에만 키워드를 할당한다.
            if (preSearchKeyword.isRefresh(keyword)) {
                searchKeyword.value = SearchKeyword(
                    keyword = keyword,
                )
            }
        } else {
            searchKeyword.value = SearchKeyword(
                keyword = keyword,
            )
        }
    }

    internal fun insertArchive(item: KakoSearchViewData) {
        viewModelScope.launch {
            runCatching {
                knowMerceDatabase.insertArchive(
                    when (item) {
                        is KakoSearchViewData.Image -> {
                            ArchiveEntity(
                                contentHashCode = item.hashCode(),
                                type = "image",
                                content = Gson().toJson(item),
                            )
                        }

                        is KakoSearchViewData.Video -> {
                            ArchiveEntity(
                                contentHashCode = item.hashCode(),
                                type = "video",
                                content = Gson().toJson(item),
                            )
                        }
                    }
                )

                successToArchive.emit(Unit)
            }
        }
    }

    private data class SearchKeyword(
        val keyword: String,
        val searchTime: LocalDateTime = LocalDateTime.now(),
    ) {
        /**
         * 갱신을 해야 하는가?
         */
        fun isRefresh(
            compareKeyword: String,
            compareMinute: Long = 5L,
        ): Boolean {
            if (keyword == compareKeyword) {
                val duration = Duration.between(searchTime, LocalDateTime.now())
                val absoluteDuration = duration.abs()
                val fiveMinutes = Duration.ofMinutes(compareMinute)

                return absoluteDuration > fiveMinutes
            } else {
                return true
            }
        }
    }
}