package com.github.maykonoliveira.mercadolivrecdd.category

import com.github.maykonoliveira.mercadolivrecdd.validation.ExistsBy
import org.springframework.util.Assert
import javax.persistence.EntityManager
import javax.validation.constraints.NotBlank

data class CreateCategoryForm(
    @field:NotBlank @field:ExistsBy(
        fieldName = "name",
        domainClass = Category::class
    ) var name: String?, var fatherCategory: Long?
) {
    fun toDomain(em: EntityManager): Category {
        val category = Category(name!!)
        if (fatherCategory != null) {
            val father = em.find(Category::class.java, fatherCategory)
            Assert.notNull(father, "Category with id $fatherCategory does not exist!")
            category.fatherCategory = father
        }
        return category
    }
}
