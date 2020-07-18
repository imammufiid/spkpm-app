package com.example.spkpm.ui.rekomendasi

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
import com.example.spkpm.activities.AddRekomendasiActivity
import com.example.spkpm.activities.RekomendasiNilaiActivity
import com.example.spkpm.adapters.KriteriaAdapter
import com.example.spkpm.adapters.RekomendasiAdapter
import com.example.spkpm.api.CheckConnection
import com.example.spkpm.models.KriteriaModel
import com.example.spkpm.models.RekomendasiModel
import com.example.spkpm.presenters.KriteriaPresenter
import com.example.spkpm.presenters.RekomendasiPresenter
import com.example.spkpm.views.KriteriaView
import com.example.spkpm.views.RekomendasiView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_kriteria.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@Suppress("DEPRECATION")
class RekomendasiFragment : Fragment(), RekomendasiView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var adapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var presenter: RekomendasiPresenter
    private lateinit var checkConnection: CheckConnection
    private var loading : ProgressDialog? = null

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
        val root =  inflater.inflate(R.layout.fragment_rekomendasi, container, false)
        // init fab
        val fab: FloatingActionButton = root.findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {
            val intent = Intent(context, AddRekomendasiActivity::class.java)
            startActivity(intent)
        }
        val btn_show_nilai : ExtendedFloatingActionButton = root.findViewById(R.id.btn_nilai) as ExtendedFloatingActionButton
        btn_show_nilai.setOnClickListener {
            val intent = Intent(context, RekomendasiNilaiActivity::class.java)
            startActivity(intent)
        }
        // init layout
        viewManager = LinearLayoutManager(activity)
        // init presenter
        presenter = RekomendasiPresenter(this)
        // init progressbar
        loading = ProgressDialog(context)

        // swipe refresh
        val sr = root.findViewById<View>(R.id.swipe_refresh) as SwipeRefreshLayout?
        sr?.setOnRefreshListener {
            sr.isRefreshing = true
            presenter.getRekomendasiSwipeRefresh()
            sr.isRefreshing = false
        }
        return root
    }

    override fun onResume() {
        super.onResume()
        presenter.getRekomendasi()
    }

    override fun onLoading(message: String) {
        loading?.setMessage(message)
        loading?.show()
    }

    override fun hideLoading() {
        loading?.dismiss()
    }


    override fun onSuccessGetData(data: List<RekomendasiModel>?) {
        rvKriteria?.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = RekomendasiAdapter(data, object : RekomendasiAdapter.OnClickItem{
                @SuppressLint("ShowToast")
                override fun clicked(item: RekomendasiModel?) {
                    activity.let {
                        val intent = Intent(it, AddRekomendasiActivity::class.java)
                        intent.putExtra("rekomendasi_id", item?.rekomendasi_id)
                        intent.putExtra("rekomendasi_kode", item?.rekomendasi_kode)
                        it?.startActivity(intent)
                    }
                }

                override fun delete(item: RekomendasiModel?) {
                    val builder = AlertDialog.Builder(context)
                    builder.setTitle("Anda ingin Menghapus data?")
                    builder.setPositiveButton("Iya"){
                            dialog, which ->
                        presenter.deleteRekom(item?.rekomendasi_id)
                        //presenter.deleteKriteria(item?.rekomendasi_id)
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
        presenter.getRekomendasiSwipeRefresh()
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