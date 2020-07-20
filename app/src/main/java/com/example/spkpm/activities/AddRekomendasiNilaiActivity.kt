package com.example.spkpm.activities

import android.app.ProgressDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import com.example.spkpm.R
import com.example.spkpm.models.KriteriaModel
import com.example.spkpm.models.RekomendasiModel
import com.example.spkpm.models.RekomendasiNilaiModel
import com.example.spkpm.models.SubkriteriaModel
import com.example.spkpm.presenters.RekomendasiNilaiPresenter
import com.example.spkpm.views.RekomendasiNilaiView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.activity_add_rekomendasi.*
import kotlinx.android.synthetic.main.activity_add_rekomendasi_nilai.*
import kotlinx.android.synthetic.main.activity_add_subkriteria.*
import kotlinx.android.synthetic.main.activity_add_subkriteria.btn_batal
import kotlinx.android.synthetic.main.toolbar.*

@Suppress("DEPRECATION")
class AddRekomendasiNilaiActivity : AppCompatActivity(), RekomendasiNilaiView {

    private lateinit var presenter: RekomendasiNilaiPresenter
    private var loading: ProgressDialog? = null
    val mContext = this
    val kriteria: MutableList<String> = ArrayList()
    val idKriteria: MutableList<Int> = ArrayList()
    val subkriteria: MutableList<String> = ArrayList()
    val idSubkriteria: MutableList<Int> = ArrayList()
    val rekomendasi: MutableList<String> = ArrayList()
    val idRekomendasi: MutableList<Int> = ArrayList()
    private var id_kriteria: Int? = null
    private var id_subkriteria: Int? = null
    private var id_rekomendasi: Int? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_rekomendasi_nilai)

        // toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.elevation = 0F
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = resources.getColor(R.color.colorWhite)

        // passing data
        val rekomendasi_nilai_id = intent.getIntExtra("rekomendasi_nilai_id", 0)
        val rekomendasi_nilai_bobot = intent.getFloatExtra("rekomendasi_nilai_bobot", 0.0F)

        // init presenter
        presenter = RekomendasiNilaiPresenter(this)
        presenter.getSpinnerKriteria()
        presenter.getSpinnerSubkriteria()
        presenter.getSpinnerRekomendasi()

        // init
        val btn_save = findViewById<View>(R.id.btn_save) as MaterialButton
        val btn_cancel = findViewById<View>(R.id.btn_batal) as MaterialButton
        val tv_rekom_nilai_id = findViewById<View>(R.id.rekomendasi_nilai_id) as MaterialTextView

        // init progressbar
        loading = ProgressDialog(this)

        // back button
        backbutton.setOnClickListener{
            onBackPressed()
        }

        // back batal
        btn_cancel.setOnClickListener {
            onBackPressed()
        }

        // spinner onChange
        spinner_kriteria.onItemSelectedListener =  object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                id_kriteria = idKriteria[position]
                //Toast.makeText(applicationContext, id_kriteria.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        spinner_subkriteria.onItemSelectedListener =  object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                id_subkriteria = idSubkriteria[position]
                //Toast.makeText(applicationContext, id_subkriteria.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        spinner_rekomendasi.onItemSelectedListener =  object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                id_rekomendasi = idRekomendasi[position]
                //Toast.makeText(applicationContext, id_rekomendasi.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        if(rekomendasi_nilai_id == 0) {
            tv_title.text = "Tambah Rekomendasi Nilai"
            btn_save.setOnClickListener{
                val rekom_nilai_bobot = et_bobot_nilai.text.toString().toFloat()
                when{
                    rekom_nilai_bobot.equals("") -> {
                        et_bobot_nilai.error = "This field Required!"
                    }
                    else -> {
                        presenter.addRekomNilai(id_kriteria, id_subkriteria, id_rekomendasi, rekom_nilai_bobot)
                    }
                }
            }
        } else {
            tv_title.text = "Edit Rekomendasi Nilai"
            tv_rekom_nilai_id.setText(rekomendasi_nilai_id.toString())
            et_bobot_nilai.setText(rekomendasi_nilai_bobot.toString())
            btn_save.setOnClickListener {
                val rekom_nilai_id = tv_rekom_nilai_id.text.toString().toInt()
                val rekom_nilai_bobot = et_bobot_nilai.text.toString().toFloat()
                when {
                    rekom_nilai_bobot.equals("") -> {
                        et_bobot_nilai.error = "This field Required!"
                    }
                    else -> {
                        presenter.updateRekomNilai(rekom_nilai_id, id_kriteria, id_subkriteria, id_rekomendasi, rekom_nilai_bobot)
                    }
                }
            }
        }
    }

    override fun onLoading(message: String) {
        loading?.setMessage(message)
        loading?.show()
    }

    override fun hideLoading() {
        loading?.dismiss()
    }

    override fun onSuccessGetData(data: List<RekomendasiNilaiModel>?) {
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

    override fun onSuccessSpinnerKriteria(data: List<KriteriaModel>) {
        val allKriteria: List<KriteriaModel> = data

        for (i in allKriteria.indices) {
            kriteria.add(allKriteria[i].kriteria_nama.toString())
            idKriteria.add(allKriteria[i].kriteria_id!!)
        }

        val adapter = ArrayAdapter(
            mContext,
            android.R.layout.simple_spinner_item, kriteria
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_kriteria.adapter = adapter
    }

    override fun onSuccessSpinnerSubkriteria(data: List<SubkriteriaModel>) {
        val allSubkriteria: List<SubkriteriaModel> = data
        for (i in allSubkriteria.indices) {
            subkriteria.add(allSubkriteria[i].subkriteria_nama.toString())
            idSubkriteria.add(allSubkriteria[i].subkriteria_id!!)
        }

        val adapter = ArrayAdapter(
            mContext,
            android.R.layout.simple_spinner_item, subkriteria
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_subkriteria.adapter = adapter
    }

    override fun onSuccessSpinnerRekomendasi(data: List<RekomendasiModel>) {
        val allRekomendasi: List<RekomendasiModel> = data
        for (i in allRekomendasi.indices) {
            rekomendasi.add(allRekomendasi[i].rekomendasi_kode.toString())
            idRekomendasi.add(allRekomendasi[i].rekomendasi_id!!)
        }

        val adapter = ArrayAdapter(
            mContext,
            android.R.layout.simple_spinner_item, rekomendasi
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_rekomendasi.adapter = adapter
    }

    override fun onFailedSpinnerKriteria(message: String) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun onFailedSpinnerSubkriteria(message: String) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun onFailedSpinnerRekomendasi(message: String) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
    }
}