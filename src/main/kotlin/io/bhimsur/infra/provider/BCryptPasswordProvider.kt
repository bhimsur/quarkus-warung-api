package io.bhimsur.infra.provider

import io.bhimsur.domain.model.provider.HashProvider
import org.mindrot.jbcrypt.BCrypt
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class BCryptPasswordProvider : HashProvider {
    override fun hashPassword(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }

    override fun checkPassword(plainText: String, hashed: String): Boolean {
        return BCrypt.checkpw(plainText, hashed)
    }
}