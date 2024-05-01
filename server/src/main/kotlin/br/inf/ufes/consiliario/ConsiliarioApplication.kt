package br.inf.ufes.consiliario

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ConsiliarioApplication

fun main(args: Array<String>) {
	runApplication<ConsiliarioApplication>(*args)
}
