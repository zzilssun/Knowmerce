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
            name = null,
        )
        .build()

    /**
     * 저장된 10개의 최근 방문한 여행지 항목 가져오기
     */
    suspend fun requestArchives(
        @IntRange(from = 0) page: Int,
        size: Int = 30,
    ): List<ArchiveEntity> {
        if (page < 1 || size <= 0) {
            return emptyList()
        }
        val offset = (page - 1) * size

        return database
            .getArchiveDao()
            .requestArchives(
                limit = size,
                offset = offset,
            )
    }
}