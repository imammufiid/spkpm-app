package com.example.spkpm.ui.subkriteria

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
import com.example.spkpm.activities.AddSubkriteriaActivity
import com.example.spkpm.adapters.SubkriteriaAdapter
import com.example.spkpm.models.KriteriaModel
import com.example.spkpm.models.SubkriteriaModel
import com.example.spkpm.presenters.SubkriteriaPresenter
import com.example.spkpm.views.SubkriteriaView
import kotlinx.android.synthetic.main.fragment_core.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@Suppress("DEPRECATION")
class SecondaryFragment : Fragment(), SubkriteriaView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    // init
    // init
    private lateinit var slideshowViewModel: SubkriteriaViewModel
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var presenter: SubkriteriaPresenter
    private var loading: ProgressDialog? = null

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
        val root = inflater.inflate(R.layout.fragment_secondary, container, false)
        // init layout
        viewManager = LinearLayoutManager(activity)
        // init presenter
        presenter = SubkriteriaPresenter(this)
        // init progressbar
        loading = ProgressDialog(context)

        // swipe refresh
        val sr = root.findViewById<View>(R.id.swipe_refresh) as SwipeRefreshLayout?
        sr?.setOnRefreshListener {
            sr.isRefreshing = true
            presenter.getDataSecondaryBySwipeRefresh()
            sr.isRefreshing = false
        }
        return root
    }
    override fun onResume() {
        super.onResume()
        presenter.getDataSecondary()
    }

    override fun onLoading(message: String) {
        loading?.setMessage(message)
        loading?.show()
    }

    override fun hideLoading() {
        loading?.dismiss()
    }

    override fun onSuccessGetData(data: List<SubkriteriaModel>?) {
        rvSubkriteria?.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = SubkriteriaAdapter(data, object : SubkriteriaAdapter.OnClickItem{
                override fun clicked(item: SubkriteriaModel?) {
                    activity?.let{
                        activity.let {
                            val intent = Intent(it, AddSubkriteriaActivity::class.java).apply {
                                putExtra("subkriteria_id", item?.subkriteria_id)
                                putExtra("subkriteria_kode", item?.subkriteria_kode)
                                putExtra("subkriteria_nama", item?.subkriteria_nama)
                                putExtra("kriteria_id", item?.kriteria_id)
                                putExtra("subkriteria_keterangan", item?.subkriteria_keterangan)
                            }
                            it?.startActivity(intent)
                        }
                    }
                }

                override fun delete(item: SubkriteriaModel?) {
                    val builder = AlertDialog.Builder(context)
                    builder.setTitle("Anda ingin Menghapus data?")
                    builder.setPositiveButton("Iya") {
                            dialog, which ->
                        //Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show()
                        presenter.deleteSubkriteria(item?.subkriteria_id)
                    }
                    builder.setNegativeButton("Tidak") {
                            dialog, which ->
                        dialog.dismiss()
                        //Toast.makeText(context, "Cancel", Toast.LENGTH_SHORT).show()
                    }
                    builder.show()
                }

            })
        }
    }

    override fun onFailedGetData(message: String) {
        TODO("Not yet implemented")
    }

    override fun onDataNull(message: String) {
        TODO("Not yet implemented")
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
        presenter.getDataSecondaryBySwipeRefresh()
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

    override fun onSuccessSpinner(data: List<KriteriaModel>) {
        TODO("Not yet implemented")
    }

    override fun onFailedSpinner(message: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGenerate(kode: String) {
        TODO("Not yet implemented")
    }

    override fun onFailedGenerate(message: String) {
        TODO("Not yet implemented")
    }
}