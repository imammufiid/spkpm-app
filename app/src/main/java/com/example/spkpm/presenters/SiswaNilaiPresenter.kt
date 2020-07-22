package com.example.spkpm.presenters

import android.annotation.SuppressLint
import android.util.Log
import com.example.rxjavaproject.API.ApiClient
import com.example.rxjavaproject.API.ApiInterface
import com.example.spkpm.views.SiswaNilaiView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SiswaNilaiPresenter(private val view: SiswaNilaiView) {
    @SuppressLint("CheckResult")
    fun getSiswaNilai(user_id: Int?) {
        view.onLoading("Loading...")
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.getSiswaNilai(user_id)
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
    fun addSiswaNilai(user_id: Int?, subkriteria_id: Int?, siswa_nilai: Int?) {
        view.onLoading("Loading...")
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.addSiswaNilai(user_id, subkriteria_id, siswa_nilai)
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
    fun deleteSiswaNilai(nilai_id: Int?) {
        view.onLoading("Loading...")
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.deleteSiswaNilai(nilai_id)
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
    fun updateSiswaNilai(nilai_id: Int?, user_id: Int?, subkriteria_id: Int?, siswa_nilai: Int?) {
        view.onLoading("Loading...")
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.updateSiswaNilai(nilai_id, user_id, subkriteria_id, siswa_nilai)
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

    @SuppressLint("CheckResult")
    fun getSpinnerSubkriteria() {
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.getAllSubkriteria()
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if(it.status == 200) {
                    view.onSuccessSpinner(it.result)
                } else {
                    view.onFailedSpinner(it.message)
                }

            }, {
                view.onFailedSpinner(it.localizedMessage)
            })
    }
}