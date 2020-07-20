package com.example.spkpm.views

import com.example.spkpm.models.KriteriaModel
import com.example.spkpm.models.RekomendasiModel
import com.example.spkpm.models.RekomendasiNilaiModel
import com.example.spkpm.models.SubkriteriaModel

interface RekomendasiNilaiView {
    // LOADING
    fun onLoading(message: String)
    fun hideLoading()

    // GET
    fun onSuccessGetData(data: List<RekomendasiNilaiModel>?)
    fun onFailedGetData(message: String)
    fun onDataNull(message: String)

    // POST
    fun onSuccessAdd(message: String)
    fun onFailedAdd(message: String)

    // DELETE
    fun onSuccessDelete(message: String)
    fun onFailedDelete(message: String)

    // DELETE
    fun onSuccessUpdate(message: String)
    fun onFailedUpdate(message: String)

    // SET SPINNER
    fun onSuccessSpinnerKriteria(data: List<KriteriaModel>)
    fun onSuccessSpinnerSubkriteria(data: List<SubkriteriaModel>)
    fun onSuccessSpinnerRekomendasi(data: List<RekomendasiModel>)
    fun onFailedSpinnerKriteria(message: String)
    fun onFailedSpinnerSubkriteria(message: String)
    fun onFailedSpinnerRekomendasi(message: String)
}