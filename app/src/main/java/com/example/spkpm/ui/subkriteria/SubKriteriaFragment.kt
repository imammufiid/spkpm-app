package com.example.spkpm.ui.subkriteria

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import com.example.spkpm.R
import com.example.spkpm.activities.AddSubkriteriaActivity
import com.example.spkpm.adapters.SubkriteriaAdapter
import com.example.spkpm.adapters.ViewPagerAdapter
import com.example.spkpm.models.KriteriaModel
import com.example.spkpm.models.SubkriteriaModel
import com.example.spkpm.presenters.SubkriteriaPresenter
import com.example.spkpm.views.SubkriteriaView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_subkriteria.*

@Suppress("DEPRECATION")
class SubKriteriaFragment : Fragment() {

    private lateinit var slideshowViewModel: SubkriteriaViewModel
    private var myCtx: FragmentActivity? =null
    private var myFragment: FragmentManager? = null
    private var tabs_subkriteria: TabLayout? = null
    private var myPager: ViewPager? = null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SubkriteriaViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_subkriteria, container, false)
        myFragment = myCtx?.supportFragmentManager
        // init
        myPager = root.findViewById<View>(R.id.myViewPager) as ViewPager?
        tabs_subkriteria = root.findViewById<View>(R.id.tabs_subkriteria) as? TabLayout
        tabs_subkriteria?.visibility = View.VISIBLE
        // init fab
        val fab: FloatingActionButton = root.findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {
            val intent = Intent(context, AddSubkriteriaActivity::class.java)
            startActivity(intent)
        }
        myPager?.let { setupViewPager(it) }
        return root
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.populateFragment(CoreFragment(), "Core")
        adapter.populateFragment(SecondaryFragment(), "Secondary")
        viewPager.adapter = adapter
        tabs_subkriteria?.setupWithViewPager(viewPager)
    }

}