package com.company.user.domain.model

import org.hibernate.Hibernate
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class UserAccount(
    @Id
    val id: UUID,
    val name: String,
    val email: String,
    val phoneNumber: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as UserAccount
        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    override fun toString(): String = this::class.simpleName + "(id = $id , name = $name , email = $email , phoneNumber = $phoneNumber )"
}
