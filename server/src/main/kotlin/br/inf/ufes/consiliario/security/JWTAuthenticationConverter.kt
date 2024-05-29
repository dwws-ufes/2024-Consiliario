package br.inf.ufes.consiliario.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class JWTAuthenticationConverter : ServerAuthenticationConverter {

    override fun convert(exchange: ServerWebExchange?): Mono<Authentication> {
        return Mono.justOrEmpty(exchange)
            .flatMap { Mono.justOrEmpty(it.request.headers.getFirst("Authorization")) }
            .filter { it.isNotEmpty() }
            .filter { it.startsWith("Bearer ") }
            .map { it.substringAfter("Bearer ") }
            .map {
                val decodedJWT = JWT.require(Algorithm.HMAC512("brinfufesconsiliario"))
                    .build()
                    .verify(it)
                UsernamePasswordAuthenticationToken(
                    decodedJWT.subject,
                    null,
                    emptyList())
            }
    }
}