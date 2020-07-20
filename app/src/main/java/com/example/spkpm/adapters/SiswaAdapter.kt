package com.example.spkpm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.spkpm.R
import com.example.spkpm.models.KriteriaModel
import com.example.spkpm.models.SiswaModel
import kotlinx.android.synthetic.main.single_kriteria_list.view.*
import kotlinx.android.synthetic.main.single_siswa_list.view.*

class SiswaAdapter(private val data: List<SiswaModel>?, private val click: OnClickItem): RecyclerView.Adapter<SiswaAdapter.MyHolder>() {
    var context: Context? = null
    class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun onBind(get: SiswaModel?){
            itemView.user_id.text = get?.user_id.toString()
            itemView.user_nama.text = get?.user_nama
            itemView.user_email.text = get?.user_email
        }

    }

    interface OnClickItem {
        fun clicked(item: SiswaModel?)
        fun delete(item: SiswaModel?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_siswa_list, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.onBind(data?.get(position))
        holder.itemView.btn_more.setOnClickListener {
            val popmenu = PopupMenu(context, holder.itemView.btn_more)
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