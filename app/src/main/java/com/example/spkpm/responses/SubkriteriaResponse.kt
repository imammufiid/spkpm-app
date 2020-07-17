package com.example.spkpm.responses

import com.example.spkpm.models.KriteriaModel
import com.example.spkpm.models.SubkriteriaModel

class SubkriteriaResponse {
    var status: Int? = null
    lateinit var message: String
    lateinit var result: List<SubkriteriaModel>
}