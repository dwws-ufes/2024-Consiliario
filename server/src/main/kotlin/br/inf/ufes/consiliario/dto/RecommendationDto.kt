package br.inf.ufes.consiliario.dto

import org.springframework.web.multipart.MultipartFile
import java.util.UUID

data class RecommendationDto(
    val receiver: UUID,
    val sender: UUID,
    val resourceFile: MultipartFile?,
    val resourceUrl: String?
)