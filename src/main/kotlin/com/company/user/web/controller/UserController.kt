package com.company.user.web.controller

import com.company.user.service.UserService
import com.company.user.web.api.UserRequest
import com.company.user.web.api.UserResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/users")
class UserController(private val service: UserService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(
        @RequestBody request: UserRequest,
    ): UserResponse {
        return service.createUser(request)
    }

    @GetMapping("/{id}")
    fun getUser(
        @PathVariable id: UUID,
    ): UserResponse {
        return service.getUser(id)
    }

    @GetMapping
    fun getAllUsers(): List<UserResponse> {
        return service.getAllUsers()
    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: UUID,
        @RequestBody request: UserRequest,
    ): UserResponse {
        return service.updateUser(id, request)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(
        @PathVariable id: UUID,
    ) {
        service.deleteUser(id)
    }

}