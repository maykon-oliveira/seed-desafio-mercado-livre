package com.github.maykonoliveira.mercadolivrecdd.validation

import com.github.maykonoliveira.mercadolivrecdd.user.CreateUserForm
import com.github.maykonoliveira.mercadolivrecdd.user.User
import org.hibernate.validator.internal.util.annotation.AnnotationDescriptor
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.context.SpringBootTest
import javax.persistence.EntityManager
import javax.persistence.Query
import javax.validation.ConstraintValidatorContext


@ExtendWith(MockitoExtension::class)
@SpringBootTest
internal class ExistsByValidatorTest {

    private lateinit var validator: ExistsByValidator
    private lateinit var emMock: EntityManager

    @BeforeEach
    internal fun setUp() {
        this.emMock = mock(EntityManager::class.java)
        this.validator = ExistsByValidator(emMock)
    }

    @Test
    fun `Given a login that exists should returns invalid`() {
        val constraintValidatorContext = mock(ConstraintValidatorContext::class.java)
        val queryMock = mock(Query::class.java)
        Mockito.`when`(queryMock.resultList).thenReturn(listOf(1))
        Mockito.`when`(emMock.createQuery(ArgumentMatchers.anyString())).thenReturn(queryMock)

        val annotation =
            AnnotationDescriptor.Builder(ExistsBy::class.java, mapOf("fieldName" to "login", "domainClass" to User::class.java))
                .build().annotation

        validator.initialize(annotation)

        val userForm = CreateUserForm("maykon@email.com", "123456")
        Assertions.assertFalse(validator.isValid(userForm.login, constraintValidatorContext))
    }

    @Test
    fun `Given a login that not exists should returns valid`() {
        val constraintValidatorContext = mock(ConstraintValidatorContext::class.java)
        val queryMock = mock(Query::class.java)
        Mockito.`when`(queryMock.resultList).thenReturn(emptyList<Void>())
        Mockito.`when`(emMock.createQuery(ArgumentMatchers.anyString())).thenReturn(queryMock)

        val annotation =
            AnnotationDescriptor.Builder(ExistsBy::class.java, mapOf("fieldName" to "login", "domainClass" to User::class.java))
                .build().annotation

        validator.initialize(annotation)

        val userForm = CreateUserForm("maykon@email.com", "123456")
        Assertions.assertTrue(validator.isValid(userForm.login, constraintValidatorContext))
    }

}