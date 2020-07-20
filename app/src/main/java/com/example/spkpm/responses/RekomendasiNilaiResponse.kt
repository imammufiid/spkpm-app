package com.example.spkpm.responses

import com.example.spkpm.models.RekomendasiNilaiModel

class RekomendasiNilaiResponse {
    var status: Int? = null
    lateinit var message: String
    lateinit var result: List<RekomendasiNilaiModel>
}