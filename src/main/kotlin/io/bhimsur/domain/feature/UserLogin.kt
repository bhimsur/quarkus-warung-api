package io.bhimsur.domain.feature

import io.bhimsur.domain.model.user.User
import io.bhimsur.domain.model.user.UserLoginInput

interface UserLogin {
    fun command(request: UserLoginInput): User
}