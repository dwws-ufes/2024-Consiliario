package br.inf.ufes.consiliario.application

import br.inf.ufes.consiliario.config.ApacheJenaConfig
import br.inf.ufes.consiliario.dto.DetailedMeetingDto
import br.inf.ufes.consiliario.dto.MeetingRegisterDto
import br.inf.ufes.consiliario.entity.Meeting
import br.inf.ufes.consiliario.repository.MeetingRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Service
import java.util.*

@Service
class MeetingApplication(
    private val meetingRepository: MeetingRepository,
    private val apacheJenaConfig: ApacheJenaConfig
) {
    private suspend fun getMeetingDetails(meeting: Meeting): DetailedMeetingDto {
        val result = apacheJenaConfig.runQuery(
            """
                    PREFIX wd: <http://www.wikidata.org/entity/>
                    PREFIX wdt: <http://www.wikidata.org/prop/direct/>
                    PREFIX wikibase: <http://wikiba.se/ontology#>
                    PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                    PREFIX bd: <http://www.bigdata.com/rdf#>
                    PREFIX schema: <http://schema.org/>
                    
                    SELECT ?itemLabel ?itemDescription WHERE {
                        VALUES ?item { wd:${meeting.location} }
                        SERVICE wikibase:label { bd:serviceParam wikibase:language "[AUTO_LANGUAGE],pt". }
                        ?item schema:description ?itemDescription .
                    }
                    LIMIT 1
                """.trimIndent()
        )

        val detailedMeeting = DetailedMeetingDto(meeting)

        if (result.hasNext()) {
            val row = result.next()

            detailedMeeting.label = row.get("itemLabel").asLiteral().string
            detailedMeeting.description = row.get("itemDescription").asLiteral().string
        }

        return detailedMeeting
    }

    suspend fun getMeeting(meetingId: UUID): DetailedMeetingDto? = meetingRepository.findById(meetingId)
        ?.let { getMeetingDetails(it) }

    suspend fun registerMeeting(meetingRegisterDto: MeetingRegisterDto) =
        meetingRepository.save(meetingRegisterDto.toEntity())

    suspend fun getAllFromTeacher(teacherId: UUID): List<DetailedMeetingDto> = coroutineScope {
        val meetings = meetingRepository.findByTeacher(teacherId)
        val detailedMeetings = meetings.map { meeting ->
            async { getMeetingDetails(meeting) }
        }.awaitAll()

        detailedMeetings
    }
}