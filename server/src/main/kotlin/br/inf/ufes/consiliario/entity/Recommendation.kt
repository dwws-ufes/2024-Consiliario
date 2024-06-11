package br.inf.ufes.consiliario.entity

import org.springframework.data.annotation.Id
import java.util.*

data class Recommendation(
    @Id val id: UUID? = null,
    val receiver: UUID,
    val sender: UUID,
    var url: String? = null
)
