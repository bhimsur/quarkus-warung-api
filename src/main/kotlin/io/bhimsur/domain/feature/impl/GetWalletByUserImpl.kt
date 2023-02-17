package io.bhimsur.domain.feature.impl

import io.bhimsur.domain.exception.NotFoundException
import io.bhimsur.domain.feature.GetWalletByUser
import io.bhimsur.domain.model.wallet.Wallet
import io.bhimsur.domain.model.wallet.WalletRepository
import java.util.*

class GetWalletByUserImpl(
    private val walletRepository: WalletRepository
) : GetWalletByUser {
    override fun command(id: UUID): Wallet {
        val wallet = walletRepository.findByUserId(id)
        return wallet.orElseThrow { NotFoundException("Wallet Not Found") }
    }
}