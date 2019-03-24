package net.azarquiel.recetascocina.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.rowcategorias.view.*
import net.azarquiel.recetascocina.model.Categoria

/**
 * Created by pacopulido on 9/10/18.
 */
class CustomAdapterCategorias(val context: Context,
                    val layout: Int
                    ) : RecyclerView.Adapter<CustomAdapterCategorias.ViewHolder>() {

    private var dataList: List<Categoria> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setCategorias(categoria: List<Categoria>) {
        this.dataList = categoria
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Categoria){
            itemView.tvNombreCategoria.text=dataItem.nombre
            itemView.cvCategorias.setCardBackgroundColor(Color.argb(200,200,200,255))
            itemView.tag=dataItem
        }
    }
}