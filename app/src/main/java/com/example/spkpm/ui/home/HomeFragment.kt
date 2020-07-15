package com.example.spkpm.ui.home

import android.content.Context
import android.content.pm.ApplicationInfo
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
//import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.example.spkpm.R


@Suppress("DEPRECATION")
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        if(!checkConnection()) {
            val toast = Toast.makeText(context, "Please Check your Connection", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER_VERTICAL , 0, 0)
            toast.show()
        }
        return root
    }

    private fun checkConnection() : Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }

//    private fun isConnected(): Boolean {
//        var connected = false
//        try{
//            val connectivity =
//                ApplicationProvider.getApplicationContext()
//                    .getSystemService(Context.CONNECTIVITY_SERVICE)
//
//            val nInfo: NetworkInfo = connectivity.getActiveNetworkInfo()
//            connected = nInfo != null && nInfo.isAvailable && nInfo.isConnected
//            return connected
//        } catch (e: Exception) {
//            Log.e("Connectivity Connection", e.message)
//        }
//        return connected
//    }
}