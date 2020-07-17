package com.example.spkpm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.spkpm.R
import com.example.spkpm.models.KriteriaModel
import kotlinx.android.synthetic.main.single_kriteria_list.view.*

class KriteriaAdapter(private val data: List<KriteriaModel>?, private val click: OnClickItem): RecyclerView.Adapter<KriteriaAdapter.MyHolder>() {
    var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_kriteria_list, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.onBind(data?.get(position))
        holder.itemView.action_more.setOnClickListener {
            val popmenu = PopupMenu(context, holder.itemView.action_more)
            popmenu.inflate(R.menu.action_menu)
            popmenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.action_edit -> {
                        click.clicked(data?.get(position))
                    }
                    R.id.action_hapus -> {
                        click.delete(data?.get(position))
                    }
                }
                return@setOnMenuItemClickListener false
            }
            popmenu.show()
        }
    }

    class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun onBind(get: KriteriaModel?){
            itemView.kriteria_id.text = get?.kriteria_id.toString()
            itemView.nama_kriteria.text = get?.kriteria_nama
        }
    }

    interface OnClickItem {
        fun clicked(item: KriteriaModel?)
        fun delete(item: KriteriaModel?)
    }

}