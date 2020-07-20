package com.example.spkpm.presenters

import android.annotation.SuppressLint
import android.util.Log
import com.example.rxjavaproject.API.ApiClient
import com.example.rxjavaproject.API.ApiInterface
import com.example.spkpm.views.SiswaView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SiswaPresenter(private val view: SiswaView) {
    @SuppressLint("CheckResult")
    fun getSiswa() {
        view.onLoading("Loading...")
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.getSiswa()
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if(it.status == 200) {
                    view.onSuccessGetData(it.result)
                } else {
                    view.onDataNull((it.message))
                }
                view.hideLoading()
            }, {
                view.onFailedGetData(it.localizedMessage)
                view.hideLoading()
            })


    }

    @SuppressLint("CheckResult")
    fun getSiswaBySwipeRefresh() {
        //view.onLoading("Loading...")
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.getSiswa()
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if(it.status == 200) {
                    view.onSuccessGetData(it.result)
                } else {
                    view.onDataNull((it.message))
                }
                //view.hideLoading()
            }, {
                view.onFailedGetData(it.localizedMessage)
                //view.hideLoading()
            })
    }

    @SuppressLint("CheckResult")
    fun addSiswa(user_nama: String?, user_email: String?, user_jurusan: String?) {
        view.onLoading("Loading...")
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.addSiswa(user_nama, user_email, user_jurusan)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if(it.status == 201) {
                    view.onSuccessAdd(it.message)
                }
                view.hideLoading()
            }, {
                view.onFailedAdd(it.localizedMessage)
                view.hideLoading()
            })
    }

    @SuppressLint("CheckResult")
    fun deleteSiswa(user_id: Int?) {
        view.onLoading("Loading...")
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.deleteSiswa(user_id)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if(it.status == 201) {
                    view.onSuccessDelete(it.message)
                    view.hideLoading()
                }
                Log.d("Response : ", it.status.toString())
            }, {
                view.onFailedDelete(it.localizedMessage)
                view.hideLoading()
            })
    }

    @SuppressLint("CheckResult")
    fun updateSiswa(user_id: Int?, user_nama: String?, user_email: String?, user_jurusan: String?) {
        view.onLoading("Loading...")
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.updateSiswa(user_id, user_nama, user_email, user_jurusan)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if(it.status == 201) {
                    view.onSuccessUpdate(it.message)
                    view.hideLoading()
                }
                Log.d("Response : ", it.status.toString())
            }, {
                view.onFailedUpdate(it.localizedMessage)
                view.hideLoading()
            })
    }
}