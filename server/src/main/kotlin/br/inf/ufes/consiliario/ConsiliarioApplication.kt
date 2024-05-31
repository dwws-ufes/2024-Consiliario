package br.inf.ufes.consiliario

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan


@SpringBootApplication
@ComponentScan(basePackages = ["br.inf.ufes.consiliario"])
class ConsiliarioApplication

fun main(args: Array<String>) {
    runApplication<ConsiliarioApplication>(*args)
}
