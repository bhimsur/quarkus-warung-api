package io.bhimsur.domain.model.user

data class UserRegistrationInput(
    val username: String,
    val email: String,
    val password: String,
    val fullName: String
)
