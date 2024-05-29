package br.inf.ufes.consiliario.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.Instant
import java.util.UUID

@Table("Users")
class User(
    @Id val id: UUID? = null,
    val email: String,
    private val username: String,
    private val password: String
) : UserDetails {
    override fun getAuthorities() = emptyList<GrantedAuthority>()
    override fun getPassword() = password
    override fun isEnabled() = true
    override fun getUsername() = email
    override fun isCredentialsNonExpired() = true
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
}