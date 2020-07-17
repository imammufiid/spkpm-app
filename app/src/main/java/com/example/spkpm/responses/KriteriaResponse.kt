package com.example.spkpm.responses

import com.example.spkpm.models.KriteriaModel

class KriteriaResponse {
    var status: Int? = null
    lateinit var message: String
    lateinit var result: List<KriteriaModel>
}