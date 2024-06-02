package br.inf.ufes.consiliario.repository

import br.inf.ufes.consiliario.entity.Teacher
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

interface TeacherRepository : CoroutineCrudRepository<Teacher, UUID> {

    @Query("SELECT * FROM teacher WHERE email = :email")
    suspend fun findByEmail(email: String): Teacher?
}