package com.company.user.web.api

import java.util.UUID

data class UserRequest(
    val name: String,
    val email: String,
    val phoneNumber: String,
)

data class UserResponse(
    val id: UUID,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val age: Int?,
)