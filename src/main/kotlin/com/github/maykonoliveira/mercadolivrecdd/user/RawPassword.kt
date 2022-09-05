package com.github.maykonoliveira.mercadolivrecdd.user

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.util.Assert

class RawPassword(password: String) {
    private val password: String;

    init {
        Assert.hasLength(password, "password não pode ser branco")
        Assert.isTrue(password.length >= 6, "password deve ter mais de 6 caracteres")

        this.password = password
    }

    fun hash(): String = BCryptPasswordEncoder().encode(password)
}
