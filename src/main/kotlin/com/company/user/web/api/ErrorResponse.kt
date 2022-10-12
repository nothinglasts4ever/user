package com.company.user.web.api

data class ErrorResponse(
    val message: String,
    val validationErrors: List<ValidationError> = emptyList(),
)

data class ValidationError(
    val field: String,
    val error: String?,
)