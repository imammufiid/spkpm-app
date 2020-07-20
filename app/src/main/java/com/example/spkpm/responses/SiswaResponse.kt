package com.example.spkpm.responses

import com.example.spkpm.models.SiswaModel

class SiswaResponse {
    var status: Int? = null
    lateinit var message: String
    lateinit var result: List<SiswaModel>
}