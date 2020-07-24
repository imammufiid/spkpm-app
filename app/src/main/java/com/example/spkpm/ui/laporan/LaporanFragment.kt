@file:Suppress("DEPRECATION")

package com.example.spkpm.ui.laporan

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.example.spkpm.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var progressBar: ProgressBar? = null

class LaporanFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_laporan, container, false)
        val webView = root.findViewById<View>(R.id.webviewLaporan) as WebView
        progressBar = root.findViewById<View>(R.id.progressBar) as ProgressBar
        webView.loadUrl("http://demo-spkpm.sanscoding.com/laporan")

        val webViewSet = webView.settings
        webViewSet.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        return root
    }

    class WebViewClient : android.webkit.WebViewClient() {

        override fun shouldOverrideUrlLoading(
            view: WebView,
            url: String
        ): Boolean {
            view.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            //loading?.dismiss()
            progressBar?.visibility = View.GONE
        }
    }

}