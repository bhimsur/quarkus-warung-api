package io.bhimsur.domain.model.user

interface UserRepository {
    fun save(user: User): Void
    fun findByUsername(username: String): User?
    fun findByEmail(email: String): User?
    fun existsUsername(username: String): Boolean
    fun existsEmail(email: String): Boolean
    fun update(user: User): Void
}