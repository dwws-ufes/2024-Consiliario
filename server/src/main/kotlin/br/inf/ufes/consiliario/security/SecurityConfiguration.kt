import br.inf.ufes.consiliario.security.JWTAuthenticationConverter
import br.inf.ufes.consiliario.security.JWTAuthorizationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsConfigurationSource
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource
import org.springframework.security.config.web.server.invoke

@Configuration
@EnableWebFluxSecurity
class SecurityConfiguration() {

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource? {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*")
        configuration.allowedMethods = listOf("*")
        configuration.allowedHeaders = listOf("*")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    @Bean
    fun configure(
        http: ServerHttpSecurity,
        jwtAuthorizationFilter: JWTAuthorizationFilter,
        jwtAuthenticationConverter: JWTAuthenticationConverter
    ): SecurityWebFilterChain {
        val authenticationWebFilter = AuthenticationWebFilter(jwtAuthorizationFilter).also {
            it.setServerAuthenticationConverter(jwtAuthenticationConverter)
        }

        return http {
            csrf {
                disable()
            }
            cors {
                configurationSource = corsConfigurationSource()
            }
            authorizeExchange {
                authorize("/login", permitAll)
                authorize("/register", permitAll)
                authorize(anyExchange, authenticated)
            }
            addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
        }
    }
}