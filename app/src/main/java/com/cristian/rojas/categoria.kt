package com.cristian.rojas

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class categoria(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "categoria") val nombre: String?
)