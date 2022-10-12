package com.company.user.web.api

import java.util.UUID
import javax.validation.constraints.Email
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class UserRequest(
    @field:Size(max = 747)
    val name: String,

    @field:Email
    val email: String,

    @field:Pattern(regexp = "^[+]?[0-9]{2,15}\$")
    val phoneNumber: String,
)

data class UserResponse(
    val id: UUID,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val age: Int?,
)