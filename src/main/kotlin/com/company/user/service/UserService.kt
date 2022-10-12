package com.company.user.service

import com.company.user.domain.UserRepository
import com.company.user.domain.model.UserAccount
import com.company.user.exceptions.UserNotFoundException
import com.company.user.web.api.UserRequest
import com.company.user.web.api.UserResponse
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
    private val repo: UserRepository,
    private val agifyService: AgifyService,
) {

    fun createUser(request: UserRequest): UserResponse {
        val user = UserAccount(
            id = UUID.randomUUID(),
            name = request.name,
            email = request.email,
            phoneNumber = request.phoneNumber,
        )
        val age = agifyService.getUserAgeOrNull(request.name)

        return repo.save(user).toResponse(age)
    }

    fun getUser(id: UUID): UserResponse {
        val user = getUserOrElseThrow(id)
        val age = agifyService.getUserAgeOrNull(user.name)

        return user.toResponse(age)
    }

    fun getAllUsers(): List<UserResponse> {
        val users = repo.findAll()
        val userNames = users.map { it.name }.toSet()
        val ages = agifyService.getUserAges(userNames)

        return users.map {
            it.toResponse(ages[it.name])
        }
    }

    fun updateUser(id: UUID, request: UserRequest): UserResponse {
        val user = getUserOrElseThrow(id)
            .copy(
                name = request.name,
                email = request.email,
                phoneNumber = request.phoneNumber,
            )
        val age = agifyService.getUserAgeOrNull(request.name)

        return repo.save(user).toResponse(age)
    }

    fun deleteUser(id: UUID) {
        val user = getUserOrElseThrow(id)
        repo.delete(user)
    }

    private fun getUserOrElseThrow(id: UUID): UserAccount = repo.findById(id).orElseThrow { UserNotFoundException(id) }

}

fun UserAccount.toResponse(age: Int?) = UserResponse(
    id = id,
    name = name,
    email = email,
    phoneNumber = phoneNumber,
    age = age,
)