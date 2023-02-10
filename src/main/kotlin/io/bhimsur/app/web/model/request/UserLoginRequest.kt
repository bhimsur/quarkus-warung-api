package io.bhimsur.app.web.model.request

import io.bhimsur.domain.model.user.UserLoginInput

data class UserLoginRequest(val username: String, val password: String) {
    fun toUserLoginInput(): UserLoginInput {
        return UserLoginInput(this.username, this.password)
    }
}
