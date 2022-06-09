package com.cristian.rojas

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface categoriaDao {
    @Query("SELECT * FROM categoria")
    suspend fun getAll(): List<categoria>

    @Insert
    suspend fun insertAll(vararg users: categoria)

    @Query("delete from categoria where id =:id")
    suspend fun borrar(id:Int)

    @Query("update categoria set categoria =:nombre where id=:id")
    suspend fun actualizarUsuario(nombre: String,id: Int)

  /*@Transaction
    @Query("SELECT * FROM categoria")
    suspend fun getcomputadoresCategorias(): List<computadoresCategorias>*/
}
