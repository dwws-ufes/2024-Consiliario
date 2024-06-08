package br.inf.ufes.consiliario.dto

import br.inf.ufes.consiliario.entity.Recommendation
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

class RecommendationDto(
    val id: UUID?,
    val receiver: UUID,
    val sender: UUID,
    val resourceFile: MultipartFile?,
    val resourceUrl: String?
) {
    constructor(recommendation: Recommendation) : this(
        id = recommendation.id,
        receiver = recommendation.receiver,
        sender = recommendation.sender,
        resourceFile = null,
        resourceUrl = recommendation.url
    )
}