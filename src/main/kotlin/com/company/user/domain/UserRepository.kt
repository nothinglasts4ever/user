package com.company.user.domain

import com.company.user.domain.model.UserAccount
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface UserRepository : CrudRepository<UserAccount, UUID>