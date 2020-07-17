package com.example.spkpm.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class CheckConnection {
    fun checkConnection(context: Context?): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }
}