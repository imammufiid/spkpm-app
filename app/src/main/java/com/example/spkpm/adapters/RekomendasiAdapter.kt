package com.example.spkpm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.spkpm.R
import com.example.spkpm.models.KriteriaModel
import com.example.spkpm.models.RekomendasiModel
import kotlinx.android.synthetic.main.single_kriteria_list.view.*
import kotlinx.android.synthetic.main.single_kriteria_list.view.action_more
import kotlinx.android.synthetic.main.single_rekomendasi_list.view.*

class RekomendasiAdapter(private val data: List<RekomendasiModel>?, private val click: RekomendasiAdapter.OnClickItem): RecyclerView.Adapter<RekomendasiAdapter.MyHolder>() {
    var context: Context? = null
    interface OnClickItem {
        fun clicked(item: RekomendasiModel?)
        fun delete(item: RekomendasiModel?)
    }

    class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun onBind(get: RekomendasiModel?){
            itemView.rekomendasi_id.text = get?.rekomendasi_id.toString()
            itemView.rekomendasi_kode.text = get?.rekomendasi_kode
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_rekomendasi_list, parent, false)
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
}