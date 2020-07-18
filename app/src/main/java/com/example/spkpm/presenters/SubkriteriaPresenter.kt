package com.example.spkpm.presenters

import android.annotation.SuppressLint
import android.util.Log
import com.example.rxjavaproject.API.ApiClient
import com.example.rxjavaproject.API.ApiInterface
import com.example.spkpm.ui.subkriteria.CoreFragment
import com.example.spkpm.views.SubkriteriaView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add_subkriteria.*

class SubkriteriaPresenter(private val view: SubkriteriaView) {
    @SuppressLint("CheckResult")
    fun getDataCore() {
        view.onLoading("Loading...")
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.getSubkriteria(1)
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
    fun getDataCoreBySwipeRefresh() {
        //view.onLoading("Loading...")
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.getSubkriteria(1)
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
    fun getDataSecondary() {
        view.onLoading("Loading...")
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.getSubkriteria(2)
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
    fun getDataSecondaryBySwipeRefresh() {
        //view.onLoading("Loading...")
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.getSubkriteria(2)
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
    fun getSpinnerKriteria() {
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.getKriteria()
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

    @SuppressLint("CheckResult")
    fun getGenerateKode() {
        view.onLoading("Tunggu sebentar....")
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.getGenerateKode()
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if(it.status == 200) {
                    view.onSuccessGenerate(it.result)
                } else {
                    view.onFailedGenerate(it.message)
                }
                view.hideLoading()

            }, {
                view.onFailedGenerate(it.localizedMessage)
                view.hideLoading()
            })
    }

    @SuppressLint("CheckResult")
    fun addSubKriteria(subkriteria_kode: String?, subkriteria_nama: String?, kriteria_id: Int?, subkriteria_keterangan: String?) {
        view.onLoading("Menambahkan data....")
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.addSubkriteria(subkriteria_kode, subkriteria_nama, kriteria_id, subkriteria_keterangan)
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
    fun deleteSubkriteria(subkriteria_id: Int?) {
        view.onLoading("Loading...")
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.deleteSubkriteria(subkriteria_id)
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
    fun updateSubkriteria(subkriteria_id: Int?, subkriteria_kode: String?, subkriteria_nama: String?, kriteria_id: Int?, subkriteria_keterangan: String?) {
        view.onLoading("Loading...")
        val apiInterface = ApiClient.getRetrofit().create(ApiInterface::class.java)
        apiInterface.updateSubkriteria(subkriteria_id, subkriteria_kode, subkriteria_nama, kriteria_id, subkriteria_keterangan)
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