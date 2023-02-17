package io.bhimsur.app.web.model.request

import io.bhimsur.domain.model.user.UserLoginInput
import javax.validation.constraints.NotBlank

data class UserLoginRequest(@NotBlank val username: String, @NotBlank val password: String) {
    fun toUserLoginInput(): UserLoginInput {
        return UserLoginInput(this.username, this.password)
    }
}
