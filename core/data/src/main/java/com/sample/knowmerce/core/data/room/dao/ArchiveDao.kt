package com.sample.knowmerce.core.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
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

    /**
     * 컨텐츠 아카이빙 하기
     *
     * @param archive 아카이브 정보
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArchive(archive: ArchiveEntity)
}