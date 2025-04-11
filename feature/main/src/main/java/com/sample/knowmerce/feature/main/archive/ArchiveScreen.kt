package com.sample.knowmerce.feature.main.archive

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.sample.knowmerce.feature.main.archive.viewModels.ArchiveViewModel
import com.sample.knowmerce.feature.main.archive.views.ArchiveContentView

@Composable
fun ArchiveScreen(
    modifier: Modifier = Modifier,
    archiveViewModel: ArchiveViewModel = viewModel(),
    onFinish: () -> Unit,
    onClickContent: (link: String) -> Unit,
) {
    val items = archiveViewModel.pager.collectAsLazyPagingItems()

    ArchiveContentView(
        modifier = modifier,
        items = items,
        onFinish = onFinish,
        onClickContent = onClickContent,
    )
}
