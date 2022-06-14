package com.cristian.rojas

import android.content.Intent
import android.net.Uri
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
        vinculo.gitb.setOnClickListener{
            var direction = "https://github.com/rojas05";
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(direction))
            startActivity(browserIntent)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }
    fun cerrarcion() {
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.salir->cerrarcion()
        }
        return super.onOptionsItemSelected(item)
    }
}