package com.cristian.rojas

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [categoria::class,computadores::class], version = 1)
abstract class classcom:RoomDatabase(){
    abstract fun daocomputer():computadorDao
    abstract fun daoclass():categoriaDao
    companion object{
        var instancia: classcom?=null
        fun getInstancia(context: Context):classcom {
            if (instancia == null ){
                instancia = Room.databaseBuilder(
                    context.applicationContext,
                    classcom::class.java,"classcom"
                ).build()
            }
            return instancia!!
        }
    }
}