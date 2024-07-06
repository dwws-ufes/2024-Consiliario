package br.inf.ufes.consiliario.repository

import br.inf.ufes.consiliario.entity.Meeting
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

interface MeetingRepository : CoroutineCrudRepository<Meeting, UUID> {
    @Query("SELECT * FROM meeting WHERE teacher = :teacher")
    suspend fun findByTeacher(teacher: UUID): List<Meeting>
}