package br.inf.ufes.consiliario.entity

import java.util.*

data class Recommendation(
    val id: UUID? = null,
    val receiver: UUID,
    val sender: UUID,
    var url: String? = null,
    var fileUrl: String? = null
)