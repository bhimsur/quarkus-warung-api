package io.bhimsur.app.web.model.response

import com.fasterxml.jackson.annotation.JsonRootName
import io.bhimsur.domain.model.user.User
import io.quarkus.runtime.annotations.RegisterForReflection

@JsonRootName("user")
@RegisterForReflection
data class UserResponse(
    val username: String,
    val fullName: String?
) {
    constructor(user: User) : this(user.username, user.fullName)
}
