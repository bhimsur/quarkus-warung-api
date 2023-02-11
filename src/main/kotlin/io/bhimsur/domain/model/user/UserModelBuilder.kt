package io.bhimsur.domain.model.user

import io.bhimsur.domain.validator.ModelValidator
import java.time.LocalDateTime
import java.util.*
import javax.inject.Named

@Named
class UserModelBuilder(private val modelValidator: ModelValidator) {
    fun build(username: String, email: String, password: String): User {
        return modelValidator.validate(User(UUID.randomUUID(), username, email, password, null, LocalDateTime.now(), null))
    }

    fun build(id: UUID, username: String, email: String, password: String, fullName: String?): User {
        return modelValidator.validate(User(id, username, email, password, fullName, LocalDateTime.now(), null))
    }
}