package com.github.maykonoliveira.mercadolivrecdd.category

import javax.persistence.*

@Entity
data class Category(val name: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long? = null

    @ManyToOne
    var fatherCategory: Category? = null
}
