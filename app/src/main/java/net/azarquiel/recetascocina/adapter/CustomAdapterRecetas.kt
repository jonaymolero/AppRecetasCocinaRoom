package net.azarquiel.recetascocina.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.rowrecetas.view.*
import net.azarquiel.recetascocina.model.Receta

class CustomAdapterRecetas(val context: Context,
                    val layout: Int
                    ) : RecyclerView.Adapter<CustomAdapterRecetas.ViewHolder>() {

    private var dataList: List<Receta> = emptyList()

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

    internal fun setrecetas(receta: List<Receta>) {
        this.dataList = receta
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Receta){
            itemView.tvNombreReceta.text=dataItem.titulo
            itemView.cvRecetas.setCardBackgroundColor(Color.argb(200,200,200,255))
            itemView.tag=dataItem
        }

    }
}