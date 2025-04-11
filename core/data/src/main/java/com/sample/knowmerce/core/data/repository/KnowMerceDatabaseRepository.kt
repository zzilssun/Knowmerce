package com.sample.knowmerce.core.data.repository

import android.content.Context
import androidx.annotation.IntRange
import androidx.room.Room
import com.sample.knowmerce.core.data.room.KnowMerceDatabase
import com.sample.knowmerce.core.data.room.entity.ArchiveEntity

class KnowMerceDatabaseRepository(
    context: Context,
) {
    private val database = Room
        .databaseBuilder(
            context = context,
            klass = KnowMerceDatabase::class.java,
            name = "KnowMerceDatabaseRepository",
        )
        .build()

    /**
     * 아카이빙 된 컨텐츠 가져오기
     */
    suspend fun requestArchives(
        @IntRange(from = 0) page: Int,
        size: Int = 30,
    ): List<ArchiveEntity> {
        return database
            .getArchiveDao()
            .requestArchives(
                limit = size,
                offset = page,
            )
    }

    /**
     * 컨텐츠 아카이빙 하기
     */
    suspend fun insertArchive(archive: ArchiveEntity) {
        database
            .getArchiveDao()
            .insertArchive(archive)
    }
}