package com.example.spkpm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.spkpm.R
import com.example.spkpm.models.KriteriaModel
import com.example.spkpm.models.SiswaNilaiModel
import kotlinx.android.synthetic.main.single_kriteria_list.view.*
import kotlinx.android.synthetic.main.single_siswa_nilai_list.view.*

class SiswaNilaiAdapter(private val data: List<SiswaNilaiModel>?, private val click: OnClickItem): RecyclerView.Adapter<SiswaNilaiAdapter.MyHolder>() {
    var context: Context? = null
    class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun onBind(get: SiswaNilaiModel?){
            itemView.user_id.text = get?.user_id.toString()
            itemView.nama_subkriteria.text = get?.subkriteria_nama
            itemView.nilai_siswa.text = get?.siswa_nilai.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiswaNilaiAdapter.MyHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_siswa_nilai_list, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int = data?.size ?: 0


    override fun onBindViewHolder(holder: SiswaNilaiAdapter.MyHolder, position: Int) {
        holder.onBind(data?.get(position))
        holder.itemView.btn_action_more.setOnClickListener {
            val popmenu = PopupMenu(context, holder.itemView.btn_action_more)
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

    interface OnClickItem {
        fun clicked(item: SiswaNilaiModel?)
        fun delete(item: SiswaNilaiModel?)
    }
}