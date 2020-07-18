package com.example.rxjavaproject.API

import com.example.spkpm.responses.KodeSubResponse
import com.example.spkpm.responses.KriteriaResponse
import com.example.spkpm.responses.SubkriteriaResponse
import io.reactivex.Observable
import retrofit2.http.*

interface ApiInterface {

    // GET DATA /////////////////////////////////////////////////
    @GET("kriteria/getKriteria")
    fun getKriteria() : Observable<KriteriaResponse>

    @GET("subkriteria/getSubkriteria/{faktor}")
    fun getSubkriteria(
        @Path("faktor") faktor: String?
    ) : Observable<SubkriteriaResponse>

    @GET("subkriteria/generate_kode")
    fun getGenerateKode() : Observable<KodeSubResponse>

    // ADD DATA /////////////////////////////////////////////////
    @FormUrlEncoded
    @POST("kriteria/addKriteria")
    fun addKriteria(
        @Field("kriteria_nama") kriteria_nama: String?
    ) : Observable<KriteriaResponse>

    @FormUrlEncoded
    @POST("subkriteria/addSubkriteria")
    fun addSubkriteria(
        @Field("subkriteria_kode") kriteria_kode: String?,
        @Field("subkriteria_nama") subkriteria_nama: String?,
        @Field("kriteria_id") kriteria_id: Int?,
        @Field("subkriteria_keterangan") subkriteria_keterangan: String?
    ) : Observable<SubkriteriaResponse>

    // DELETE DATA /////////////////////////////////////////////////
    @FormUrlEncoded
    @POST("kriteria/deleteKriteria")
    fun deleteKriteria(
        @Field("kriteria_id") kriteria_id: Int?
    ) : Observable<KriteriaResponse>

    @FormUrlEncoded
    @POST("subkriteria/deleteSubkriteria")
    fun deleteSubkriteria(
        @Field("subkriteria_id") subkriteria_id: Int?
    ) : Observable<SubkriteriaResponse>

    // UPDATE DATA /////////////////////////////////////////////////
    @FormUrlEncoded
    @POST("kriteria/updateKriteria")
    fun updateKriteria(
        @Field("kriteria_id") kriteria_id: Int?,
        @Field("kriteria_nama")  kriteria_nama: String?
    ) : Observable<KriteriaResponse>

    @FormUrlEncoded
    @POST("subkriteria/updateSubkriteria")
    fun updateSubkriteria(
        @Field("subkriteria_id") skriteria_id: Int?,
        @Field("subkriteria_kode")  subkriteria_kode: String?,
        @Field("subkriteria_nama")  subkriteria_nama: String?,
        @Field("kriteria_id") kriteria_id: Int?,
        @Field("subkriteria_keterangan") subkriteria_keterangan: String?
    ) : Observable<SubkriteriaResponse>
}