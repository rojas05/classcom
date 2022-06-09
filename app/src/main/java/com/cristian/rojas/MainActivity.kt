package com.cristian.rojas

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.cristian.rojas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var vinculo : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        vinculo= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(vinculo.root)

    vinculo.btingresar.setOnClickListener {


        val preferencias = getSharedPreferences( "registrar", Context.MODE_PRIVATE)
            val editor = preferencias.edit()
            val usuario = "admin"
            val contrase = "admin"
            editor.putString("usuario",usuario)
            editor.putString("contraseña",contrase)
            editor.apply()
            val user = preferencias .getString("usuario","")
            val passwor = preferencias .getString("contraseña","")
            if (TextUtils.isEmpty(vinculo.usuario.text.toString()) && TextUtils.isEmpty(vinculo.contrase.text.toString())){
                vinculo.usuario.error = "este campo es obligatorio"
                vinculo.contrase.error = "este campo es obligatorio"
                vinculo.usuario.requestFocus()
                vinculo.contrase.requestFocus()
            }else{
                val usuar= vinculo.usuario.text.toString()
                val contra= vinculo.contrase.text.toString()
                if (user.equals(usuar) and passwor.equals(contra)){
                    startActivity(Intent(this,busqueda::class.java))
                    finish()
                }else{
                    Toast.makeText(this, "datos erroneos", Toast.LENGTH_SHORT).show()
                }
            }
        }

     }

}