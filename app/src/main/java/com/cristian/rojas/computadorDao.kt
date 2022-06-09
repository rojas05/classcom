package com.cristian.rojas

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface computadorDao {
        @Query("SELECT * FROM computadores")
        suspend fun listar(): List<computadores>
        @Insert
        suspend fun insertar(vararg computador: computadores)

       @Query("delete from computadores where codigo =:codigo")
        suspend fun eliminar(codigo:Int)

        @Query("update computadores set modelo =:modelo, marca =:marca, categoria =:categoria where codigo=:codigo")
        suspend fun actualizarComputador(marca: String,modelo: String,categoria: Int,codigo: Int)
}