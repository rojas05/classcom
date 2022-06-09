package com.cristian.rojas

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "computadores",
foreignKeys = [
    ForeignKey(entity = categoria::class, parentColumns = ["id"], childColumns = ["categoria"])
])
data class computadores (
    @PrimaryKey val codigo: Int?,
    @ColumnInfo(name = "modelo") val modelo: String?,
    @ColumnInfo(name = "marca") val marca: String?,
    @ColumnInfo(name = "categoria") val categoria: Int?
        )