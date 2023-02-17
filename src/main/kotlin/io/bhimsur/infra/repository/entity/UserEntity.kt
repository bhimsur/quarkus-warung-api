package io.bhimsur.infra.repository.entity

import io.bhimsur.domain.model.user.User
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
class UserEntity {
    @Id
    @Column(name = "id")
    lateinit var id: UUID
    @Column(name = "username")
    lateinit var username: String
    @Column(name = "email")
    lateinit var email: String
    @Column(name = "password")
    lateinit var password: String
    @Column(name = "fullname")
    var fullName: String? = null
    @Column(name = "create_date")
    lateinit var createDate: LocalDateTime
    @Column(name = "modified_date")
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
