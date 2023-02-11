package io.bhimsur.domain.model.user

import java.util.*

interface UserRepository {
    fun save(user: User)
    fun findByUsername(username: String): Optional<User>
    fun findByEmail(email: String): Optional<User>
    fun existsUsername(username: String): Boolean
    fun existsEmail(email: String): Boolean
    fun update(user: User)
}