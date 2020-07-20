package com.example.spkpm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.spkpm.R
import com.example.spkpm.models.KriteriaModel
import com.example.spkpm.models.RekomendasiNilaiModel
import kotlinx.android.synthetic.main.single_rekomendasi_nilai_list.view.*

class RekomendasiNilaiAdapter(private val data: List<RekomendasiNilaiModel>?, private val click: OnClickItem):
    RecyclerView.Adapter<RekomendasiNilaiAdapter.MyHolder>() {
    var context: Context? = null
    class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun onBind(get: RekomendasiNilaiModel?) {
            itemView.rekom_nilai_id.text = get?.rekomendasi_nilai_id.toString()
            itemView.subkriteria_nama.text = get?.subkriteria_nama
            itemView.rekomendasi_nama.text = get?.rekomendasi_kode
            itemView.rekomendasi_nilai.text = get?.rekomendasi_nilai_bobot.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_rekomendasi_nilai_list, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
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
        fun clicked(item: RekomendasiNilaiModel?)
        fun delete(item: RekomendasiNilaiModel?)
    }
}