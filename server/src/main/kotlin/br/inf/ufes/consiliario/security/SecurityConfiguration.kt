package br.inf.ufes.consiliario.security

import br.inf.ufes.consiliario.application.auth.AuthenticationService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
        private val authenticationService: AuthenticationService,
        private val securityFilter: SecurityFilter
) {
    @Bean
    fun securityFilterChain(
            http: HttpSecurity,
            securityFilter: SecurityFilter
    ): DefaultSecurityFilterChain {
        return http.authorizeHttpRequests {
            it
                    .anyRequest().authenticated()
                    .requestMatchers(HttpMethod.POST,"/login").permitAll()
                    .requestMatchers(HttpMethod.POST,"/register").permitAll()
        }.csrf {
            it.disable()
        }.sessionManagement {
            it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter::class.java)
                .build()
    }

    @Bean
    fun authenticationManager(
            authenticationManagerBuilder: AuthenticationManagerBuilder,
            passwordEncoder: PasswordEncoder
    ): AuthenticationManager {
        val provider = DaoAuthenticationProvider()
        provider.setPasswordEncoder(passwordEncoder)
        provider.setUserDetailsService(authenticationService)

        return ProviderManager(provider)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        // For simplicity, this project does not use salt. Though, it should definitely be used in real applications
        return BCryptPasswordEncoder()
    }
}