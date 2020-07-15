package com.example.spkpm.ui.gallery

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
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
import com.example.spkpm.adapters.KriteriaAdapter
import com.example.spkpm.models.KriteriaModel
import com.example.spkpm.presenters.KriteriaPresenter
import com.example.spkpm.presenters.KriteriaView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_gallery.*

@Suppress("DEPRECATION")
class GalleryFragment : Fragment(), KriteriaView {

    private lateinit var galleryViewModel: GalleryViewModel
    private lateinit var adapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var presenter: KriteriaPresenter
    private var loading : ProgressDialog? = null
    @SuppressLint("ShowToast")
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        // init layout
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        // init fab
        val fab: FloatingActionButton = root.findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {
            val intent = Intent(context, AddKriteriaActivity::class.java)
            startActivity(intent)
        }
        // init layout
        viewManager = LinearLayoutManager(activity)
        // init presenter
        presenter = KriteriaPresenter(this)
        //presenter.getKriteria()
        // init progressbar
        loading = ProgressDialog(context)

        // swipe refresh
        val sr = root.findViewById<View>(R.id.swipe_refresh) as SwipeRefreshLayout?
        sr?.setOnRefreshListener {
            sr.isRefreshing = true
            presenter.getKriteriaBySwipeRefresh()
            sr.isRefreshing = false
        }
        return root
    }

    override fun onResume() {
        super.onResume()
        presenter.getKriteria()
    }

    override fun onLoading(message: String) {
        loading?.setMessage(message)
        loading?.show()
    }

    override fun hideLoading() {
        loading?.dismiss()
    }

    override fun onSuccessGetData(data: List<KriteriaModel>?) {
        rvKriteria?.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = KriteriaAdapter(data, object : KriteriaAdapter.OnClickItem{
                @SuppressLint("ShowToast")
                override fun clicked(item: KriteriaModel?) {
                    //Toast.makeText(context, "edit", Toast.LENGTH_SHORT).show()
                    val builder = AlertDialog.Builder(context)
                    builder.setTitle("Anda ingin mengubah data?")
                    builder.setPositiveButton("Iya"){ dialog, which ->
                        activity.let {
                            val intent = Intent(it, AddKriteriaActivity::class.java)
                            intent.putExtra("kriteria_id", item?.kriteria_id)
                            intent.putExtra("kriteria_nama", item?.kriteria_nama)
                            it?.startActivity(intent)
                        }

                    }
                    builder.setNegativeButton("Tidak"){
                            dialog, which -> dialog.dismiss();
                    }
                    builder.show()
                }

                override fun delete(item: KriteriaModel?) {
                    val builder = AlertDialog.Builder(context)
                    builder.setTitle("Anda ingin Menghapus data?")
                    builder.setPositiveButton("Iya"){
                            dialog, which ->
                        //Toast.makeText(context, item?.kriteria_id.toString(), Toast.LENGTH_SHORT).show()
                        presenter.deleteKriteria(item?.kriteria_id)
                        //dialog.dismiss();
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
        presenter.getKriteria()
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