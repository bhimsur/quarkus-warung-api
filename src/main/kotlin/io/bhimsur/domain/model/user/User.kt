package io.bhimsur.domain.model.user

import java.time.LocalDateTime
import java.util.*

data class User(
    val id: UUID,
    val username: String,
    val email: String,
    var password: String,
    val fullName: String?,
    val createDate: LocalDateTime,
    val modifiedDate: LocalDateTime?
)