@file:Suppress("DEPRECATION")

package com.example.spkpm.ui.siswa

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.spkpm.R
import com.example.spkpm.activities.AddKriteriaActivity
import com.example.spkpm.activities.AddSiswaActivity
import com.example.spkpm.adapters.KriteriaAdapter
import com.example.spkpm.adapters.SiswaAdapter
import com.example.spkpm.api.CheckConnection
import com.example.spkpm.models.KriteriaModel
import com.example.spkpm.models.SiswaModel
import com.example.spkpm.presenters.KriteriaPresenter
import com.example.spkpm.presenters.SiswaPresenter
import com.example.spkpm.views.KriteriaView
import com.example.spkpm.views.SiswaView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_kriteria.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var adapter: RecyclerView.Adapter<*>
private lateinit var viewManager: RecyclerView.LayoutManager
private lateinit var presenter: SiswaPresenter
private lateinit var checkConnection: CheckConnection
private var loading : ProgressDialog? = null

class SiswaFragment : Fragment(), SiswaView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_siswa, container, false)
        // init fab
        val fab: FloatingActionButton = root.findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {
            val intent = Intent(context, AddSiswaActivity::class.java)
            startActivity(intent)
        }
        // init layout
        viewManager = LinearLayoutManager(activity)
        // init presenter
        presenter = SiswaPresenter(this)
        //presenter.getKriteria()
        // init progressbar
        loading = ProgressDialog(context)

        // swipe refresh
        val sr = root.findViewById<View>(R.id.swipe_refresh) as SwipeRefreshLayout?
        sr?.setOnRefreshListener {
            sr.isRefreshing = true
            presenter.getSiswaBySwipeRefresh()
            sr.isRefreshing = false
        }
        return root
    }

    override fun onResume() {
        super.onResume()
        presenter.getSiswa()
    }

    override fun onLoading(message: String) {
        loading?.setMessage(message)
        loading?.show()
    }

    override fun hideLoading() {
        loading?.dismiss()
    }

    override fun onSuccessGetData(data: List<SiswaModel>?) {
        rvKriteria?.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = SiswaAdapter(data, object : SiswaAdapter.OnClickItem{
                @SuppressLint("ShowToast")
                override fun clicked(item: SiswaModel?) {
                    activity.let {
                        val intent = Intent(it, AddSiswaActivity::class.java)
                        intent.putExtra("user_id", item?.user_id)
                        intent.putExtra("user_nama", item?.user_nama)
                        intent.putExtra("user_email", item?.user_email)
                        intent.putExtra("user_jurusan", item?.user_jurusan)
                        it?.startActivity(intent)
                    }
                }

                override fun delete(item: SiswaModel?) {
                    val builder = AlertDialog.Builder(context)
                    builder.setTitle("Anda ingin Menghapus data?")
                    builder.setPositiveButton("Iya"){
                            dialog, which ->
                        presenter.deleteSiswa(item?.user_id)
                    }
                    builder.setNegativeButton("Tidak"){
                            dialog, which -> dialog.dismiss();
                    }
                    builder.show()

                }

            })
        }
    }
    private fun refresh(){
        presenter.getSiswaBySwipeRefresh()
    }

    override fun onFailedGetData(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDataNull(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessAdd(message: String) {
        TODO("Not yet implemented")
    }

    override fun onFailedAdd(message: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessDelete(message: String) {
        val toast = Toast.makeText(context, message,  Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        toast.show()
        refresh()
    }

    override fun onFailedDelete(message: String) {
        val toast = Toast.makeText(context, message,  Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        toast.show()
    }

    override fun onSuccessUpdate(message: String) {
        TODO("Not yet implemented")
    }

    override fun onFailedUpdate(message: String) {
        TODO("Not yet implemented")
    }

}