package pichardo.fernanda.misnotas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.nota_layout.view.*
import java.io.File
import java.lang.Exception

class AdaptadorNotas : BaseAdapter {
    var context : Context
    var notas = ArrayList<Nota>()

    constructor(context: Context,notas:ArrayList<Nota>) {
        this.context = context
        this.notas = notas
    }

    override fun getItem(p0: Int): Any {
        return notas[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var inflador = LayoutInflater.from(context)
        var vista = inflador.inflate(R.layout.nota_layout,null)
        var nota = notas[p0]

        vista.tv_titulo_det.text = nota.titulo
        vista.tv_contenido_det.text = nota.contenido
        vista.btn_borrar.setOnClickListener {
            eliminar(nota.titulo)
            notas.remove(nota)
            this.notifyDataSetChanged()
        }
        return vista
    }

    private fun eliminar(titulo: String) {
        if(titulo == "") {
            Toast.makeText(context,"Error : título vacío", Toast.LENGTH_SHORT).show()
        } else {
            try {
                val archivo = File(ubicacion(),titulo+".txt")
                Toast.makeText(context,"Se elimino el archivo",Toast.LENGTH_SHORT).show()

            } catch (e : Exception) {
                Toast.makeText(context,"Error al eliminar el archivo",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun ubicacion(): String {
        val album = File(context?.getExternalFilesDir(null),"notas")
        if(!album.exists()) {
            album.mkdir()
        }
        return album.absolutePath
    }


    override fun getCount(): Int {
        return notas.size
    }

}