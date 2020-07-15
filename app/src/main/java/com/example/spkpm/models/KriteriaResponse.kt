package com.example.spkpm.models

class KriteriaResponse {
    var status: Int? = null
    lateinit var message: String
    lateinit var result: List<KriteriaModel>
}