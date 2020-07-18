package com.example.spkpm.presenters

import android.annotation.SuppressLint
import com.example.rxjavaproject.API.ApiClient
import com.example.rxjavaproject.API.ApiInterface
import com.example.spkpm.views.RekomendasiView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RekomendasiPresenter(private val view: RekomendasiView) {
    @SuppressLint("CheckResult")
    fun getRekomendasi() {
        view.onLoading("Loading...")
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.getRekomendasi()
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
    fun getRekomendasiSwipeRefresh() {
        view.onLoading("Loading...")
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.getRekomendasi()
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
    fun addRekom(rekomendasi_kode: String) {
        view.onLoading("Loading...")
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.addRekomendasi(rekomendasi_kode)
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
    fun updateRekom(rekom_id: Int?, rekom_kode: String?) {
        view.onLoading("Loading...")
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.updateRekomendasi(rekom_id, rekom_kode)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if(it.status == 201) {
                    view.onSuccessUpdate(it.message)
                }
                view.hideLoading()
            }, {
                view.onFailedUpdate(it.localizedMessage)
                view.hideLoading()
            })
    }

    @SuppressLint("CheckResult")
    fun deleteRekom(rekom_id: Int?) {
        view.onLoading("Loading...")
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.deleteRekomendasi(rekom_id)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if(it.status == 201) {
                    view.onSuccessDelete(it.message)
                }
                view.hideLoading()
            }, {
                view.onFailedDelete(it.localizedMessage)
                view.hideLoading()
            })
    }

}