package com.example.rxjavaproject.API

import com.example.spkpm.models.KriteriaResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET("kriteria/getKriteria")
    fun getKriteria() : Observable<KriteriaResponse>

    @FormUrlEncoded
    @POST("kriteria/addKriteria")
    fun addKriteria(
        @Field("kriteria_nama") kriteria_nama: String?
    ) : Observable<KriteriaResponse>

    @FormUrlEncoded
    @POST("kriteria/deleteKriteria")
    fun deleteKriteria(
        @Field("kriteria_id") kriteria_id: Int?
    ) : Observable<KriteriaResponse>

    @FormUrlEncoded
    @POST("kriteria/updateKriteria")
    fun updateKriteria(
        @Field("kriteria_id") kriteria_id: Int?,
        @Field("kriteria_nama")  kriteria_nama: String?
    ) : Observable<KriteriaResponse>
}