package io.bhimsur.infra.repository.entity.utils

import io.bhimsur.domain.model.user.User
import io.bhimsur.domain.model.user.UserModelBuilder
import io.bhimsur.infra.repository.entity.UserEntity
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class EntityUtils(
    private val userModelBuilder: UserModelBuilder
) {
    fun user(userEntity: UserEntity): User {
        val id = userEntity.id
        val username = userEntity.username
        val email = userEntity.email
        val password = userEntity.password
        val fullName = userEntity.fullName
        return userModelBuilder.build(id, username, email, password, fullName)
    }
}