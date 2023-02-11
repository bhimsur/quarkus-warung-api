package io.bhimsur.domain.feature.impl

import io.bhimsur.domain.exception.AlreadyExistsException
import io.bhimsur.domain.feature.UserRegistration
import io.bhimsur.domain.model.constant.ErrorMapper
import io.bhimsur.domain.model.provider.HashProvider
import io.bhimsur.domain.model.user.User
import io.bhimsur.domain.model.user.UserModelBuilder
import io.bhimsur.domain.model.user.UserRegistrationInput
import io.bhimsur.domain.model.user.UserRepository

class UserRegistrationImpl(
    private val userRepository: UserRepository,
    private val hashProvider: HashProvider,
    private val userModelBuilder: UserModelBuilder
) : UserRegistration {
    override fun command(request: UserRegistrationInput): User {
        val user: User = userModelBuilder.build(request.username, request.email, request.password)
        checkExistingEmail(user.email)
        checkExistingUsername(user.username)
        user.password = hashProvider.hashPassword(user.password)
        userRepository.save(user)
        return user
    }

    private fun checkExistingUsername(username: String) {
        if (userRepository.existsUsername(username)) {
            throw AlreadyExistsException(ErrorMapper.Code.USERNAME_ALREADY_EXISTS, ErrorMapper.Message.USERNAME_ALREADY_EXISTS)
        }
    }

    private fun checkExistingEmail(email: String) {
        if (userRepository.existsEmail(email)) {
            throw AlreadyExistsException(ErrorMapper.Code.EMAIL_ALREADY_EXISTS, ErrorMapper.Message.EMAIL_ALREADY_EXISTS)
        }
    }
}