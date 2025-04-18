package com.sample.knowmerce.feature.main.search.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AllInbox
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.sample.knowmerce.core.ui.designSystem.InputView
import com.sample.knowmerce.core.ui.extensions.DevicePosture
import com.sample.knowmerce.core.ui.extensions.rememberDevicePosture
import com.sample.knowmerce.core.ui.extensions.rippleClickable
import com.sample.knowmerce.core.ui.paging.mockingLazyPagingItems
import com.sample.knowmerce.core.ui.scaffold.KnowMerceScaffold
import com.sample.knowmerce.feature.main.R
import com.sample.knowmerce.feature.main.card.KakaoImageCardView
import com.sample.knowmerce.feature.main.card.KakaoVideoCardView
import com.sample.knowmerce.feature.main.card.models.KakoSearchViewData
import com.sample.knowmerce.feature.main.card.models.sampleKakaoSearchViewDataImages
import com.sample.knowmerce.feature.main.card.models.sampleKakaoSearchViewDataVideos

@Composable
internal fun SearchContentView(
    modifier: Modifier = Modifier,
    isInit: Boolean,
    items: LazyPagingItems<KakoSearchViewData>,
    onClickSearch: (keyword: String) -> Unit,
    onClickFab: () -> Unit,
    onClickArchive: (item: KakoSearchViewData) -> Unit,
    onClickContent: (link: String) -> Unit,
) {
    var keyword by remember { mutableStateOf("") }

    KnowMerceScaffold(
        modifier = modifier,
        topBar = {
            Column {
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth(),
                    keyword = keyword,
                    onChangeValue = {
                        keyword = it
                    },
                    onClickSearch = {
                        onClickSearch(keyword)
                    },
                )

                HorizontalDivider(
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onClickFab,
            ) {
                Icon(
                    imageVector = Icons.Filled.AllInbox,
                    contentDescription = "아카이빙 페이지 이동",
                )
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            if (isInit) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = "카카오 검색 시스템에 오신 것을 환영합니다.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            } else {
                ContentsView(
                    items = items,
                    onClickArchive = onClickArchive,
                    onClickContent = onClickContent,
                )
            }
        }
    }
}

@Composable
private fun SearchBar(
    modifier: Modifier = Modifier,
    keyword: String,
    onChangeValue: (changedValue: String) -> Unit,
    onClickSearch: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                all = 16.dp,
            ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        InputView(
            modifier = Modifier
                .weight(1f),
            initValue = keyword,
            hint = stringResource(R.string.search_screen_hint),
            onChangeValue = onChangeValue,
        )

        Icon(
            modifier = Modifier
                .rippleClickable(
                    shape = CircleShape,
                    onClick = {
                        onClickSearch()

                        keyboardController?.hide()
                        focusManager.clearFocus()
                    },
                )
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape,
                )
                .padding(
                    all = 8.dp
                ),
            imageVector = Icons.Filled.Search,
            tint = MaterialTheme.colorScheme.onPrimary,
            contentDescription = "검색",
        )
    }
}

@Composable
private fun ContentsView(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<KakoSearchViewData>,
    onClickArchive: (item: KakoSearchViewData) -> Unit,
    onClickContent: (link: String) -> Unit,
) {
    val devicePosture = rememberDevicePosture()
    val spanCount by remember(devicePosture) {
        mutableIntStateOf(
            when (devicePosture) {
                DevicePosture.Flat -> 4
                else -> 2
            }
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize(),
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
                    text = stringResource(R.string.search_screen_empty_result),
                )
            } else {
                // 최초 로드 이후 데이터가 있는 경우
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxSize(),
                    columns = GridCells.Fixed(spanCount),
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
                                    onClickArchive = {
                                        onClickArchive(item)
                                    },
                                    onClickContent = {
                                        onClickContent(item.link)
                                    },
                                )
                            }

                            is KakoSearchViewData.Video -> {
                                KakaoVideoCardView(
                                    video = item,
                                    onClickArchive = {
                                        onClickArchive(item)
                                    },
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

@Preview
@Composable
private fun PreviewSearchContentView_init() {
    Surface {
        SearchContentView(
            isInit = true,
            items = mockingLazyPagingItems(listOf()),
            onClickSearch = {},
            onClickArchive = {},
            onClickContent = {},
            onClickFab = {},
        )
    }
}

@Preview
@Composable
private fun PreviewSearchContentView() {
    Surface {
        SearchContentView(
            isInit = false,
            items = mockingLazyPagingItems(
                sampleKakaoSearchViewDataImages + sampleKakaoSearchViewDataVideos
            ),
            onClickSearch = {},
            onClickArchive = {},
            onClickContent = {},
            onClickFab = {},
        )
    }
}

@Preview(device = Devices.PIXEL_FOLD)
@Composable
private fun PreviewSearchContentView_flat() {
    Surface {
        SearchContentView(
            isInit = false,
            items = mockingLazyPagingItems(
                sampleKakaoSearchViewDataImages + sampleKakaoSearchViewDataVideos
            ),
            onClickSearch = {},
            onClickArchive = {},
            onClickContent = {},
            onClickFab = {},
        )
    }
}