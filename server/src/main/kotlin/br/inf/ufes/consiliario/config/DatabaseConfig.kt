package br.inf.ufes.consiliario.config

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import io.r2dbc.spi.ConnectionFactoryOptions.*
import org.flywaydb.core.Flyway
import org.springframework.boot.autoconfigure.flyway.FlywayProperties
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import java.util.*


@Configuration
@EnableConfigurationProperties(R2dbcProperties::class, FlywayProperties::class)
@EnableR2dbcRepositories
internal class DatabaseConfig : AbstractR2dbcConfiguration() {
    @Bean(initMethod = "migrate")
    fun flyway(flywayProperties: FlywayProperties, r2dbcProperties: R2dbcProperties): Flyway {
        return Flyway.configure()
            .dataSource(
                flywayProperties.url,
                r2dbcProperties.username,
                r2dbcProperties.password
            )
            .locations(
                flywayProperties.locations.first().toString()
            )
            .baselineOnMigrate(true)
            .load()
    }

    @Bean
    override fun connectionFactory(): ConnectionFactory {
        val baseOptions = parse("r2dbc:postgresql://database:5432/postgres?schema=consiliario")
        var ob = builder().from(baseOptions)
        ob = ob.option(USER, "postgres")
        ob = ob.option(PASSWORD, "admin")
        return ConnectionFactories.get(ob.build())
    }
}
