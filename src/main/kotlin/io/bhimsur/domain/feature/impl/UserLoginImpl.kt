package io.bhimsur.domain.feature.impl

import io.bhimsur.domain.exception.NotFoundException
import io.bhimsur.domain.exception.WrongPasswordException
import io.bhimsur.domain.feature.UserLogin
import io.bhimsur.domain.model.provider.CryptoProvider
import io.bhimsur.domain.model.provider.HashProvider
import io.bhimsur.domain.model.user.User
import io.bhimsur.domain.model.user.UserLoginInput
import io.bhimsur.domain.model.user.UserRepository
import java.util.*
import javax.inject.Named

class UserLoginImpl(
    private val hashProvider: HashProvider,
    private val userRepository: UserRepository,
    @Named(value = "aesProvider") private val cryptoProvider: CryptoProvider
) : UserLogin {
    override fun command(request: UserLoginInput): User {
        val user: Optional<User> = userRepository.findByUsername(request.username)
        val password: String = cryptoProvider.decrypt(request.password)
        if (user.isEmpty) {
            throw NotFoundException("Username Not Found")
        }
        if (!isPasswordValid(password, user.get().password)) {
            throw WrongPasswordException()
        }
        return user.get()
    }

    private fun isPasswordValid(password: String, hashed: String): Boolean {
        return hashProvider.checkPassword(password, hashed)
    }
}