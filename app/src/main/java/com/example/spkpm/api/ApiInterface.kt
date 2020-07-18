package com.example.rxjavaproject.API

import com.example.spkpm.responses.KodeSubResponse
import com.example.spkpm.responses.KriteriaResponse
import com.example.spkpm.responses.RekomendasiResponse
import com.example.spkpm.responses.SubkriteriaResponse
import io.reactivex.Observable
import retrofit2.http.*

interface ApiInterface {

    // GET DATA /////////////////////////////////////////////////
    @GET("kriteria/getKriteria")
    fun getKriteria() : Observable<KriteriaResponse>

    @GET("subkriteria/getSubkriteria/{kriteria_id}")
    fun getSubkriteria(
        @Path("kriteria_id") kriteria_id: Int?
    ) : Observable<SubkriteriaResponse>

    @GET("subkriteria/generate_kode")
    fun getGenerateKode() : Observable<KodeSubResponse>

    @GET("rekom/getrekom")
    fun getRekomendasi() : Observable<RekomendasiResponse>

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

    @FormUrlEncoded
    @POST("rekom/addRekom")
    fun addRekomendasi(
        @Field("rekomendasi_kode") rekomendasi_kode: String?
    ) : Observable<RekomendasiResponse>

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

    @FormUrlEncoded
    @POST("rekom/deleteRekom")
    fun deleteRekomendasi(
        @Field("rekomendasi_id") rekomendasi_id: Int?
    ) : Observable<RekomendasiResponse>

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

    @FormUrlEncoded
    @POST("rekom/updateRekom")
    fun updateRekomendasi(
        @Field("rekomendasi_id") rekomendasi_id: Int?,
        @Field("rekomendasi_kode")  rekomendasi_kode: String?
    ) : Observable<RekomendasiResponse>
}