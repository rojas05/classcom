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
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.cristian.rojas.databinding.ActivityBusquedaBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class busqueda : AppCompatActivity(), View.OnClickListener {
    private lateinit var vinculo : ActivityBusquedaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        vinculo = ActivityBusquedaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(vinculo.root)
        vinculo.insertar.setOnClickListener(this)
        vinculo.eliminar.setOnClickListener(this)
        vinculo.listar.setOnClickListener(this)
        vinculo.actualizar.setOnClickListener(this)
        vinculo.categoria.setOnClickListener(this)
        vinculo.btcategoria.setOnClickListener {
          val intent =  Intent(this,categorias::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }
    fun cerrarcion() {
        startActivity(Intent(this,MainActivity::class.java))
    }
    fun verperfil() {
        startActivity(Intent(this,perfil::class.java))
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.salir->cerrarcion()
            R.id.perfil->verperfil()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun mostrarinfo(titulo: String, mensaje: String){
        val alerta= AlertDialog.Builder(this@busqueda)
        alerta.setTitle(titulo)
        alerta.setMessage(mensaje)
        alerta.setCancelable(true)
        alerta.show()
    }
    private fun mostrar(mensaje: String){
        val alerta= AlertDialog.Builder(this@busqueda)
        alerta.setMessage(mensaje)
        alerta.setCancelable(true)
        alerta.show()
    }
    override fun onClick(v: View?) {
        when (v?.id){
            R.id.insertar->{
                var cate: Int? = null
                var marc: String? = null
                var codi: Int? = null
                var mode: String? = null
                if (TextUtils.isEmpty(vinculo.modelo.text.toString()) && TextUtils.isEmpty(vinculo.marca.text.toString())
                    && TextUtils.isEmpty(vinculo.codigo.text.toString()) && TextUtils.isEmpty(vinculo.categoria.text.toString())) {
                    vinculo.modelo.error = "ingrese un modelo"
                    vinculo.marca.error = "ingrese una marca"
                    vinculo.categoria.error = "ingrese el id de una categoria"
                    vinculo.codigo.error = "ingrese el codigo"
                    vinculo.modelo.requestFocus()
                    vinculo.marca.requestFocus()
                    vinculo.codigo.requestFocus()
                    vinculo.categoria.requestFocus()
                } else {
                    cate = vinculo.categoria.text.toString().toInt()
                    marc = vinculo.marca.text.toString()
                    codi = vinculo.codigo.text.toString().toInt()
                    mode = vinculo.modelo.text.toString()
                    mostrarinfo("computadores", "computador agregado exitosamente")
                    val computerInfo = computadores(codi,mode,marc,cate)
                    GlobalScope.launch {
                        classcom.getInstancia(this@busqueda).daocomputer().insertar(computerInfo)
                    }
                }
            }
            R.id.actualizar->{
                val codigo: EditText = EditText(this@busqueda)
                val alerta= AlertDialog.Builder(this@busqueda)
                alerta.setTitle("ingrese el codigo del computador para actualizar")
                alerta.setView(codigo)
                var cate: Int? = null
                var marc: String? = null
                var mode: String? = null
                vinculo.codigo.text = null
                if (TextUtils.isEmpty(vinculo.modelo.text.toString()) && TextUtils.isEmpty(vinculo.marca.text.toString())
                   && TextUtils.isEmpty(vinculo.categoria.text.toString())) {
                    vinculo.modelo.error = "ingrese un modelo"
                    vinculo.marca.error = "ingrese una marca"
                    vinculo.categoria.error = "ingrese el id de una categoria"
                    vinculo.modelo.requestFocus()
                    vinculo.marca.requestFocus()
                    vinculo.categoria.requestFocus()
                }else{
                    cate = vinculo.categoria.text.toString().toInt()
                    marc = vinculo.marca.text.toString()
                    mode = vinculo.modelo.text.toString()
                    alerta.setPositiveButton("actualizar",object: DialogInterface.OnClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            val codigo=codigo.text.toString().toInt()
                            GlobalScope.launch {
                                if (cate != null && marc != null && mode != null) {

                                    classcom.getInstancia(this@busqueda).daocomputer().actualizarComputador(marc,mode,cate,codigo)
                                }
                            }
                            mostrarinfo("informacion computadores", "computador actualizado")
                        }
                    })
                    alerta.create()
                    alerta.show()
                }
            }
            R.id.eliminar->{
                val codigo:EditText= EditText(this@busqueda)
                val alerta=AlertDialog.Builder(this@busqueda)
                alerta.setTitle("ingrese codigo del computador a eliminar")
                alerta.setView(codigo)
                alerta.setPositiveButton("borrar computador",object:DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        val codigo=codigo.text.toString().toInt()
                        GlobalScope.launch {
                            classcom.getInstancia(this@busqueda).daocomputer().eliminar(codigo)
                        }
                        mostrarinfo("informacion computador", "computador borrado")
                    }
                })
                alerta.create()
                alerta.show()
            }
            R.id.listar->{
                lateinit var alluser:List<computadores>
                val userData: StringBuffer= StringBuffer()
                GlobalScope.launch(Dispatchers.IO){
                    alluser = classcom.getInstancia(this@busqueda).daocomputer().listar()

                    launch(Dispatchers.Main){
                        alluser.forEach{
                            userData.append( "-->" + "CODIGO " + it.codigo + " MODELO " +it.modelo +"\n"
                                   + " MARCA " +it.marca  + " CATEGOTIA "+it.categoria +"\n"+"\n")
                        }
                        mostrar(userData.toString())
                    }
                }
            }
        }
    }
}