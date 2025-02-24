package com.github.maykonoliveira.mercadolivrecdd

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = arrayOf(SecurityAutoConfiguration::class))
class MercadoLivreCddApplication

fun main(args: Array<String>) {
    runApplication<MercadoLivreCddApplication>(*args)
}
