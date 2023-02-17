package io.bhimsur.domain.feature

import io.bhimsur.domain.model.wallet.Wallet
import java.util.UUID

interface GetWalletByUser {
    fun command(id: UUID): Wallet
}