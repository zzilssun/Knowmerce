package com.sample.knowmerce.core.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.sample.knowmerce.core.data.room.entity.ArchiveEntity

@Dao
interface ArchiveDao {

    /**
     * 아카이브 목록 가져오기
     *
     * @param limit 페이지당 아이템 수
     * @param offset 페이지 시작 위치
     */
    @Query("SELECT * FROM ArchiveEntity ORDER BY createdAt DESC LIMIT :limit OFFSET :offset")
    suspend fun requestArchives(limit: Int, offset: Int): List<ArchiveEntity>
}