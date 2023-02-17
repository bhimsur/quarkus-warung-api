package io.bhimsur.infra.repository.panache

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
    override fun topUp(wallet: Wallet) {
        persist(WalletEntity(wallet, true))
    }

    override fun withdraw(wallet: Wallet) {
        persist(WalletEntity(wallet, false))
    }

    override fun findByUserId(id: UUID): Optional<Wallet> {
        return find("user_id", id).firstResultOptional<WalletEntity>()
            .map { walletEntity: WalletEntity -> entityUtils.wallet(walletEntity) }
    }
}