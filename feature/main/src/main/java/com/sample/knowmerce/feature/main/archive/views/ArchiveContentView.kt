package com.sample.knowmerce.feature.main.archive.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.sample.knowmerce.core.ui.extensions.rippleClickable
import com.sample.knowmerce.core.ui.paging.mockingLazyPagingItems
import com.sample.knowmerce.core.ui.scaffold.KnowMerceScaffold
import com.sample.knowmerce.feature.main.card.KakaoImageCardView
import com.sample.knowmerce.feature.main.card.KakaoVideoCardView
import com.sample.knowmerce.feature.main.card.models.KakoSearchViewData
import com.sample.knowmerce.feature.main.card.models.sampleKakaoSearchViewDataImages
import com.sample.knowmerce.feature.main.card.models.sampleKakaoSearchViewDataVideos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ArchiveContentView(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<KakoSearchViewData>,
    onFinish: () -> Unit,
    onClickContent: (link: String) -> Unit,
) {
    KnowMerceScaffold(
        modifier = modifier,
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    navigationIcon = {
                        Icon(
                            modifier = Modifier
                                .rippleClickable(
                                    shape = CircleShape,
                                    onClick = onFinish,
                                )
                                .padding(
                                    all = 8.dp,
                                ),
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = "뒤로 가기",
                        )
                    },
                    title = {
                        Text(
                            text = "컨텐츠 아카이빙",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary,
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
                                        onClickContent = {
                                            onClickContent(item.link)
                                        },
                                    )
                                }

                                is KakoSearchViewData.Video -> {
                                    KakaoVideoCardView(
                                        video = item,
                                        onClickContent = {
                                            onClickContent(item.link)
                                        },
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

@Preview
@Composable
private fun PreviewArchiveContentView() {
    Surface {
        ArchiveContentView(
            items = mockingLazyPagingItems(
                sampleKakaoSearchViewDataImages + sampleKakaoSearchViewDataVideos
            ),
            onFinish = {},
            onClickContent = {},
        )
    }
}