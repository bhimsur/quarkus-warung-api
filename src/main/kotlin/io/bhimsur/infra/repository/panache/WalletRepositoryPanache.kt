package io.bhimsur.infra.repository.panache

import io.bhimsur.domain.exception.TransactionException
import io.bhimsur.domain.model.wallet.Wallet
import io.bhimsur.domain.model.wallet.WalletRepository
import io.bhimsur.infra.repository.entity.WalletEntity
import io.bhimsur.infra.repository.entity.utils.EntityUtils
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class WalletRepositoryPanache(
    private val entityUtils: EntityUtils
) : AbstractPanacheRepository<WalletEntity?, UUID?>(), WalletRepository {
    override fun topUp(wallet: Wallet): Wallet {
        persist(WalletEntity(wallet, true))
        if (isPersistent(WalletEntity(wallet, true))) {
            return wallet
        }
        throw TransactionException("Failed to insert : " + wallet.id)
    }

    override fun withdraw(wallet: Wallet): Wallet {
        persist(WalletEntity(wallet, false))
        if (isPersistent(WalletEntity(wallet, false))) {
            return wallet
        }
        throw TransactionException("Failed to insert : " + wallet.id)
    }

    override fun findByUserId(id: UUID): Optional<Wallet> {
        return find("user_id", id).firstResultOptional<WalletEntity>()
            .map { walletEntity: WalletEntity -> entityUtils.wallet(walletEntity) }
    }
}