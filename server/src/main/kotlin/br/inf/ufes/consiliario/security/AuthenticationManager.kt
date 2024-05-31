package br.inf.ufes.consiliario.security

import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono


@Component
class AuthenticationManager(private val jwtUtil: JWTUtil) : ReactiveAuthenticationManager {

    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        val authToken = authentication.credentials.toString()
        val username = try {
            jwtUtil.getUsernameFromToken(authToken)
        } catch (e: Throwable) {
            null
        }

        return if (null != username && jwtUtil.validateToken(authToken)) {
            val claims = jwtUtil.getAllClaimsFromToken(authToken)
            UsernamePasswordAuthenticationToken(username, null, listOf()).toMono()
        } else {
            Mono.empty()
        }
    }
}