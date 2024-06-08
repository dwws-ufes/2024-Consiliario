package br.inf.ufes.consiliario.controller

import br.inf.ufes.consiliario.application.RecommendationApplication
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.*

@RestController
@RequestMapping("/recommendation")
class RecommendationController(
    private val recommendationApplication: RecommendationApplication
) {
    @GetMapping
    suspend fun getRecommendation(
        @RequestParam recommendationId: UUID?
    ) = when (recommendationId) {
        null -> recommendationApplication.getAllRecommendations()
        else -> listOf(recommendationApplication.getRecommendation(recommendationId))
    }

    @PostMapping
    suspend fun recommend(
        @RequestPart sender: String,
        @RequestPart receiver: String,
        @RequestPart(required = false) resourceFile: Mono<FilePart>?,
        @RequestPart(required = false) resourceUrl: Mono<String>?,
    ) = recommendationApplication.recommend(
        sender = UUID.fromString(sender),
        receiver = UUID.fromString(receiver),
        recommendationFile = resourceFile?.awaitSingleOrNull(),
        recommendationUrl = resourceUrl?.awaitSingleOrNull()
    )

    @DeleteMapping
    suspend fun deleteRecommendation(
        @RequestParam recommendationId: UUID
    ) = recommendationApplication.deleteRecommendation(recommendationId)
}
