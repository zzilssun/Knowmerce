package com.sample.knowmerce.core.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.knowmerce.core.data.room.dao.ArchiveDao
import com.sample.knowmerce.core.data.room.entity.ArchiveEntity

@Database(
    entities = [
        ArchiveEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class KnowMerceDatabase : RoomDatabase() {
    abstract fun getArchiveDao(): ArchiveDao
}