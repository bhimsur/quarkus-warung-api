package io.bhimsur.domain.model.user

import java.util.UUID

data class UserUpdateInput(
    val id: UUID, val username: String, val fullName: String
)
