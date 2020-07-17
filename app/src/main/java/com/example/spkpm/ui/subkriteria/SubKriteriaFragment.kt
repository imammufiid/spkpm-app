package com.example.spkpm.ui.subkriteria

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.spkpm.R
import com.example.spkpm.activities.AddKriteriaActivity
import com.example.spkpm.activities.AddSubkriteriaActivity
import com.example.spkpm.adapters.SubkriteriaAdapter
import com.example.spkpm.models.KriteriaModel
import com.example.spkpm.models.SubkriteriaModel
import com.example.spkpm.presenters.KriteriaPresenter
import com.example.spkpm.presenters.SubkriteriaPresenter
import com.example.spkpm.views.SubkriteriaView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_subkriteria.*

@Suppress("DEPRECATION")
class SubKriteriaFragment : Fragment(), SubkriteriaView {

    private lateinit var slideshowViewModel: SubkriteriaViewModel
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var presenter: SubkriteriaPresenter
    private var loading: ProgressDialog? = null
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SubkriteriaViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_subkriteria, container, false)

        // init fab
        val fab: FloatingActionButton = root.findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {
            val intent = Intent(context, AddSubkriteriaActivity::class.java)
            startActivity(intent)
        }
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
            presenter.getDataBySwipeRefresh()
            sr.isRefreshing = false
        }
        return root
    }

    override fun onResume() {
        super.onResume()
        presenter.getData()
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
        presenter.getDataBySwipeRefresh()
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