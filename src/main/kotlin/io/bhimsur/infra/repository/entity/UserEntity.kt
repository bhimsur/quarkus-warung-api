package io.bhimsur.infra.repository.entity

import io.bhimsur.domain.model.user.User
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user")
class UserEntity {
    @Id
    lateinit var id: UUID
    lateinit var username: String
    lateinit var email: String
    lateinit var password: String
    var fullName: String? = null
    lateinit var createDate: LocalDateTime
    var modifiedDate: LocalDateTime? = null

    constructor(user: User) {
        id = user.id
        update(user)
    }

    constructor()

    fun update(user: User) {
        username = user.username
        email = user.email
        password = user.password
        fullName = user.fullName
        createDate = user.createDate
        modifiedDate = user.modifiedDate
    }
}
