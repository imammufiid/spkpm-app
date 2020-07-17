package com.example.spkpm.views

import com.example.spkpm.models.KriteriaModel
import com.example.spkpm.models.SubkriteriaModel

interface SubkriteriaView {
    // LOADING
    fun onLoading(message: String)
    fun hideLoading()
    // GET
    fun onSuccessGetData(data: List<SubkriteriaModel>?)
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
    fun onSuccessSpinner(data: List<KriteriaModel>)
    fun onFailedSpinner(message: String)

    // SET KODE
    fun onSuccessGenerate(kode: String)
    fun onFailedGenerate(message: String)
}