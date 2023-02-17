package io.bhimsur.app.web.model.request

import io.bhimsur.domain.model.user.UserRegistrationInput
import javax.validation.constraints.NotBlank

data class UserRegistrationRequest(
    @NotBlank
    val username: String,
    @NotBlank
    val email: String,
    @NotBlank
    val password: String,
    @NotBlank
    val fullName: String
) {
    fun toUserRegistrationInput(): UserRegistrationInput {
        return UserRegistrationInput(username, email, password, fullName)
    }
}
