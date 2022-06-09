package com.cristian.rojas

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.cristian.rojas.databinding.ActivityPerfilBinding

class perfil : AppCompatActivity() {
    private lateinit var vinculo : ActivityPerfilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        vinculo = ActivityPerfilBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(vinculo.root)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }
    fun cerrarcion() {
        startActivity(Intent(this,MainActivity::class.java))
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.salir->cerrarcion()
        }
        return super.onOptionsItemSelected(item)
    }
}