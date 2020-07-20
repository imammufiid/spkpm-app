package com.example.spkpm.presenters

import android.annotation.SuppressLint
import com.example.rxjavaproject.API.ApiClient
import com.example.rxjavaproject.API.ApiInterface
import com.example.spkpm.views.RekomendasiNilaiView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RekomendasiNilaiPresenter(private val view: RekomendasiNilaiView) {
    @SuppressLint("CheckResult")
    fun getRekomNilai(kriteria_id: Int?) {
        view.onLoading("Loading...")
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.getRekomNIlai(kriteria_id)
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
    fun getRekomNilaiBySwipeRefresh(kriteria_id: Int?) {
        //view.onLoading("Loading...")
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.getRekomNIlai(kriteria_id)
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
    fun deleteRekomNilai(rekomendasi_nilai_id: Int?) {
        view.onLoading("Menghapus data...")
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.deleteRekomNilai(rekomendasi_nilai_id)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if(it.status == 201) {
                    view.onSuccessDelete(it.message)
                } else {
                    view.onFailedDelete((it.message))
                }
                view.hideLoading()
            }, {
                view.onFailedDelete(it.localizedMessage)
                view.hideLoading()
            })
    }

    @SuppressLint("CheckResult")
    fun getSpinnerKriteria() {
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.getKriteria()
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if(it.status == 200) {
                    view.onSuccessSpinnerKriteria(it.result)
                } else {
                    view.onFailedSpinnerKriteria(it.message)
                }

            }, {
                view.onFailedSpinnerKriteria(it.localizedMessage)
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
                    view.onSuccessSpinnerSubkriteria(it.result)
                } else {
                    view.onFailedSpinnerSubkriteria(it.message)
                }

            }, {
                view.onFailedSpinnerSubkriteria(it.localizedMessage)
            })
    }

    @SuppressLint("CheckResult")
    fun getSpinnerRekomendasi() {
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.getRekomendasi()
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if(it.status == 200) {
                    view.onSuccessSpinnerRekomendasi(it.result)
                } else {
                    view.onFailedSpinnerRekomendasi(it.message)
                }

            }, {
                view.onFailedSpinnerRekomendasi(it.localizedMessage)
            })
    }

    @SuppressLint("CheckResult")
    fun addRekomNilai(kriteria_id: Int?, subkriteria_id: Int?, rekomendasi_id: Int?, rekomendasi_nilai_bobot: Float?) {
        view.onLoading("Loading...")
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.addRekomNilai(kriteria_id, subkriteria_id, rekomendasi_id, rekomendasi_nilai_bobot)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if(it.status == 201) {
                    view.onSuccessAdd(it.message)
                } else {
                    view.onFailedAdd(it.message)
                }
                view.hideLoading()

            }, {
                view.onFailedAdd(it.localizedMessage)
                view.hideLoading()
            })
    }

    @SuppressLint("CheckResult")
    fun updateRekomNilai(rekomendasi_nilai_id: Int?, kriteria_id: Int?, subkriteria_id: Int?, rekomendasi_id: Int?, rekomendasi_nilai_bobot: Float?) {
        view.onLoading("Loading...")
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.updateRekomNilai(rekomendasi_nilai_id, kriteria_id, subkriteria_id, rekomendasi_id, rekomendasi_nilai_bobot)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if(it.status == 201) {
                    view.onSuccessUpdate(it.message)
                } else {
                    view.onFailedUpdate(it.message)
                }
                view.hideLoading()

            }, {
                view.onFailedUpdate(it.localizedMessage)
                view.hideLoading()
            })
    }
}