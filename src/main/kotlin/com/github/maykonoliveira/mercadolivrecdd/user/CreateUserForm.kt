package com.github.maykonoliveira.mercadolivrecdd.user

import com.github.maykonoliveira.mercadolivrecdd.validation.ExistsBy
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class CreateUserForm(
    @field:Email
    @field:NotBlank
    @field:ExistsBy(fieldName = "login", domainClass = User::class)
    val login: String?,
    @field:NotBlank
    @field:Size(min = 6)
    val password: String?,
) {
    fun toDomain(): User {
        return User(login!!, RawPassword(password!!))
    }
}
