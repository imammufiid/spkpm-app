package com.example.rxjavaproject.API

import com.example.spkpm.responses.*
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

    @GET("subkriteria/getAllSubkriteria")
    fun getAllSubkriteria() : Observable<SubkriteriaResponse>

    @GET("subkriteria/generate_kode")
    fun getGenerateKode() : Observable<KodeSubResponse>

    @GET("rekom/getrekom")
    fun getRekomendasi() : Observable<RekomendasiResponse>

    @GET("rekomNilai/getRekomNilai/{kriteria_id}")
    fun getRekomNIlai(
        @Path("kriteria_id") kriteria_id: Int?
    ) : Observable<RekomendasiNilaiResponse>

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

    @FormUrlEncoded
    @POST("rekomNilai/addRekomNilai")
    fun addRekomNilai(
        @Field("kriteria_id") kriteria_id: Int?,
        @Field("subkriteria_id") subkriteria_id: Int?,
        @Field("rekomendasi_id") rekomendasi_id: Int?,
        @Field("rekomendasi_nilai_bobot") rekomendasi_nilai_bobot: Float?
    ) : Observable<RekomendasiNilaiResponse>

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

    @FormUrlEncoded
    @POST("rekomNilai/deleteRekomNilai")
    fun deleteRekomNilai(
        @Field("rekomendasi_nilai_id") rekomendasi_nilai_id: Int?
    ) : Observable<RekomendasiNilaiResponse>

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

    @FormUrlEncoded
    @POST("rekomNilai/updateRekomNilai")
    fun updateRekomNilai(
        @Field("rekomendasi_nilai_id") rekomendasi_nilai_id: Int?,
        @Field("kriteria_id") kriteria_id: Int?,
        @Field("subkriteria_id") subkriteria_id: Int?,
        @Field("rekomendasi_id") rekomendasi_id: Int?,
        @Field("rekomendasi_nilai_bobot") rekomendasi_nilai_bobot: Float?
    ) : Observable<RekomendasiNilaiResponse>
}