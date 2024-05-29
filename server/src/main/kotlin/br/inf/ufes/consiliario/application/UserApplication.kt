package br.inf.ufes.consiliario.application

import br.inf.ufes.consiliario.entity.User
import br.inf.ufes.consiliario.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class UserApplication(
    private val userRepository: UserRepository
) {
    suspend fun getUserByEmail(email: String): User {
        return userRepository.findByEmail(email)
    }

    suspend fun createUser() = userRepository.save(
        User(username = "John Doe", email = "johndoe@mail.com", password = "12345678")
    )
}