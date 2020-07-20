package com.example.spkpm.activities

import android.app.ProgressDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import com.example.spkpm.R
import com.example.spkpm.models.SiswaModel
import com.example.spkpm.presenters.SiswaPresenter
import com.example.spkpm.views.SiswaView
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_add_kriteria.*
import kotlinx.android.synthetic.main.activity_add_kriteria.btn_save
import kotlinx.android.synthetic.main.activity_add_siswa.*
import kotlinx.android.synthetic.main.toolbar.*

@Suppress("DEPRECATION")
class AddSiswaActivity : AppCompatActivity(), SiswaView {
    private lateinit var presenter: SiswaPresenter
    private var loading : ProgressDialog? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_siswa)

        // init toolbar action bar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.elevation = 0F
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = resources.getColor(R.color.colorWhite)

        // get passing data
        val user_id = intent.getIntExtra("user_id", 0)
        val user_nama = intent.getStringExtra("user_nama")
        val user_email = intent.getStringExtra("user_email")
        val user_jurusan = intent.getStringExtra("user_jurusan")

        // init presenter
        presenter = SiswaPresenter(this)
        loading = ProgressDialog(this)
        val btn_batal = findViewById<View>(R.id.btn_batal) as MaterialButton
        // set
        if(user_id == 0) {
            tv_title.text = "Tambah Siswa"
            btn_save.setOnClickListener{
                val user_nama = et_user_nama.text.toString()
                val user_email = et_user_email.text.toString()
                val user_jurusan = et_user_jurusan.text.toString()
                when{
                    user_nama.trim() == "" -> {
                        et_user_nama?.error = "This field Required!"
                    }
                    user_email.trim() == "" -> {
                        et_user_email?.error = "This field Required!"
                    }
                    user_jurusan.trim() == "" -> {
                        et_user_jurusan?.error = "This field Required!"
                    }
                    else -> {
                        presenter.addSiswa(user_nama, user_email, user_jurusan)
                    }
                }
            }
        } else {
            tv_title.text = "Edit Siswa"
            et_user_id.setText(user_id.toString())
            et_user_nama.setText(user_nama.toString())
            et_user_email.setText(user_email.toString())
            et_user_jurusan.setText(user_jurusan.toString())
            btn_save.setOnClickListener {
                val user_id: Int = et_user_id.text.toString().toInt()
                val user_nama: String = et_user_nama.text.toString()
                val user_email: String = et_user_email.text.toString()
                val user_jurusan: String = et_user_jurusan.text.toString()
                when{
                    user_nama.trim() == "" -> {
                        et_user_nama?.error = "This field Required!"
                    }
                    user_email.trim() == "" -> {
                        et_user_email?.error = "This field Required!"
                    }
                    user_jurusan.trim() == "" -> {
                        et_user_jurusan?.error = "This field Required!"
                    }
                    else -> {
                        presenter.updateSiswa(user_id, user_nama, user_email, user_jurusan)
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

    override fun onSuccessGetData(data: List<SiswaModel>?) {
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