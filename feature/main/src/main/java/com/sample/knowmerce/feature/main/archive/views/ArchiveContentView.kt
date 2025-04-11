package com.sample.knowmerce.feature.main.archive.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.sample.knowmerce.core.ui.designSystem.theme.KnowMerceTheme
import com.sample.knowmerce.feature.main.card.KakaoImageCardView
import com.sample.knowmerce.feature.main.card.KakoVideoCardView
import com.sample.knowmerce.feature.main.card.models.KakoSearchViewData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ArchiveContentView(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<KakoSearchViewData>,
) {
    KnowMerceTheme {
        Scaffold(
            modifier = modifier
                .fillMaxSize(),
            topBar = {
                Column {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "컨텐츠 아카이빙",
                            )
                        },
                    )

                    HorizontalDivider()
                }
            },
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ) {
                if (items.loadState.refresh == LoadState.Loading) {
                    // 최초 로드 케이스
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center),
                    )
                } else {
                    if (items.itemCount == 0) {
                        // 최초 로드 이후 데이터가 없는 경우
                        Text(
                            modifier = Modifier
                                .align(Alignment.Center),
                            text = "검색 결과가 없습니다.",
                        )
                    } else {
                        // 최초 로드 이후 데이터가 있는 경우
                        LazyVerticalGrid(
                            modifier = Modifier
                                .fillMaxSize(),
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(
                                all = 8.dp,
                            ),
                        ) {
                            items(
                                count = items.itemCount,
                                key = { index ->
                                    items[index].hashCode() + index
                                },
                            ) { index ->
                                val item = items[index] ?: return@items

                                when (item) {
                                    is KakoSearchViewData.Image -> {
                                        KakaoImageCardView(
                                            image = item,
                                        )
                                    }

                                    is KakoSearchViewData.Video -> {
                                        KakoVideoCardView(
                                            video = item,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}