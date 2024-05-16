package br.inf.ufes.consiliario.repository

import br.inf.ufes.consiliario.entity.User
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

interface UserRepository : CoroutineCrudRepository<User, UUID> {

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun findByEmail(email: String): User
}