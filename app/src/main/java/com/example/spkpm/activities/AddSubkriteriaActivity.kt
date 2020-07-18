package com.example.spkpm.activities

import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.spkpm.R
import com.example.spkpm.models.KriteriaModel
import com.example.spkpm.models.SubkriteriaModel
import com.example.spkpm.presenters.SubkriteriaPresenter
import com.example.spkpm.views.SubkriteriaView
import kotlinx.android.synthetic.main.activity_add_subkriteria.*
import kotlinx.android.synthetic.main.activity_add_subkriteria.btn_batal
import kotlinx.android.synthetic.main.activity_add_subkriteria.btn_save
import kotlinx.android.synthetic.main.toolbar.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "DEPRECATION")
class AddSubkriteriaActivity : AppCompatActivity(), SubkriteriaView {
    // declare
    private lateinit var presenter: SubkriteriaPresenter
    private var loading: ProgressDialog? = null
    val mContext = this
    val listSpinner: MutableList<String> = ArrayList()
    val idKriteria: MutableList<Int> = ArrayList()
    private var id_kriteria: Int? = null
    private var radio_factor: RadioGroup? = null
    private var cfsf: String? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_subkriteria)
        // toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.elevation = 0F
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = resources.getColor(R.color.colorWhite)

        // get passing data
        val sub_id= intent.getIntExtra("subkriteria_id", 0)
        val sub_kode = intent.getStringExtra("subkriteria_kode")
        val sub_nama = intent.getStringExtra("subkriteria_nama")
        val sub_ket = intent.getStringExtra("subkriteria_keterangan")
        val k_id = intent.getIntExtra("kriteria_id", 0)

        // init
        presenter = SubkriteriaPresenter(this)
        presenter.getSpinnerKriteria()

        // init progressbar
        loading = ProgressDialog(this)

        // back button
        backbutton.setOnClickListener{
            onBackPressed()
        }

        // back batal
        btn_batal.setOnClickListener {
            onBackPressed()
        }

        // spinner onChange
        spinner.onItemSelectedListener =  object :
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
                //val selectedName: String = parent?.getItemAtPosition(position).toString()
                id_kriteria = idKriteria[position]
                //Toast.makeText(applicationContext, id.toString(), Toast.LENGTH_SHORT).show()
            }

        }

        radio_factor = findViewById<View>(R.id.radio_cfsf) as RadioGroup?
        radio_factor?.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            cfsf = radio.text.toString()
        })

        // simpan data
        if(sub_id == 0) {
            presenter.getGenerateKode()
            tv_title.text = "Tambah SubKriteria"
            btn_save.setOnClickListener {
                val kode_sub = et_subkriteria_kode.text.toString()
                val nama_sub = et_subkriteria_nama.text.toString()

                when{
                    kode_sub.trim() == "" -> {
                        et_subkriteria_kode?.error = "This field Required!"
                        et_subkriteria_kode?.requestFocus()
                    }
                    nama_sub.trim() == "" -> {
                        et_subkriteria_nama?.error = "This field Required!"
                        et_subkriteria_nama?.requestFocus()
                    }
                    radio_factor?.checkedRadioButtonId!! <= 0 -> {
                        radio_core.error = "This field Required!"
                    }
                    else -> {
                        presenter.addSubKriteria(
                            kode_sub, nama_sub, id_kriteria, cfsf
                        )
                    }
                }
            }
        } else {
            tv_title.text = "Edit SubKriteria"
            tvSubkriteriaId.setText(sub_id.toString())
            et_subkriteria_kode.setText(sub_kode)
            et_subkriteria_nama.setText(sub_nama)
            if(sub_ket == "Core") {
                radio_factor?.check(R.id.radio_core)
            } else {
                radio_factor?.check(R.id.radio_secondary)
            }
            btn_save.setOnClickListener {
                val sub_id = tvSubkriteriaId.text.toString().toInt()
                val sub_kode = et_subkriteria_kode.text.toString()
                val sub_nama = et_subkriteria_nama.text.toString()
                when {
                    sub_kode.trim() == "" -> {
                        et_subkriteria_kode?.error = "This field Required!"
                        et_subkriteria_kode?.requestFocus()
                    }
                    sub_nama.trim() == "" -> {
                        et_subkriteria_nama?.error = "This field Required!"
                        et_subkriteria_nama?.requestFocus()
                    }
                    radio_factor?.checkedRadioButtonId!! <= 0 -> {
                        radio_core.error = "This field Required!"
                    }
                    else -> {
                        presenter.updateSubkriteria(sub_id, sub_kode, sub_nama, id_kriteria, cfsf)
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

    override fun onSuccessGetData(data: List<SubkriteriaModel>?) {
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

    override fun onSuccessSpinner(data: List<KriteriaModel>) {
        val allKriteria: List<KriteriaModel> = data

        for (i in allKriteria.indices) {
            listSpinner.add(allKriteria[i].kriteria_nama.toString())
            idKriteria.add(allKriteria[i].kriteria_id!!)
        }

        val adapter = ArrayAdapter(
            mContext,
            android.R.layout.simple_spinner_item, listSpinner
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    override fun onFailedSpinner(message: String) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    override fun onSuccessGenerate(kode: String) {
        et_subkriteria_kode.setText(kode)
        et_subkriteria_kode.isEnabled = false
    }

    override fun onFailedGenerate(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        et_subkriteria_kode.isEnabled = true
    }
}




