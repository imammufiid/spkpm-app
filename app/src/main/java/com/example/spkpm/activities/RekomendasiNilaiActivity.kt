package com.example.spkpm.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.spkpm.R
import com.example.spkpm.adapters.ViewPagerAdapter
import com.example.spkpm.ui.rekomendasi.AkademikRekomFragment
import com.example.spkpm.ui.rekomendasi.NonakaRekomFragment
import com.example.spkpm.ui.subkriteria.CoreFragment
import com.example.spkpm.ui.subkriteria.SecondaryFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_rekomendasi_nilai.*
import kotlinx.android.synthetic.main.toolbar.*

@Suppress("DEPRECATION")
class RekomendasiNilaiActivity : AppCompatActivity() {
//    private lateinit var tab_layout: TabLayout
//    private lateinit var view_pager: ViewPager
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rekomendasi_nilai)

        // init toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        tv_title.text = "Rekomendasi Nilai"
        supportActionBar?.setDisplayShowTitleEnabled(false)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = resources.getColor(R.color.colorWhite)

        // init fab
        val fab: FloatingActionButton = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {
            //Toast.makeText(applicationContext, "Add", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, AddRekomendasiNilaiActivity::class.java)
            startActivity(intent)
        }
        setupViewPager(pagerRekomNilai)

        // back button
        backbutton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.populateFragment(AkademikRekomFragment(), "Akademik")
        adapter.populateFragment(NonakaRekomFragment(), "Non Akademik")
        viewPager.adapter = adapter
        tabs?.setupWithViewPager(viewPager)
    }

}