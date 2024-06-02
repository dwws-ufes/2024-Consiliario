package br.inf.ufes.consiliario.application

import br.inf.ufes.consiliario.config.AWSConfig
import br.inf.ufes.consiliario.dto.RecommendationDto
import br.inf.ufes.consiliario.entity.Recommendation
import br.inf.ufes.consiliario.repository.RecommendationRepository
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.core.io.buffer.DataBufferUtils
import org.springframework.http.codec.multipart.FilePart
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class RecommendationApplication(
    private val recommendationRepository: RecommendationRepository,
    private val awsConfig: AWSConfig
) {
    @Transactional
    suspend fun recommend(
        sender: UUID,
        receiver: UUID,
        recommendationFile: FilePart?,
        recommendationUrl: String?
    ) {
        val recommendation = Recommendation(receiver = receiver, sender = sender)

        if (recommendationFile != null) {
            val fileId = UUID.randomUUID()
            val file = DataBufferUtils.join(recommendationFile.content())
                .map { dataBuffer -> dataBuffer.asInputStream() }
                .awaitSingle()

            val fileUrl = awsConfig.saveObject("$fileId.jpg", file)
            recommendation.url = fileUrl
        } else if (recommendationUrl != null) {
            recommendation.url = recommendationUrl
        }

        recommendationRepository.save(recommendation)
    }
}