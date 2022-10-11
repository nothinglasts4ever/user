package com.company.user.exceptions

import org.springframework.http.HttpStatus
import java.util.UUID

class AgifyConnectionException() : UserAppException(
    status = HttpStatus.BAD_GATEWAY,
    reason = "Cannot establish connection to Agify"
)

