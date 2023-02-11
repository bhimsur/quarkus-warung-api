package io.bhimsur.domain.model.constant

object ErrorMapper {
    object Code {
        const val VALIDATION: Int = 0
        const val WRONG_PASSWORD: Int = 1
        const val USERNAME_ALREADY_EXISTS: Int = 2
        const val EMAIL_ALREADY_EXISTS: Int = 3
    }

    object Message {
        const val WRONG_PASSWORD: String = "Wrong Password"
        const val USERNAME_ALREADY_EXISTS: String = "Username Already Exists"
        const val EMAIL_ALREADY_EXISTS: String = "Email Already Exists"
    }
}