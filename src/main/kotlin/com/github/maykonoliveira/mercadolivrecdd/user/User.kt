package com.github.maykonoliveira.mercadolivrecdd.user

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
class User(val login: String, val password: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long? = null

    @CreatedDate
    private val createdDate: LocalDateTime = LocalDateTime.now()
}
