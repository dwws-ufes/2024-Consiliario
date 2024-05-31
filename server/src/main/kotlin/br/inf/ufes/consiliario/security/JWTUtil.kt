package br.inf.ufes.consiliario.security

import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.*
import io.jsonwebtoken.jackson.io.JacksonDeserializer
import io.jsonwebtoken.jackson.io.JacksonSerializer
import io.jsonwebtoken.security.SecurityException
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class JWTUtil(
    @Value("\${jwt.secret}") private val secret: String,
    @Value("\${jwt.expiration}") private val expirationTime: Long, //in second
    private val objectMapper: ObjectMapper
) {

    private val jwtParser = Jwts.parser()
        .deserializeJsonWith(JacksonDeserializer(objectMapper))
        .setSigningKey(secret.toByteArray())
        .build()

    fun getAllClaimsFromToken(token: String): Claims = jwtParser.parseClaimsJws(token).body

    fun getUsernameFromToken(token: String): String = getAllClaimsFromToken(token).subject

    fun generateToken(user: UserDetails): String {
        val claims = mutableMapOf<String, Any>()
        claims["authorities"] = user.authorities
        claims["enabled"] = user.isEnabled
        return doGenerateToken(user.username, claims)
    }

    fun validateToken(token: String): Boolean {
        try {
            // token must be signed
            if (!jwtParser.isSigned(token)) return false
            // parse token to get claims, will throw ExpiredJwtException if expired at current time
            val claims = getAllClaimsFromToken(token)
            val enabled = claims.getOrDefault("enabled", false) as Boolean
            return enabled
        } catch (ex: SecurityException) {
            return false
        } catch (ex: MalformedJwtException) {
            return false
        } catch (ex: ExpiredJwtException) {
            return false
        } catch (ex: UnsupportedJwtException) {
            return false
        } catch (ex: IllegalArgumentException) {
            return false
        }
    }

    private fun doGenerateToken(username: String, claims: Map<String, Any>): String {
        val createdDate = Date()
        val expirationDate = Date(createdDate.time + expirationTime * 1000)
        val key = Keys.hmacShaKeyFor(secret.toByteArray())

        return Jwts.builder()
            .serializeToJsonWith(JacksonSerializer(objectMapper))
            .setClaims(claims)
            .setSubject(username)
            .setIssuedAt(createdDate)
            .setExpiration(expirationDate)
            .signWith(key, SignatureAlgorithm.HS512)
            .compact()
    }
}