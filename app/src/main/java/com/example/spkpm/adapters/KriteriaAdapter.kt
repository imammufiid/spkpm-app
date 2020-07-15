package com.example.spkpm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spkpm.R
import com.example.spkpm.models.KriteriaModel
import kotlinx.android.synthetic.main.single_kriteria_list.view.*

class KriteriaAdapter(private val data: List<KriteriaModel>?, private val click: OnClickItem): RecyclerView.Adapter<KriteriaAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_kriteria_list, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.onBind(data?.get(position))
        holder.itemView.row.setOnLongClickListener {
            click.clicked(data?.get(position))
            return@setOnLongClickListener true
        }
        holder.itemView.btn_hapus.setOnClickListener{
            click.delete(data?.get(position))
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