package io.bhimsur.infra.repository.panache

import io.bhimsur.domain.model.user.User
import io.bhimsur.domain.model.user.UserRepository
import io.bhimsur.infra.repository.entity.UserEntity
import io.bhimsur.infra.repository.entity.utils.EntityUtils
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserRepositoryPanache(
    private val entityUtils: EntityUtils
) : AbstractPanacheRepository<UserEntity?, UUID?>(), UserRepository {

    override fun save(user: User) {
        persist(UserEntity(user))
    }

    override fun findByUsername(username: String): Optional<User> {
        return find("upper(username)", username.uppercase().trim { it <= ' ' })
            .firstResultOptional<UserEntity>()
            .map { userEntity: UserEntity -> entityUtils.user(userEntity) }
    }

    override fun findByEmail(email: String): Optional<User> {
        return find("upper(email)", email.uppercase().trim { it <= ' ' })
            .firstResultOptional<UserEntity>()
            .map { userEntity: UserEntity -> entityUtils.user(userEntity) }
    }

    override fun existsUsername(username: String): Boolean {
        return count("upper(username)", username.uppercase().trim { it <= ' ' }) > 0
    }

    override fun existsEmail(email: String): Boolean {
        return count("upper(email)", email.uppercase().trim { it <= ' ' }) > 0
    }

    override fun update(user: User) {
        val userEntity = findById(user.id)
        userEntity!!.update(user)
    }

}