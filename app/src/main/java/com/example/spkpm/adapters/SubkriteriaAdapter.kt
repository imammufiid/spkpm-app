package com.example.spkpm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.spkpm.R
import com.example.spkpm.models.KriteriaModel
import com.example.spkpm.models.SubkriteriaModel
import kotlinx.android.synthetic.main.single_kriteria_list.view.*
import kotlinx.android.synthetic.main.single_subkriteria_list.view.*
import kotlinx.android.synthetic.main.single_subkriteria_list.view.nama_kriteria

class SubkriteriaAdapter(private val data: List<SubkriteriaModel>?, private val click: OnClickItem): RecyclerView.Adapter<SubkriteriaAdapter.MyHolder>() {
    var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubkriteriaAdapter.MyHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_subkriteria_list, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: SubkriteriaAdapter.MyHolder, position: Int) {
        holder.onBind(data?.get(position))
        holder.itemView.btn_more.setOnClickListener {
            val popmenu = PopupMenu(context, holder.itemView.btn_more)
            popmenu.inflate(R.menu.action_menu)
            popmenu.setOnMenuItemClickListener {
                when(it.itemId) {
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

    interface OnClickItem {
        fun clicked(item: SubkriteriaModel?)
        fun delete(item: SubkriteriaModel?)
    }

    class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun onBind(get: SubkriteriaModel?) {
            itemView.subkriteria_id.text = get?.subkriteria_id.toString()
            itemView.nama_subkriteria.text = get?.subkriteria_nama
            itemView.subkriteria_kode.text = get?.subkriteria_kode
            itemView.subkriteria_keterangan.text = get?.subkriteria_keterangan
            itemView.nama_kriteria.text = get?.kriteria_nama
        }

    }
}