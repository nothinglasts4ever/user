package com.company.user.web.api

import java.util.UUID
import javax.validation.constraints.Email
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class UserRequest(
    @field:Size(min = 1, max = 747)
    val name: String,

    @field:Email @field:Size(max = 255)
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