package com.example.spkpm.ui.rekomendasi

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
import com.example.spkpm.activities.AddRekomendasiNilaiActivity
import com.example.spkpm.adapters.RekomendasiNilaiAdapter
import com.example.spkpm.api.CheckConnection
import com.example.spkpm.models.KriteriaModel
import com.example.spkpm.models.RekomendasiModel
import com.example.spkpm.models.RekomendasiNilaiModel
import com.example.spkpm.models.SubkriteriaModel
import com.example.spkpm.presenters.RekomendasiNilaiPresenter
import com.example.spkpm.views.RekomendasiNilaiView
import kotlinx.android.synthetic.main.fragment_akademik_rekom.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
@Suppress("DEPRECATION")
class NonakaRekomFragment : Fragment(), RekomendasiNilaiView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    // init
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var presenter: RekomendasiNilaiPresenter
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
        val root = inflater.inflate(R.layout.fragment_nonaka_rekom, container, false)

        // init layout
        viewManager = LinearLayoutManager(activity)
        // init presenter
        presenter = RekomendasiNilaiPresenter(this)
        //presenter.getKriteria()
        // init progressbar
        loading = ProgressDialog(context)

        // swipe refresh
        val sr = root.findViewById<View>(R.id.swipe_refresh) as SwipeRefreshLayout?
        sr?.setOnRefreshListener {
            sr.isRefreshing = true
            presenter.getRekomNilaiBySwipeRefresh(2)
            sr.isRefreshing = false
        }

        return root
    }

    override fun onResume() {
        super.onResume()
        presenter.getRekomNilai(2)
    }
    override fun onLoading(message: String) {
        loading?.setMessage(message)
        loading?.show()
    }

    override fun hideLoading() {
        loading?.dismiss()
    }

    override fun onSuccessGetData(data: List<RekomendasiNilaiModel>?) {
        rv_akademik_rekom?.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = RekomendasiNilaiAdapter(data, object: RekomendasiNilaiAdapter.OnClickItem{
                override fun clicked(item: RekomendasiNilaiModel?) {
                    activity.let{
                        val intent = Intent(it, AddRekomendasiNilaiActivity::class.java).apply {
                            putExtra("rekomendasi_nilai_id", item?.rekomendasi_nilai_id)
                            putExtra("rekomendasi_nilai_bobot", item?.rekomendasi_nilai_bobot)
                        }
                        it?.startActivity(intent)
                    }
                }

                override fun delete(item: RekomendasiNilaiModel?) {
                    val builder = AlertDialog.Builder(context)
                    builder.setTitle("Anda ingin menghapus data?")
                    builder.setPositiveButton("Iya") {
                            dialog, which ->
                        presenter.deleteRekomNilai(item?.rekomendasi_nilai_id)
                    }
                    builder.setNegativeButton("Tidak") {
                            dialog, which ->
                        dialog.dismiss()
                    }
                    builder.show()
                }

            })
        }
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
        presenter.getRekomNilaiBySwipeRefresh(2)
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

    override fun onSuccessSpinnerKriteria(data: List<KriteriaModel>) {
        TODO("Not yet implemented")
    }

    override fun onSuccessSpinnerSubkriteria(data: List<SubkriteriaModel>) {
        TODO("Not yet implemented")
    }

    override fun onSuccessSpinnerRekomendasi(data: List<RekomendasiModel>) {
        TODO("Not yet implemented")
    }

    override fun onFailedSpinnerKriteria(message: String) {
        TODO("Not yet implemented")
    }

    override fun onFailedSpinnerSubkriteria(message: String) {
        TODO("Not yet implemented")
    }

    override fun onFailedSpinnerRekomendasi(message: String) {
        TODO("Not yet implemented")
    }

}