package br.inf.ufes.consiliario.repository

import br.inf.ufes.consiliario.entity.Student
import br.inf.ufes.consiliario.entity.Teacher
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

interface StudentRepository : CoroutineCrudRepository<Student, UUID> {

    @Query("SELECT * FROM student WHERE advisor = :advisor")
    suspend fun findByAdvisor(advisor: UUID): List<Student>
}