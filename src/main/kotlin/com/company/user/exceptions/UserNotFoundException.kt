package com.company.user.exceptions

import org.springframework.http.HttpStatus
import java.util.UUID

class UserNotFoundException(id: UUID) : UserAppException(
    status = HttpStatus.NOT_FOUND,
    reason = "User $id was not found in the system"
)

