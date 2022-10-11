package com.company.user.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

open class UserAppException(
    status: HttpStatus,
    reason: String,
) : ResponseStatusException(status, reason)