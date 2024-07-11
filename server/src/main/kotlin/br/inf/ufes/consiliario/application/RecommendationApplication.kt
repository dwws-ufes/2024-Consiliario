package br.inf.ufes.consiliario.application

import br.inf.ufes.consiliario.dto.RecommendationDto
import br.inf.ufes.consiliario.entity.Recommendation
import br.inf.ufes.consiliario.repository.RecommendationRepository
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactor.awaitSingle
import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.vocabulary.RDF
import org.springframework.core.io.buffer.DataBufferUtils
import org.springframework.http.codec.multipart.FilePart
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.ByteArrayOutputStream
import java.util.*

@Service
class RecommendationApplication(
    private val recommendationRepository: RecommendationRepository,
    private val s3Application: S3Application
) {
    suspend fun getAllRecommendations(): List<RecommendationDto> =
        recommendationRepository.findAll().map { RecommendationDto(it) }.toList()

    suspend fun getRecommendation(recommendationId: UUID): RecommendationDto {
        val recommendation = recommendationRepository.findById(recommendationId)
        return recommendation?.let { RecommendationDto(it) } ?: throw Exception("Recommendation not found")
    }

    @Transactional
    suspend fun recommend(
        sender: UUID,
        receiver: UUID,
        recommendationFile: FilePart?,
        recommendationUrl: String?
    ): RecommendationDto {
        val recommendation =
            Recommendation(receiver = receiver, sender = sender)

        if (recommendationFile != null) {
            val fileId = UUID.randomUUID()
            val file = DataBufferUtils.join(recommendationFile.content())
                .map { dataBuffer -> dataBuffer.asInputStream() }
                .awaitSingle()

            val fileExtension =
                recommendationFile.filename().substring(recommendationFile.filename().lastIndexOf('.') + 1).lowercase()

            val fileUrl = s3Application.saveObject("$fileId.$fileExtension", file)
            recommendation.url = fileUrl
        } else if (recommendationUrl != null) {
            recommendation.url = recommendationUrl
        }

        return RecommendationDto(recommendationRepository.save(recommendation))
    }

    @Transactional
    suspend fun deleteRecommendation(recommendationId: UUID) {
        val recommendation = recommendationRepository.findById(recommendationId)
        recommendation?.let {
            s3Application.deleteObject(it.url!!)
            recommendationRepository.delete(it)
        } ?: throw Exception("Recommendation not found")
    }

    suspend fun getRecommendationsRdf(): ByteArray {
        val model = ModelFactory.createDefaultModel()
        val ns = "http://localhost:8080/consiliario/data/recommendations"
        val sns = "http://schema.org/"
        model.setNsPrefixes(mapOf("schema" to sns))

        val recommendationResource = model.createResource(sns + "CreativeWork")
        val creatorProperty = model.createProperty(sns, "creator")
        val createdProperty = model.createProperty(sns, "dateCreated")
        val textProperty = model.createProperty(sns, "text")
        val teachesProperty = model.createProperty(sns, "teaches")

        val recommendations = recommendationRepository.findAll().collect {
            model.createResource("$ns/${it.id}")
                .addProperty(RDF.type, recommendationResource)
                .addProperty(creatorProperty, it.sender.toString())
                .addProperty(textProperty, it.url)
                .addProperty(createdProperty, it.createdAt.toString())
                .addProperty(teachesProperty, it.receiver.toString())
        }

        val outputStream = ByteArrayOutputStream()
        model.write(outputStream, "Turtle")

        return outputStream.toByteArray()
    }
}