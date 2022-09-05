package com.github.maykonoliveira.mercadolivrecdd.validation

import org.springframework.util.Assert
import javax.persistence.EntityManager
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import kotlin.reflect.KClass

class ExistsByValidator(private val entityManager: EntityManager) :
    ConstraintValidator<ExistsBy, Any> {
    private lateinit var fieldName: String
    private lateinit var domainClass: KClass<*>

    override fun initialize(constraintAnnotation: ExistsBy) {
        super.initialize(constraintAnnotation)
        this.domainClass = constraintAnnotation.domainClass
        this.fieldName = constraintAnnotation.fieldName
    }

    override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean {
        if (value == null) return false

        val query = entityManager.createQuery("SELECT 1 FROM ${domainClass.java.simpleName} WHERE $fieldName = :value")
        query.setParameter("value", value)
        val resultList = query.resultList

        Assert.isTrue(
            resultList.size <= 1,
            "Foi encontrado mais de um ${domainClass.java.simpleName} com o atributo $fieldName"
        )

        return resultList.isEmpty()
    }
}