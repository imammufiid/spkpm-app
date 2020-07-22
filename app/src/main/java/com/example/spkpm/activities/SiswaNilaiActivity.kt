package com.example.spkpm.activities

import android.app.AlertDialog
import android.app.AlertDialog.*
import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.spkpm.R
import com.example.spkpm.adapters.SiswaNilaiAdapter
import com.example.spkpm.api.CheckConnection
import com.example.spkpm.models.SiswaNilaiModel
import com.example.spkpm.models.SubkriteriaModel
import com.example.spkpm.presenters.KriteriaPresenter
import com.example.spkpm.presenters.SiswaNilaiPresenter
import com.example.spkpm.views.SiswaNilaiView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_siswa_nilai.*
import kotlinx.android.synthetic.main.toolbar.*

@Suppress("DEPRECATION")
class SiswaNilaiActivity : AppCompatActivity(), SiswaNilaiView {
    private lateinit var presenter: SiswaNilaiPresenter
    private lateinit var checkConnection: CheckConnection
    private var loading : ProgressDialog? = null
    private var user_id: Int? = null
    private lateinit var viewManager: RecyclerView.LayoutManager
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_siswa_nilai)

        // init toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        tv_title.text = "Nilai Siswa"
        supportActionBar?.setDisplayShowTitleEnabled(false)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = resources.getColor(R.color.colorWhite)

        // get
        user_id = intent.getIntExtra("user_id", 0)
        val id_user = intent.getIntExtra("user_id", 0)

        // init
        presenter = SiswaNilaiPresenter(this)
        loading = ProgressDialog(this)
        viewManager = LinearLayoutManager(this)
        // init fab
        val fab: FloatingActionButton = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {
            //Toast.makeText(applicationContext, "Add", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, AddSiswaNilaiActivity::class.java)
            intent.putExtra("user_id", id_user)
            startActivity(intent)
        }

        // swipe refresh
        val sr = findViewById<View>(R.id.swipe_refresh) as SwipeRefreshLayout?
        sr?.setOnRefreshListener {
            sr.isRefreshing = true
            presenter.getSiswaNilai(user_id)
            sr.isRefreshing = false
        }

        // back button
        backbutton.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getSiswaNilai(user_id)
    }

    override fun onLoading(message: String) {
        loading?.setMessage(message)
        loading?.show()
    }

    override fun hideLoading() {
        loading?.dismiss()
    }

    override fun onSuccessGetData(data: List<SiswaNilaiModel>?) {
        rvSiswaNilai?.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = SiswaNilaiAdapter(data, object : SiswaNilaiAdapter.OnClickItem{
                override fun clicked(item: SiswaNilaiModel?) {
                    val intent = Intent(this@SiswaNilaiActivity, AddSiswaNilaiActivity::class.java)
                    intent.putExtra("nilai_id", item?.nilai_id)
                    intent.putExtra("user_id", item?.user_id)
                    intent.putExtra("siswa_nilai", item?.siswa_nilai)
                    intent.putExtra("subkriteria_id", item?.subkriteria_id)
                    startActivity(intent)
                }

                override fun delete(item: SiswaNilaiModel?) {
                    val builder = Builder(context)
                    builder.setTitle("Anda ingin Menghapus data?")
                    builder.setPositiveButton("Iya"){
                            dialog, which ->
                        presenter.deleteSiswaNilai(item?.nilai_id)
                    }
                    builder.setNegativeButton("Tidak"){
                            dialog, which -> dialog.dismiss()
                    }
                    builder.show()
                }

            })

        }
    }

    override fun onFailedGetData(message: String) {
        val toast = Toast.makeText(this, message,  Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        toast.show()
    }

    override fun onDataNull(message: String) {
        val toast = Toast.makeText(this, message,  Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        toast.show()
    }

    override fun onSuccessAdd(message: String) {
        TODO("Not yet implemented")
    }

    override fun onFailedAdd(message: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessDelete(message: String) {
        val toast = Toast.makeText(this, message,  Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        toast.show()
        presenter.getSiswaNilai(user_id)
    }

    override fun onFailedDelete(message: String) {
        val toast = Toast.makeText(this, message,  Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        toast.show()
    }

    override fun onSuccessUpdate(message: String) {
        TODO("Not yet implemented")
    }

    override fun onFailedUpdate(message: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessSpinner(data: List<SubkriteriaModel>) {
        TODO("Not yet implemented")
    }

    override fun onFailedSpinner(message: String) {
        TODO("Not yet implemented")
    }
}