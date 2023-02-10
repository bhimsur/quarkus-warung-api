package io.bhimsur.app.web.model.request

import io.bhimsur.domain.model.user.UserRegistrationInput

data class UserRegistrationRequest(
    val username: String,
    val email: String,
    val password: String,
    val fullName: String
) {
    fun toUserRegistrationInput(): UserRegistrationInput {
        return UserRegistrationInput(username, email, password, fullName)
    }
}
