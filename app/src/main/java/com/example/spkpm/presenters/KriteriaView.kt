package com.example.spkpm.presenters

import com.example.spkpm.models.KriteriaModel

interface KriteriaView {
    // LOADING
    fun onLoading(message: String)
    fun hideLoading()
    // GET
    fun onSuccessGetData(data: List<KriteriaModel>?)
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
}