package com.company.user.service

import com.company.user.client.AgifyClient
import com.company.user.client.api.AgifyInfo
import com.company.user.domain.UserRepository
import com.company.user.domain.model.UserAccount
import com.company.user.web.api.UserRequest
import feign.FeignException
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.Optional
import java.util.UUID

class UserServiceTest {
    private val repo = mockk<UserRepository>()
    private val client = mockk<AgifyClient>()
    private val service = UserService(repo, AgifyService(client))

    @Test
    fun `Age returned for just created user`() {
        every { client.getAgifyInfo(any()) } returns AgifyInfo(age = 30, name = "name")
        every { repo.save(any()) } returns userAccount()

        val user = service.createUser(
            UserRequest(
                name = "name",
                email = "test@test.com",
                phoneNumber = "15550000000"
            )
        )

        assertEquals(30, user.age)
    }

    @Test
    fun `Age is null on get user if Afigy call failed`() {
        every { client.getAgifyInfo(any()) } throws mockk<FeignException>()
        every { repo.findById(any()) } returns Optional.of(userAccount())

        val user = service.getUser(UUID.randomUUID())

        assertEquals(null, user.age)
    }

    @Test
    fun `Age returned for get all users`() {
        every { client.getBatchAgifyInfo(any()) } returns listOf(
            AgifyInfo(age = 30, name = "name1"),
            AgifyInfo(age = 31, name = "name2"),
            AgifyInfo(age = 32, name = "name3")
        )
        every { repo.findAll() } returns listOf(
            userAccount("name1"),
            userAccount("name2"),
            userAccount("name3")
        )

        val users = service.getAllUsers()

        assertEquals(3, users.size)
        assertEquals(30, users.find { it.name == "name1" }?.age)
        assertEquals(31, users.find { it.name == "name2" }?.age)
        assertEquals(32, users.find { it.name == "name3" }?.age)
    }

    @Test
    fun `Age returned for part of users`() {
        every { client.getBatchAgifyInfo(any()) } returns listOf(
            AgifyInfo(age = 30, name = "name1"),
            AgifyInfo(age = 31, name = "name2")
        )
        every { repo.findAll() } returns listOf(
            userAccount("name1"),
            userAccount("name2"),
            userAccount("name3")
        )

        val users = service.getAllUsers()

        assertEquals(3, users.size)
        assertEquals(30, users.find { it.name == "name1" }?.age)
        assertEquals(31, users.find { it.name == "name2" }?.age)
        assertEquals(null, users.find { it.name == "name3" }?.age)
    }

    private fun userAccount(name: String = "name"): UserAccount {
        val id = UUID.randomUUID()
        return UserAccount(
            id = id,
            name = name,
            email = "$id@test.com",
            phoneNumber = "15550000000"
        )
    }
}