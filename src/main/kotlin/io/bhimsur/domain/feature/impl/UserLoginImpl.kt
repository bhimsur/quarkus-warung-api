package io.bhimsur.domain.feature.impl

import io.bhimsur.domain.exception.WrongPasswordException
import io.bhimsur.domain.feature.UserLogin
import io.bhimsur.domain.model.provider.HashProvider
import io.bhimsur.domain.model.user.User
import io.bhimsur.domain.model.user.UserLoginInput
import io.bhimsur.domain.model.user.UserRepository

class UserLoginImpl(
    private val hashProvider: HashProvider,
    private val userRepository: UserRepository
) : UserLogin {
    override fun command(request: UserLoginInput): User {
        val user: User? = userRepository.findByUsername(request.username)
        if (!isPasswordValid(request.password, user!!.password)) {
            throw WrongPasswordException()
        }
        return user
    }

    private fun isPasswordValid(password: String, hashed: String): Boolean {
        return hashProvider.checkPassword(password, hashed)
    }
}