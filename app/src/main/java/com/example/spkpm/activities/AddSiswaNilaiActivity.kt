package com.example.spkpm.activities

import android.app.ProgressDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spkpm.R
import com.example.spkpm.api.CheckConnection
import com.example.spkpm.models.KriteriaModel
import com.example.spkpm.models.SiswaNilaiModel
import com.example.spkpm.models.SubkriteriaModel
import com.example.spkpm.presenters.SiswaNilaiPresenter
import com.example.spkpm.views.SiswaNilaiView
import kotlinx.android.synthetic.main.activity_add_siswa_nilai.*
import kotlinx.android.synthetic.main.activity_add_siswa_nilai.btn_batal
import kotlinx.android.synthetic.main.activity_add_siswa_nilai.btn_save
import kotlinx.android.synthetic.main.activity_add_subkriteria.*
import kotlinx.android.synthetic.main.toolbar.*

@Suppress("DEPRECATION")
class AddSiswaNilaiActivity : AppCompatActivity(), SiswaNilaiView {
    private lateinit var presenter: SiswaNilaiPresenter
    private lateinit var checkConnection: CheckConnection
    private var loading : ProgressDialog? = null
    //private var user_id: Int? = null
    private var subkriteria_id: Int? = null
    val mContext = this
    val spinnerSubkriteria: MutableList<String> = ArrayList()
    val idSubkriteria: MutableList<Int> = ArrayList()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_siswa_nilai)

        // init toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        tv_title.text = "Tambah Nilai Siswa"
        supportActionBar?.setDisplayShowTitleEnabled(false)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = resources.getColor(R.color.colorWhite)

        // get
        val user_id = intent.getIntExtra("user_id", 0)
        val nilai_id = intent.getIntExtra("nilai_id", 0)
        val siswa_nilai = intent.getIntExtra("siswa_nilai", 0)
        //val subkriteria_id = intent.getIntExtra("subkriteria_id", 0)

        // init
        presenter = SiswaNilaiPresenter(this)
        loading = ProgressDialog(this)
        presenter.getSpinnerSubkriteria()

        // back button
        backbutton.setOnClickListener {
            onBackPressed()
        }

        btn_batal.setOnClickListener {
            onBackPressed()
        }

        spinner_subkriteria.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                subkriteria_id = idSubkriteria[position]
            }

        }

        if(nilai_id == 0) {
            tv_title.text = "Tambah Nilai Siswa"
            btn_save.setOnClickListener {
                val siswa_nilai = et_siswa_nilai.text.toString().toInt()
                when {
                    siswa_nilai.equals("") -> {
                        et_siswa_nilai.error = "This field Required!"
                    }
                    else -> {
                        presenter.addSiswaNilai(user_id, subkriteria_id, siswa_nilai)
                    }
                }
            }
        } else {
            tv_title.text = "Edit Nilai Siswa"
            et_siswa_nilai.setText(siswa_nilai.toString())
            btn_save.setOnClickListener {
                val siswa_nilai = et_siswa_nilai.text.toString().toInt()
                when{
                    siswa_nilai.equals("") -> {
                        et_siswa_nilai.error = "This field Required!"
                    }
                    else -> {
                        presenter.updateSiswaNilai(nilai_id, user_id, subkriteria_id, siswa_nilai)
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

    override fun onSuccessGetData(data: List<SiswaNilaiModel>?) {
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

    override fun onSuccessSpinner(data: List<SubkriteriaModel>) {
        val allSubkriteria: List<SubkriteriaModel> = data

        for (i in allSubkriteria.indices) {
            spinnerSubkriteria.add(allSubkriteria[i].subkriteria_nama.toString())
            idSubkriteria.add(allSubkriteria[i].subkriteria_id!!)
        }


        val adapter = ArrayAdapter(
            mContext,
            android.R.layout.simple_spinner_item, spinnerSubkriteria
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_subkriteria.adapter = adapter
    }

    override fun onFailedSpinner(message: String) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
}