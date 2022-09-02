package com.github.maykonoliveira.mercadolivrecdd.user

import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class CreateUserController(@PersistenceContext private val em: EntityManager) {

    @PostMapping
    @Transactional
    fun create(@Valid @RequestBody userForm: CreateUserForm): ResponseEntity<*> {
        val user = userForm.toDomain()

        em.persist(user)

        return ResponseEntity.ok().build<Void>()
    }
}