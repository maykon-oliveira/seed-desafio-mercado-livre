package com.github.maykonoliveira.mercadolivrecdd.category

import org.springframework.http.HttpStatus
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
@RequestMapping("/categories")
class CreateCategoryController(@PersistenceContext private val em: EntityManager) {
    @PostMapping
    @Transactional
    fun create(@Valid @RequestBody categoryForm: CreateCategoryForm): ResponseEntity<*> {
        val category = categoryForm.toDomain(em)

        em.persist(category)

        return ResponseEntity.status(HttpStatus.CREATED).body("Salvo");
    }
}