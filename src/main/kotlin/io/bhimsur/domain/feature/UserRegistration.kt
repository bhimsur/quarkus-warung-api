package io.bhimsur.domain.feature

import io.bhimsur.domain.model.user.User
import io.bhimsur.domain.model.user.UserRegistrationInput

interface UserRegistration {
    fun command(request: UserRegistrationInput): User
}