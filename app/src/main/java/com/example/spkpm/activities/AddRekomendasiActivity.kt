package com.example.spkpm.activities

import android.app.ProgressDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import com.example.spkpm.R
import com.example.spkpm.models.RekomendasiModel
import com.example.spkpm.presenters.KriteriaPresenter
import com.example.spkpm.presenters.RekomendasiPresenter
import com.example.spkpm.views.RekomendasiView
import kotlinx.android.synthetic.main.activity_add_kriteria.*
import kotlinx.android.synthetic.main.activity_add_kriteria.btn_batal
import kotlinx.android.synthetic.main.activity_add_kriteria.btn_save
import kotlinx.android.synthetic.main.activity_add_rekomendasi.*
import kotlinx.android.synthetic.main.toolbar.*

@Suppress("DEPRECATION")
class AddRekomendasiActivity : AppCompatActivity(), RekomendasiView {
    private var loading : ProgressDialog? = null
    private var presenter: RekomendasiPresenter? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_rekomendasi)

        // init toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.elevation = 0F
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = resources.getColor(R.color.colorWhite)

        // get passing data
        val rekomendasi_id = intent.getIntExtra("rekomendasi_id", 0)
        val rekomendasi_kode = intent.getStringExtra("rekomendasi_kode")

        // init presenter
        presenter = RekomendasiPresenter(this)
        // init progressbar
        loading = ProgressDialog(this)

        // check id
        if(rekomendasi_id == 0) {
            //
            tv_title.setText("Tambah Rekomendasi")
            btn_save.setOnClickListener{
                val rekom_kode = et_rekomendasi.text.toString()
                when{
                    rekom_kode.trim() == "" -> {
                        et_rekomendasi?.error = "This field Required!"
                    }
                    else -> {
                        presenter!!.addRekom(rekom_kode)
                    }
                }
            }
        } else {
            //
            tv_title.setText("Edit Rekomendasi")
            et_rekomendasi_id.setText(rekomendasi_id.toString())
            et_rekomendasi.setText(rekomendasi_kode)
            btn_save.setOnClickListener {
                val rekom_id = et_rekomendasi_id.text.toString().toInt()
                val rekom_kode = et_rekomendasi.text.toString()
                when{
                    rekom_kode.trim() == "" -> {
                        et_rekomendasi?.error = "This field Required!"
                    }
                    else -> {
                        presenter!!.updateRekom(rekom_id, rekom_kode)
                    }
                }
            }
        }

        // back button
        backbutton.setOnClickListener{
            onBackPressed()
        }

        // back batal
        btn_batal.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onLoading(message: String) {
        loading?.setMessage(message)
        loading?.show()
    }

    override fun hideLoading() {
        loading?.dismiss()
    }

    override fun onSuccessGetData(data: List<RekomendasiModel>?) {
        TODO("Not yet implemented")
    }

    override fun onFailedGetData(message: String) {
        TODO("Not yet implemented")
    }

    override fun onDataNull(message: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessAdd(message: String) {
        val toast = Toast.makeText(this, message,  Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        toast.show()
        finish()
    }

    override fun onFailedAdd(message: String) {
        val toast = Toast.makeText(this, message,  Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        toast.show()
    }

    override fun onSuccessDelete(message: String) {
        TODO("Not yet implemented")
    }

    override fun onFailedDelete(message: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessUpdate(message: String) {
        val toast = Toast.makeText(this, message,  Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        toast.show()
        finish()
    }

    override fun onFailedUpdate(message: String) {
        val toast = Toast.makeText(this, message,  Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        toast.show()
    }

}