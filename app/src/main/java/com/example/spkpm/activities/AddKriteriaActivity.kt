@file:Suppress("DEPRECATION")

package com.example.spkpm.activities

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.spkpm.R
import com.example.spkpm.models.KriteriaModel
import com.example.spkpm.presenters.KriteriaPresenter
import com.example.spkpm.views.KriteriaView
import kotlinx.android.synthetic.main.activity_add_kriteria.*
import kotlinx.android.synthetic.main.toolbar.*


@Suppress("DEPRECATION")
class AddKriteriaActivity : AppCompatActivity(),
    KriteriaView {
    private lateinit var presenter: KriteriaPresenter
    private var loading : ProgressDialog? = null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_kriteria)

//        val actionBar = supportActionBar
//        actionBar?.setDisplayShowHomeEnabled(true)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.elevation = 0F
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = resources.getColor(R.color.colorWhite)


        // get passing data
        val kriteria_id = intent.getIntExtra("kriteria_id", 0)
        val kriteria_nama = intent.getStringExtra("kriteria_nama")

        // init presenter
        presenter = KriteriaPresenter(this)
        // init progressbar
        loading = ProgressDialog(this)

        // proses simpan data
        if(kriteria_id == 0) {
            tv_title.setText("Tambah Kriteria")
            btn_save.setOnClickListener{
                val kriteria_nama = et_kriteria.text.toString()
                when{
                    kriteria_nama.trim() == "" -> {
                        et_kriteria?.error = "This field Required!"
                    }
                    else -> {
                        presenter.addKriteria(kriteria_nama)
                    }
                }
            }
        } else {
            tv_title.setText("Edit Kriteria")
            et_kriteria_id.setText(kriteria_id.toString())
            et_kriteria.setText(kriteria_nama)
            btn_save.setOnClickListener {
                val kriteria_id: Int = et_kriteria_id.text.toString().toInt()
                val kriteria_nama: String = et_kriteria.text.toString()
                when{
                    kriteria_nama.trim() == "" -> {
                        et_kriteria?.error = "This field Required!"
                    }
                    else -> {
                        presenter.updateKriteria(kriteria_id, kriteria_nama)
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

    override fun onSuccessGetData(data: List<KriteriaModel>?) {
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

    }

    override fun onFailedDelete(message: String) {

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