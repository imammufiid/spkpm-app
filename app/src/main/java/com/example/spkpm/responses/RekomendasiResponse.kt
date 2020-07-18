package com.example.spkpm.responses

import com.example.spkpm.models.RekomendasiModel

class RekomendasiResponse {
    var status: Int? = null
    lateinit var message: String
    lateinit var result: List<RekomendasiModel>
}