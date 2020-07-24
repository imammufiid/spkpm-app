package com.example.spkpm.ui.bobotgap

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.example.spkpm.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var progressBar: ProgressBar? = null
class BobotGapFragment : Fragment() {
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
        val root = inflater.inflate(R.layout.fragment_bobot_gap, container, false)
        val webView = root.findViewById<View>(R.id.webviewNilaiGap) as WebView
        progressBar = root.findViewById<View>(R.id.progressBar) as ProgressBar
        webView.loadUrl("http://demo-spkpm.sanscoding.com/bobot_gap")

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