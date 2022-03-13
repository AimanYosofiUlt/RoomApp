package com.moonx.roomapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "setting_table")
data class Setting(
    @PrimaryKey
    val id: Int = 0,
    @ColumnInfo(defaultValue = "0")
    var currentId: Int
)