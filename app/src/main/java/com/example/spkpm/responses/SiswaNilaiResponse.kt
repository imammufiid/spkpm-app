package com.example.spkpm.responses

import com.example.spkpm.models.KriteriaModel
import com.example.spkpm.models.SiswaNilaiModel

class SiswaNilaiResponse {
    var status: Int? = null
    lateinit var message: String
    lateinit var result: List<SiswaNilaiModel>
}