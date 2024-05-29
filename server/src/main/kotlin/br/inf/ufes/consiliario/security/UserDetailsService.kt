package br.inf.ufes.consiliario.security

import br.inf.ufes.consiliario.repository.UserRepository
import kotlinx.coroutines.reactor.mono
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserDetailsService(
        private val repository: UserRepository
) : ReactiveUserDetailsService {
    override fun findByUsername(email: String?): Mono<UserDetails> {
        if (email == null)
            throw BadCredentialsException("")

        return mono {
            repository.findByEmail(email) as UserDetails? ?: throw BadCredentialsException("")
        }
    }

}