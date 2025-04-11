package com.sample.knowmerce.feature.main.archive.viewModels

import androidx.lifecycle.ViewModel
import com.sample.knowmerce.core.data.repository.KnowMerceDatabaseRepository
import com.sample.knowmerce.core.ui.paging.getPager
import com.sample.knowmerce.feature.main.archive.paging.ArchivePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArchiveViewModel @Inject constructor(
    private val repository: KnowMerceDatabaseRepository,
) : ViewModel() {
    internal val pager = getPager {
        ArchivePagingSource(repository)
    }.flow
}