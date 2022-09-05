package com.github.maykonoliveira.mercadolivrecdd.user

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class RawPasswordTest {
    @ParameterizedTest
    @CsvSource("123456", "1234567")
    fun `Given a greater than 6 password, should create object`(rawPassword: String) {
        RawPassword(rawPassword)
    }

    @ParameterizedTest
    @CsvSource("1", "12345")
    fun `Given a less than 6 password, should create object`(rawPassword: String) {
        Assertions.assertThrows(IllegalArgumentException::class.java) { RawPassword(rawPassword) }
    }

}