package com.company.user.web.api

import java.time.OffsetDateTime

data class ErrorResponse(
    val message: String,
    val timestamp: OffsetDateTime = OffsetDateTime.now(),
)