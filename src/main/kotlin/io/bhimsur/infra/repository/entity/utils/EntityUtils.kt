package io.bhimsur.infra.repository.entity.utils

import io.bhimsur.domain.model.user.User
import io.bhimsur.domain.model.user.UserModelBuilder
import io.bhimsur.domain.model.wallet.Wallet
import io.bhimsur.domain.model.wallet.WalletModelBuilder
import io.bhimsur.infra.repository.entity.UserEntity
import io.bhimsur.infra.repository.entity.WalletEntity
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class EntityUtils(
    private val userModelBuilder: UserModelBuilder,
    private val walletModelBuilder: WalletModelBuilder
) {
    fun user(userEntity: UserEntity): User {
        val id = userEntity.id
        val username = userEntity.username
        val email = userEntity.email
        val password = userEntity.password
        val fullName = userEntity.fullName
        return userModelBuilder.build(id, username, email, password, fullName)
    }

    fun wallet(walletEntity: WalletEntity): Wallet {
        val id = walletEntity.id
        val userId = walletEntity.userId
        val amount = walletEntity.amount
        return walletModelBuilder.build(id, userId, amount)
    }
}