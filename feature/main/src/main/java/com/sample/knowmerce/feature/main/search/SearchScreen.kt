package com.sample.knowmerce.feature.main.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.sample.knowmerce.feature.main.R
import com.sample.knowmerce.feature.main.search.viewModels.SearchViewModel
import com.sample.knowmerce.feature.main.search.views.SearchContentView
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel = viewModel(),
    handleToast: (message: String) -> Unit,
    onClickFab: () -> Unit,
    onClickContent: (link: String) -> Unit,
) {
    val context = LocalContext.current

    val isInit by searchViewModel.isInit.collectAsState()
    val pager by searchViewModel.pager.collectAsState()
    val items = pager.flow.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        launch {
            searchViewModel.successToArchive.collect {
                handleToast(context.getString(R.string.search_screen_archived_content))
            }
        }
    }

    SearchContentView(
        modifier = modifier,
        isInit = isInit,
        items = items,
        onClickSearch = { keyword ->
            searchViewModel.setKeyword(keyword)
        },
        onClickFab = onClickFab,
        onClickArchive = { item ->
            searchViewModel.insertArchive(item)
        },
        onClickContent = onClickContent,
    )
}