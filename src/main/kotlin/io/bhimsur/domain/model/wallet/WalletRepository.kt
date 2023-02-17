package io.bhimsur.domain.model.wallet

import java.util.Optional
import java.util.UUID

interface WalletRepository {
    fun topUp(wallet: Wallet): Wallet
    fun withdraw(wallet: Wallet): Wallet
    fun findByUserId(id: UUID): Optional<Wallet>
}