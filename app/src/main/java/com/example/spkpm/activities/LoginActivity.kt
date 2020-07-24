package com.example.spkpm.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.spkpm.MainActivity
import com.example.spkpm.R
import kotlinx.android.synthetic.main.activity_login.*

@Suppress("DEPRECATION")
class LoginActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = resources.getColor(R.color.colorWhite)
//        val etusername = findViewById<View>(R.id.et_username) as EditText
//        val etpassword = findViewById<View>(R.id.et_password) as EditText

        val username = et_username.text.toString()
        val password = et_password.text.toString()
        btn_login.setOnClickListener {
//            Toast.makeText(this, "$username $password", Toast.LENGTH_SHORT).show()
//            if(username.trim() == "admin" && password.trim() == "admin") {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
//            } else {
//                Toast.makeText(this, "Gagal Login!", Toast.LENGTH_SHORT).show()
//            }
        }
    }
}