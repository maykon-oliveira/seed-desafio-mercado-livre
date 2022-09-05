package com.github.maykonoliveira.mercadolivrecdd.user

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.util.Assert
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
class User(private val login: String, password: RawPassword) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long? = null

    @CreatedDate
    private val createdDate: LocalDateTime = LocalDateTime.now()
    private val password: String

    init {
        Assert.hasLength(login, "login não pode ser branco")
        Assert.notNull(password, "password não pode ser nula")

        this.password = password.hash()
    }

}
