package com.example.notas_app_sqlite
import android.content.Context
import android.renderscript.Type
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
class NotasAdaptador (
    private var notas : List<Nota>,
            context: Context) : RecyclerView.Adapter<NotasAdaptador.NotaViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_nota,parent,false)
        return NotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotaViewHolder,position: Int
    ) {
       val nota = notas[position]
        holder.itemTitulo.text = nota.titulo
        holder.itemDescription.text = nota.descripcion

    }

    override fun getItemCount(): Int {
      return notas.size
    }

    class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemTitulo : TextView = itemView.findViewById(R.id.item_titulo)
        val itemDescription : TextView = itemView.findViewById(R.id.item_descripcion)
    }
    fun refrescarLista (nuevaNota : List<Nota>){
        notas= nuevaNota
        notifyDataSetChanged()
        
    }
}
