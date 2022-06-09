package com.cristian.rojas

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.cristian.rojas.databinding.ActivityCategoriasBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class categorias : AppCompatActivity(), View.OnClickListener {
    private lateinit var vinculo: ActivityCategoriasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        vinculo = ActivityCategoriasBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(vinculo.root)
        vinculo.btin.setOnClickListener(this)
        vinculo.btact.setOnClickListener(this)
        vinculo.btborr.setOnClickListener(this)
        vinculo.btlis.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    fun cerrarcion() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun verperfil() {
        startActivity(Intent(this, perfil::class.java))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.salir -> cerrarcion()
            R.id.perfil -> verperfil()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btin -> {
                var cate: String? = null
                if (TextUtils.isEmpty(vinculo.categorias.text.toString())) {
                    vinculo.categorias.error = "ingrese un nombre"
                    vinculo.categorias.requestFocus()
                } else {
                    cate = vinculo.categorias.text.toString()
                    mostrarinfo("categorias", "categora agregada exotasamente")
                    val usuarioinfo = categoria(null,cate)
                    GlobalScope.launch {
                        classcom.getInstancia(this@categorias).daoclass().insertAll(usuarioinfo)
                    }
                }
            }
            R.id.btact ->{
                val userid: EditText = EditText(this@categorias)
                val alerta= AlertDialog.Builder(this@categorias)
                alerta.setTitle("ingrese el id de la categoria para actualizar")
                alerta.setView(userid)
                var nom: String? = null
                if (TextUtils.isEmpty(vinculo.categorias.text.toString())){
                    vinculo.categorias.error = "ingrese una la actualizacion"
                    vinculo.categorias.requestFocus()
                }else{
                    nom = vinculo.categorias.text.toString()
                    alerta.setPositiveButton("actualizar",object: DialogInterface.OnClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            val id=userid.text.toString().toInt()
                            GlobalScope.launch {
                                if (nom != null) {

                                    classcom.getInstancia(this@categorias).daoclass().actualizarUsuario(nom,id)
                                }
                            }
                            mostrarinfo("informacion categorias", "categoria actualizada")
                        }
                    })
                    alerta.create()
                    alerta.show()
                }
            }
            R.id.btborr ->{
                val userid:EditText= EditText(this@categorias)
                val alerta=AlertDialog.Builder(this@categorias)
                alerta.setTitle("ingrese el id dela categoria a eliminar")
                alerta.setView(userid)
                alerta.setPositiveButton("borrar categoria",object:DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        val uid=userid.text.toString().toInt()
                        GlobalScope.launch {
                           classcom.getInstancia(this@categorias).daoclass().borrar(uid)
                        }
                        mostrarinfo("informacion categorias", "categoria borrada")
                    }
                })
                alerta.create()
                alerta.show()
            }
            R.id.btlis ->{
                lateinit var alluser:List<categoria>
                val userData: StringBuffer= StringBuffer()
                GlobalScope.launch(Dispatchers.IO){
                    alluser = classcom.getInstancia(this@categorias).daoclass().getAll()
                    launch(Dispatchers.Main){
                        alluser.forEach{
                            userData.append("-->" +"ID " + it.id + " CATEGORIA " +it.nombre +"\n" + "\n")
                        }
                        mostrar(userData.toString())
                    }
                }
            }
        }

    }
    private fun mostrarinfo(titulo: String, mensaje: String){
        val alerta= AlertDialog.Builder(this@categorias)
        alerta.setTitle(titulo)
        alerta.setMessage(mensaje)
        alerta.setCancelable(true)
        alerta.show()
    }
    private fun mostrar(mensaje: String){
        val alerta= AlertDialog.Builder(this@categorias)
        alerta.setMessage(mensaje)
        alerta.setCancelable(true)
        alerta.show()
    }
}