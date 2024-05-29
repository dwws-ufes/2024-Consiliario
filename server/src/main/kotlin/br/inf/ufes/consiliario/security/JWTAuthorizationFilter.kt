package br.inf.ufes.consiliario.security

import br.inf.ufes.consiliario.entity.User
import br.inf.ufes.consiliario.repository.UserRepository
import org.springframework.stereotype.Component
import kotlinx.coroutines.reactor.mono
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import reactor.core.publisher.Mono

@Component
class JWTAuthorizationFilter(
    private val userRepository: UserRepository,
) : ReactiveAuthenticationManager {

    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        val authRequest = authentication.principal as User
        return mono {
            authRequest.id?.let {
                userRepository.findById(it)?.let {
                    UsernamePasswordAuthenticationToken(
                        authRequest,
                        null
                    )
                } ?: throw BadCredentialsException("User not found")
            }
        }
    }
}