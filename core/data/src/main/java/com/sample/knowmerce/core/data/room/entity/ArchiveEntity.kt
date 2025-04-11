package com.sample.knowmerce.core.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArchiveEntity(
    @PrimaryKey
    val contentHashCode: Int,

    @ColumnInfo
    val type: String,

    @ColumnInfo
    val content: String,

    @ColumnInfo
    var createdAt: Long = System.currentTimeMillis(),
)